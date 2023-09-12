package org.example.thread_mianshi;

import org.example.thread_pool.MyRunnable;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * @author Lihu
 * @PROJECT_NAME: learnDistributed
 * @DESCRIPTION:
 * @USER: Irene-Jisoo
 * @DATE: 2023/9/5 14:10
 * 目标：1有100份礼品，小红，小明两人同时发送，当剩下的礼品小于10份的时候则不再送出，
 * 利用多线程模拟该过程并将线程的名称打印出来。并最后在控制台分别打印小红，小明各自送出多少分礼物。
 */
public class Demo1 {
    public static void main(String[] args) throws InterruptedException {
        // 1、 拿100份礼物 到程序中来

        List<String> gift = new ArrayList<>();
        String[] names = {"口红", "mac", "鲜花", "switch", "watch", "game"};
        Random runnable = new Random();
        for (int i = 0; i < 100; i++) {
            gift.add(names[runnable.nextInt(names.length)] + (i + 1));

        }
        System.out.println(gift);
        SendThread t = new SendThread(gift, "小明");
        SendThread sendThread = new SendThread(gift, "小红");
        sendThread.start();
        t.start();


        t.join();
        sendThread.join();
        System.out.println(t.getCount());
        System.out.println(sendThread.getCount());
        // 2、 定义线程


    }


}
