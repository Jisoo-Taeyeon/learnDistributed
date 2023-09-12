package org.example.thread_pool;

import java.util.concurrent.Callable;

/**
 * @author Lihu
 * @PROJECT_NAME: learnDistributed
 * @DESCRIPTION:
 * @USER: Irene-Jisoo
 * @DATE: 2023/9/4 21:26
 */
public class MyCallable implements Callable {
    private int n;

    @Override
    public String call() throws Exception {
        int sum = 0;
        for (int i = 0; i < n+1; i++) {
            sum += i;
        }

        return Thread.currentThread().getName() + " 求出了1-" + n + "和是  " + sum;
    }

    public MyCallable(int n) {
        this.n = n;
    }
}
