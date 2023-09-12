package org.example.thread_safe;

/**
 * @author Lihu
 * @PROJECT_NAME: learnDistributed
 * @DESCRIPTION:
 * @USER: Irene-Jisoo
 * @DATE: 2023/9/4 15:34
 *
 *  同步代码块 和同步方法 相比
 *
 *  范围上 ： 同步代码块锁的 范围更小 ， 同步方法所得范围更大
 *
 *  可读性 ： 同步方法更好
 *
 */
public class ThreadTest {
    public static void main(String[] args) {
        Account account = new Account("red velvet", 1000000);
        // 创建两个线程 ，分别代表小红和小明 去同一个账户对象中取钱 10 万
        new DrawThread(account,"小明").start();
        new DrawThread(account,"小红").start();
        Account account1 = new Account("red velvet", 1000000);
        // 创建两个线程 ，分别代表小红和小明 去同一个账户对象中取钱 10 万
        new DrawThread(account1,"小黑").start();
        new DrawThread(account1,"小白").start();
    }
}
