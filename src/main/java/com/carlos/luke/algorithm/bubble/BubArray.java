package com.carlos.luke.algorithm.bubble;

public class BubArray {
    // 数组
    private long[] arr;

    // 数组中有效数据的大小
    private int elems;

    //默认构造函数
    public BubArray() {
        arr = new long[50];
    }

    public BubArray(int max) {
        arr = new long[max];
    }

    // 插入数据
    public void insert(long value) {
        arr[elems] = value;
        elems++;
    }

    // 显示数据
    public void display() {
        for (int i = 0; i < elems; i++) {
            System.out.print(arr[i] + " ");
        }
        System.out.println();
    }

    // 冒泡排序
    public void bubbleSort() {
        long tmp = 0L;
        for(int i = 0; i < elems - 1; i++) {
            for(int j = 0; j < elems - i - 1; j++) {
                if(arr[j] > arr[j + 1]){
                    tmp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = tmp;
                }
            }
        }
    }
}