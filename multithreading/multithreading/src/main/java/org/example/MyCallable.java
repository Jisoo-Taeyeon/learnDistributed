package org.example;

import java.util.concurrent.Callable;

/**
 * @author Lihu
 * @PROJECT_NAME: learnDistributed
 * @DESCRIPTION:
 * @USER: Irene-Jisoo
 * @DATE: 2023/8/29 17:05
 */
public class MyCallable implements Callable<Integer> {

    @Override
    public Integer call() throws Exception {
        // 求1-100 之间的和
        int sum = 0;
        for (int i = 0; i < 100; i++) {
            sum += i;
        }
        return sum;
    }
}
