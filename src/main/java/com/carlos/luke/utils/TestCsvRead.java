package com.carlos.luke.utils;

import java.io.BufferedReader;
import java.io.FileReader;

public class TestCsvRead { 

    public static void main(String[] args) { 
        readFile(); 
    }

    private static void readFile() {
        try { 
            BufferedReader reader = new BufferedReader(new FileReader("D://export//1.txt"));//换成你的文件名
//            reader.readLine();//第一行信息，为标题信息，不用，如果需要，注释掉
            String line = null; 
            while((line=reader.readLine())!=null){ 
                String item[] = line.split(",");//CSV格式文件为逗号分隔符文件，这里根据逗号切分
                 
                String last = item[item.length-1];//这就是你要的数据了
                //int value = Integer.parseInt(last);//如果是数值，可以转化为数值
                System.out.println(last); 
            } 
        } catch (Exception e) { 
            e.printStackTrace(); 
        }
    } 

}