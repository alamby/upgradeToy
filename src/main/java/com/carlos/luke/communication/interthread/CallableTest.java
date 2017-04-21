package com.carlos.luke.communication.interthread;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
* @desc    
* @since   2017年4月21日
* 子线程完成某件任务后，把得到的结果回传给主线程
* 注意!!会阻塞主线程。若不想阻塞可用 ExecutorService，把 FutureTask 放到线程池去管理执行。
*/
public class CallableTest {

    public static void main(String[] args) {
        doTaskWithResultInWorker();
    }
    
    private static void doTaskWithResultInWorker() {
        Callable<Integer> callable = new Callable<Integer>() {
            @Override
            public Integer call() throws Exception {
                System.out.println("Task starts");
                Thread.sleep(5000);
                int result = 0;
                for (int i=0; i<=100; i++) {
                    result += i;
                }
                System.out.println("Task finished and return result");
                return result;
            }
        };

        FutureTask<Integer> futureTask = new FutureTask<>(callable);
        new Thread(futureTask).start();


        try {
            System.out.println("Before futureTask.get()");
            System.out.println("Result: " + futureTask.get());
            System.out.println("After futureTask.get()");
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }
}
