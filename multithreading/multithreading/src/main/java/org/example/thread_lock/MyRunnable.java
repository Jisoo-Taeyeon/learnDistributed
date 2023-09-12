package org.example.thread_lock;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author Lihu
 * @PROJECT_NAME: learnDistributed
 * @DESCRIPTION:
 * @USER: Irene-Jisoo
 * @DATE: 2023/9/5 13:21
 */
public class MyRunnable implements Runnable {
    /**
     * 记录浏览人次
     */
    private int number;

    private AtomicInteger count = new AtomicInteger();

    @Override
    public void run() {
        for (int i = 0; i < 100; i++) {
            System.out.println(this);
            System.out.println("count ==================="+ count.incrementAndGet());
//            synchronized (this) {
//                System.out.println("number ==================="+ ++number);
//            }
        }

    }
}
