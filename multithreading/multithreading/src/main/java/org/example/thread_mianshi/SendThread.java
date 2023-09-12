package org.example.thread_mianshi;

import java.util.List;
import java.util.Random;

/**
 * @author Lihu
 * @PROJECT_NAME: learnDistributed
 * @DESCRIPTION:
 * @USER: Irene-Jisoo
 * @DATE: 2023/9/5 15:49
 */
public class SendThread extends Thread {

    private List<String> gift;

    private int count;

    public SendThread(List<String> gift, String name) {
        super(name);
        this.gift =gift;

    }

    @Override
    public void run() {
        // 小明 ，小红 发礼物出去
        // 实现线程安全问题
        Random random = new Random();
        String name = Thread.currentThread().getName();
        while (true) {
            synchronized (gift) {
                if (gift.size() < 10) {
                    break;
                }
                String remove = gift.remove(random.nextInt(gift.size()));
                System.out.println(name + " 发出了" + remove);
                count++;
            }
        }



    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
}
