package org.example.thread_pool;



import java.math.BigDecimal;
import java.util.concurrent.*;

/**
 * @author Lihu
 * @PROJECT_NAME: learnDistributed
 * @DESCRIPTION:
 * @USER: Irene-Jisoo
 * @DATE: 2023/9/4 20:39
 *
 * ●参数一：corePoolSize:指定线程池的核心线程的数量。
 * ●参数二：maximumPoolSize:指定线程池的最大线程数量。
 * ●参数三：keepAliveTime:指定临时线程的存活时间。
 * ●参数四：uit：指定临时线程存活的时间单位（秒、分、时、天）
 * ●参数五：workQueue:指定线程池的任务队列。 LinkedBlockingQueue 链表队列  ArrayBlockingQueue 数组队列
 * ●参数六：threadFactory:指定线程池的线程工厂。
 * ●参数七：handler:指定线程池的任务拒绝策略（线程都在忙，任务队列也满了的时候，新任务来了该怎么处理)
 *
 * 临时线程创建时间 新任务提交时发现核心线程都在忙，任务队列也满了，并且还可以创建临时线程，此时才会创建临时队列
 *
 * 什么时候开始拒接新任务  核心线程和临时线程都在忙，任务队列也满了，新的任务过来的时候才会开始拒接任务
 *
 * void execute(Runnable command)  执行Runnable任务
 * Future<T>submit(Callable<T>task)  执行Callab1e任务，返回未来任务对象，用于获取线程返回的结果
 * void shutdown()  等全部任务执行完毕后，再关闭线程池！
 * List<Runnable>shutdownNow()  立刻关闭线程池，停止正在执行的任务，并返回队列中未执行的任务
 *
 *

 * ThreadPoolExecutor.AbortPolicy   丢弃任务并抛出RejectedExecutionException.异常。是默认的策略
 * ThreadPoolExecutor.DiscardPolicy:  丢弃任务，但是不抛出异常这是不推荐的做法
 * ThreadPoolExecutor.DiscardoldestPolicy 抛弃队列中等待最久的任务然后把当前任务加入队列中
 * ThreadPoolExecutor.CallerRunsPolicy  由主线程负责调用任务的run0方法从而绕过线程池直接执行
 *
 * 核心线程配置
 * 计算密集型的任务 核心线程数量 =  cpu的核数+1
 * IO 密集型的任务 核心线程数量 = cpu *2 ；
 */
public class ThreadPoolTest1 {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        //1、 通过ThreadPoolExecutor 创建一个线程池对象
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(3, 5, 200, TimeUnit.SECONDS,
                new ArrayBlockingQueue<>(4), Executors.defaultThreadFactory(),
                new ThreadPoolExecutor.AbortPolicy());

        Runnable target = new MyRunnable();
        // 线程池会自动创建一个新线程，自动处理这个任务，自动执行
        threadPoolExecutor.execute(target);
        threadPoolExecutor.execute(target);
        threadPoolExecutor.execute(target);
        // 复用前面核心线程 进入队列排队
        threadPoolExecutor.execute(target);
        threadPoolExecutor.execute(target);
        threadPoolExecutor.execute(target);
        threadPoolExecutor.execute(target);
        // 到了临时线程的创建时时间了
//        threadPoolExecutor.execute(target);
//        threadPoolExecutor.execute(target);
        // 到了新任务的拒接时间了
//        threadPoolExecutor.execute(target);
        // 等待线程池的任务全部执行完毕后，再关闭线程池
//        threadPoolExecutor.shutdown();
        // 立即关闭线程池 不管任务是否执行完毕
        threadPoolExecutor.shutdownNow();

        // 2 、 使用线程处理Callable任务

        ThreadPoolExecutor pool = new ThreadPoolExecutor(3, 5, 200, TimeUnit.SECONDS,
                new ArrayBlockingQueue<>(4), Executors.defaultThreadFactory(),
                new ThreadPoolExecutor.AbortPolicy());

        Future f1 = pool.submit(new MyCallable(100));
        Future f2 = pool.submit(new MyCallable(200));
        Future f3 = pool.submit(new MyCallable(300));
        Future f4 = pool.submit(new MyCallable(400));
        Future f5 = pool.submit(new MyCallable(500));

        System.out.println(f1.get());
        System.out.println(f2.get());
        System.out.println(f3.get());
        System.out.println(f4.get());
        System.out.println(f5.get());

        // 通过Executors 创建一个线程池对象
        ExecutorService service = Executors.newFixedThreadPool(3);
        ExecutorService service1 = Executors.newSingleThreadExecutor();


    }

}
