package org.example;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Lihu
 * @PROJECT_NAME: learnDistributed
 * @DESCRIPTION:
 * @USER: Irene-Jisoo
 * @DATE: 2023/9/4 14:41
 */
public class Test {
    public static void main(String[] args) {
        List<Integer> list = new ArrayList<>();

        for (int i = 0; i < 100; i++) {
            list.add(i);
            list.remove(i);
            i--;

        }
        System.out.println(list);

    }
}
