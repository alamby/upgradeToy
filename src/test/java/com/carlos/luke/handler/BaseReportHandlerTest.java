package com.carlos.luke.handler;

import static com.carlos.luke.handler.Router.validateResult;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.joda.time.DateTime;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.util.ReflectionTestUtils;

public class BaseReportHandlerTest {
    private static final Logger logger = LoggerFactory.getLogger(BaseReportHandlerTest.class);
    private Router dspRouter = new Router();

    @Before
    public void init() {
        ReflectionTestUtils.setField(dspRouter, "routerApiAddress", "http://127.0.0.1/router/rest");
    }

    @Test
    public void test_getBoards() {
        Date from = new DateTime(2017, 7, 12, 0, 0, 0).toDate();
        Date to = new DateTime(2017, 7, 18, 23, 59, 59).toDate();
        Map<String, Object> param = new HashMap<>();
        param.put("from", from);
        param.put("to", to);
        param.put("pageSize", "10");
        param.put("pageNo", "2");
        validateResult(dspRouter.request(param, "uap-server.uapService.getBoards"));
    }
    
    @Test
    public void test_addBoard() {
        String title = "标题1";
        String content = "内容1";
        int weight = 5;
        Map<String, Object> param = new HashMap<>();
        param.put("title", title);
        param.put("content", content);
        param.put("weight", weight);
        validateResult(dspRouter.request(param, "uap-server.uapService.addBoard"));
    }
    
    @Test
    public void test_updateBoard() {
        int id = 4;
        String title = "bbb";
        String content = "2333";
        int weight = 3;
        Map<String, Object> param = new HashMap<>();
        param.put("id", id);
        param.put("title", title);
        param.put("content", content);
        param.put("weight", weight);
        validateResult(dspRouter.request(param, "uap-server.uapService.updateBoard"));
    }
    
    @Test
    public void test_delBoard() {
        int id = 4;
        Map<String, Object> param = new HashMap<>();
        param.put("id", id);
        validateResult(dspRouter.request(param, "uap-server.uapService.delBoard"));
    }
    
    @Test
    public void test_getSysList() {
        Map<String, Object> param = new HashMap<>();
        param.put("username", "hegao");
        validateResult(dspRouter.request(param, "uap-server.uapService.getSysList"));
    }
    @Test
    public void test_getUserDetail() {
        Map<String, Object> param = new HashMap<>();
        param.put("username", "hegao");
        validateResult(dspRouter.request(param, "uap-server.uapService.getUserDetail"));
    }
    
    @Test
    public void test_getOrgCode() {
        Map<String, Object> param = new HashMap<>();
        param.put("orgcode", "01.02.14");
        validateResult(dspRouter.request(param, "uap-server.uapService.getOrgCode"));
    }    
}
