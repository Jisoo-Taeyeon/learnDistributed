package org.example.thread_safe;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author Lihu
 * @PROJECT_NAME: learnDistributed
 * @DESCRIPTION:
 * @USER: Irene-Jisoo
 * @DATE: 2023/9/4 15:33
 */
public class Account {
    private String cardId;
    private double money;
    /**
     * 创建一个锁对象
     *
     */
    private final Lock lock = new ReentrantLock();


    public double getMoney() {
        return money;
    }

    public void setMoney(double money) {
        this.money = money;
    }

    public String getCardId() {
        return cardId;
    }

    public void setCardId(String cardId) {
        this.cardId = cardId;
    }

    public Account(String cardId,double money) {
        this.money = money;
        this.cardId = cardId;
    }

    public Account() {
    }

    public void drawMoney(double money) {
        // 先搞清楚谁来取钱
        String name = Thread.currentThread().getName();
        lock.lock();

        try {
            // 1、 判断余额是否足够

            if (this.money >= money) {
                System.out.println(name + "取了 " + money + "元，取钱成功");
                this.money -= money;
                System.out.println(name+"取钱后，余额为 "+ this.money);
            } else {
                System.out.println(name +" 来取钱，余额不足");
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            lock.unlock();
        }


    }
}
