package com.carlos.luke.json;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Iterator;

public class ParseDubboMonitorLog {
    public static void main(String[] args) throws FileNotFoundException {
        for (String line : IO.lineSeq("D:\\123.txt")) {
            if (line.contains("dsp_business")) {
                String[] out = line.split("&");
                for (String string : out) {
                    System.out.println(string);
                }
                System.out.println("______________");
            }
        }
    }
}

final class IO {
    public static Iterable<String> lineSeq(final String path) throws FileNotFoundException {

        return new Iterable<String>() {
            BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(
                                      path)));

            @Override
            public Iterator<String> iterator() {
                return new Iterator<String>() {
                    private String content;

                    @Override
                    public void remove() {
                        throw new UnsupportedOperationException();
                    }

                    @Override
                    public String next() {
                        return content;
                    }

                    @Override
                    public boolean hasNext() {
                        try {
                            content = reader.readLine();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }

                        if (content != null) {
                            return true;
                        }

                        try {
                            reader.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }

                        return false;
                    }
                };
            }

        };

    }
}