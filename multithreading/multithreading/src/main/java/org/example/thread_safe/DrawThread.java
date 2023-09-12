package org.example.thread_safe;

/**
 * @author Lihu
 * @PROJECT_NAME: learnDistributed
 * @DESCRIPTION:
 * @USER: Irene-Jisoo
 * @DATE: 2023/9/4 15:36
 */
public class DrawThread extends Thread {

    private  Account account;

    private static final Object lock = new Object();

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public DrawThread(Account account, String name) {
        super( name);
        this.account = account;
    }

    @Override
    public void run() {
        synchronized (DrawThread.class) {
            account.drawMoney(1000000);
        }
        // 取钱

    }
}
