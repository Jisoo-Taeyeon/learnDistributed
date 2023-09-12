package org.example;

/**
 * @author Lihu
 * @PROJECT_NAME: learnDistributed
 * @DESCRIPTION:
 * @USER: Irene-Jisoo
 * @DATE: 2023/8/29 16:39
 */
public class MyThread extends Thread {

    @Override
    public void run() {
        // 书写线程要执行的代码
        for (int i = 0; i < 100; i++) {
            System.out.println(getName()+" red velvet");

        }



    }
}
