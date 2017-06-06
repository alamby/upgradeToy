package com.carlos.luke.collection;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.LinkedBlockingQueue;

import org.junit.Test;

/**
* @desc    
* @since   2017年4月7日
*
*/
public class CollectionTest {

    @Test
    public void test_List_Set() {
        Set<String> set = new HashSet<>();
        set.add("AAA");
        set.add("bbb");
        set.add("AAA");
        List<String> list = new ArrayList<>();
        list.add("AAA");
        list.add("bbb");
        list.add("AAA");
        System.out.println(set);
    }
    
    @Test
    public void test_queue_copy() {
        LinkedBlockingQueue<String> queue = new LinkedBlockingQueue<String>();
        queue.add("abc");
        LinkedBlockingQueue<String> queueMileage = new LinkedBlockingQueue<String>(queue);
//        String trucknum = queueMileage.poll();
        queueMileage.remove();
//        System.out.println(trucknum);
        System.out.println(queue);
    }
}
