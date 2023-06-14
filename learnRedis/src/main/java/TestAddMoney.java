import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author Lihu
 * @PROJECT_NAME: learnDistributed
 * @DESCRIPTION:
 * @USER: Irene-Jisoo
 * @DATE: 2023/5/21 11:53
 */
public class TestAddMoney {
    
    public static void main(String[] args) {
        Account account = new Account();

        // 创建100 个线程池
        ExecutorService service = Executors.newFixedThreadPool(100);
        for (int i = 0; i < 100; i++) {
            // 调用构造方法
            service.execute(new AddMoneyThread(account, 1));
            //

        }

        // 关闭线程
        service.shutdown();
        while (!service.isTerminated()){

        }
        System.out.println(account.getBalance());
    }
}

class Account {
    private double balance;

    private Lock accountLock = new ReentrantLock();

    public double getBalance() {
        return balance;
    }

    public synchronized void deposit(double money) {
        accountLock.lock();
        try {
            balance += balance;
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            accountLock.unlock();
        }

    }
}

class AddMoneyThread implements Runnable {
    private Account account;
    private double money;

    public AddMoneyThread(Account account, double money) {
        this.account = account;
        this.money = money;
    }

    @Override
    public void run() {
        synchronized (account) {
            account.deposit(money);
        }
    }
}

