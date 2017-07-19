package com.carlos.luke.handler;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.security.MessageDigest;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.zip.GZIPInputStream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.google.gson.Gson;

@Component
//TODO 使用httpclient来实现
public class Router {
    private static final Logger logger = LoggerFactory.getLogger(Router.class);
    @Value("${router.api.address}")
    private String routerApiAddress;
    private int version = 0;
    private static final String DEFAULT_CHARSET = "utf-8";

    public void setVersion(int version) {
        this.version = version;
    }

    private String createRequestParam(Map<String, Object> map) {
        StringBuilder param = new StringBuilder();
        for (Iterator<Map.Entry<String, Object>> it = map.entrySet().iterator(); it.hasNext(); ) {
            Map.Entry<String, Object> entry = it.next();
            Object value = entry.getValue();
            if (value != null) {
                try {
                    value = URLEncoder.encode(value.toString(), DEFAULT_CHARSET);
                } catch (UnsupportedEncodingException ex) {
                    throw new RuntimeException(ex);
                }
                param.append("&").append(entry.getKey()).append("=").append(value);
            }
        }
        return param.toString().substring(1);
    }

    public static void validateResult(String response) {
        Gson gson = new Gson();
        RouterResponse drr = gson.fromJson(response, RouterResponse.class);
        if (!"0".equals(drr.getCode())) {
            throw new HandlerTestException(drr.getMessage());
        }
    }

    public String request(Map<String, Object> map, String method) {
        map.putAll(systemParams());
        map.put("method", method);
        //88DA9E321AF1B146B758460D5D0697F8
        //httx
        map.put("sign", sign(Collections.unmodifiableMap(map), "88DA9E321AF1B146B758460D5D0697F8"));
        return request(routerApiAddress, createRequestParam(map));
    }

    private Map<String, Object> systemParams() {
        Map<String, Object> systemParams = new HashMap<>();
        if(version>0){
            systemParams.put("version", version);
        }
        //bc7e94
        //1088
        systemParams.put("app_key", "bc7e94");
        systemParams.put("format", "json");
        systemParams.put("timestamp", new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
        return systemParams;
    }

    private String sign(Map params, String secret) {
        String result = null;
        if (params == null)
            return result;
        // 将参数按key排序
        Set<String> keys = params.keySet();
        String[] ps = new String[keys.size()];
        int i = 0;
        for (String key : keys) {
            Object value = params.get(key);
            if (value != null) {
                ps[i++] = key + value.toString();
            }
        }
        if (i == 0)
            return result;
        String[] ss = new String[i];
        System.arraycopy(ps, 0, ss, 0, i);
        Arrays.sort(ss);
        // 将secret同时放在头尾，拼成字符串
        StringBuilder origin = new StringBuilder(secret);
        for (int j = 0; j < ss.length; j++) {
            origin.append(ss[j]);
        }
        origin.append(secret);
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            result = byte2hex(md.digest(origin.toString().getBytes(DEFAULT_CHARSET)));
        } catch (Exception ex) {
            throw new RuntimeException("sign error !", ex);
        }
        return result;
    }

    private static String byte2hex(byte[] b) {
        StringBuffer hs = new StringBuffer();
        String stmp = "";
        for (int n = 0; n < b.length; n++) {
            stmp = (Integer.toHexString(b[n] & 0XFF));
            if (stmp.length() == 1)
                hs.append("0").append(stmp);
            else
                hs.append(stmp);
        }
        return hs.toString().toUpperCase();
    }

    private String request(String urlStr, String content) {
        long start = System.currentTimeMillis();
        logger.info("url:{}, content:{}", urlStr, content);
        HttpURLConnection connection = null;
        DataOutputStream out = null;
        String response = null;
        BufferedReader reader = null;
        try {
            URL url = new URL(urlStr);
            connection = (HttpURLConnection) url.openConnection();
            connection.setDoOutput(true);
            connection.setDoInput(true);
            connection.setRequestMethod("POST");
            connection.setUseCaches(false);
            connection.setRequestProperty("Accept-Encoding", "gzip");
            connection.connect();
            if (content != null) {
                out = new DataOutputStream(connection.getOutputStream());
                out.writeBytes(content);
            }
            String encoding = connection.getContentEncoding();
            InputStream inputStream = connection.getInputStream();
            if ("gzip".equals(encoding)) {
                InputStreamReader is = new InputStreamReader(new GZIPInputStream(inputStream), DEFAULT_CHARSET);
                reader = new BufferedReader(is);
            } else {
                InputStreamReader is = new InputStreamReader(inputStream, DEFAULT_CHARSET);
                reader = new BufferedReader(is);
            }
            if (connection.getResponseCode() == HttpURLConnection.HTTP_OK) {
                StringBuffer buffer = new StringBuffer();
                String line;
                while ((line = reader.readLine()) != null) {
                    buffer.append(line);
                }
                response = buffer.toString();
                logger.info("Response:{}", response);
            } else {
                logger.info("url:{},ResponseCode:{},ResponseMessage:{}", urlStr, connection.getResponseCode(), connection.getResponseMessage());
                throw new RuntimeException();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            long end = System.currentTimeMillis();
            logger.info("request time elapse:{}", (end - start));
            if (connection != null) {
                connection.disconnect();
            }
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    logger.warn("", e);
                }
            }
            if (out != null) {
                try {
                    out.flush();
                } catch (IOException e) {
                    logger.warn("", e);
                }
                try {
                    out.close();
                } catch (IOException e) {
                    logger.warn("", e);
                }
            }
        }
        return response;
    }
}
