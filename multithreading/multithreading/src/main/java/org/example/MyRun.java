package org.example;

/**
 * @author Lihu
 * @PROJECT_NAME: learnDistributed
 * @DESCRIPTION:
 * @USER: Irene-Jisoo
 * @DATE: 2023/8/29 16:49
 */
public class MyRun implements Runnable {


    @Override
    public void run() {
        // 书写线程要执行的代码
        for (int i = 0; i < 100; i++) {
            // 获取线程当前对象
            Thread t = Thread.currentThread();
            System.out.println(t.getName() + " red velvet");

        }
    }
}
