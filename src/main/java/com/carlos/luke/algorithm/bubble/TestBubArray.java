package com.carlos.luke.algorithm.bubble;
public class TestBubArray {
    public static void main(String[] args) {
        BubArray barr = new BubArray();
        barr.insert(30);
        barr.insert(78);
        barr.insert(56);
        barr.insert(102);
        barr.insert(1);
        barr.insert(789);
        barr.insert(23);

        barr.display();
        barr.bubbleSort();
        barr.display();
    }

}