package org.example;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * @author Lihu
 * @PROJECT_NAME: learnDistributed
 * @DESCRIPTION:
 * @USER: Irene-Jisoo
 * @DATE: 2023/8/29 16:42
 */
public class ThreadDemo {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        /**
         * 多线程的第一种启动方式：
         * 1、 自定义一个继承Thread
         * 2.  重写run 方法
         * 3、 创建子线程的对象，并启动线程
         **/
        MyThread t1 = new MyThread();
        MyThread t2 = new MyThread();

        t1.setName("线程一");
        t2.setName("线程二");

//        t1.start();
//        t2.start();

        /**
         * 创建MyRun 对象
         * 表示多线程要指向任务
         *
         */
        MyRun mr = new MyRun();

        // 创建线程对象
        Thread t3 = new Thread(mr);
        Thread t4 = new Thread(mr);

        // 给线程设置名字

        t3.setName("线程3");
        t4.setName("线程4");

        // 开启线程
//        t3.start();
//        t4.start();




        /**
         * 多线程的第三种方式：
         * 特点： 可以获取到多线程运行到的结果
         * 1、 创建一个类 Mycallable去实现Callable 接口
         * 2.重写 call （是有返回值的，便是多线程运行的结果）
         * 3、 创建Mycallable的对象 表示多线程要执行的任务
         * 创建FutureTask的对象 作用管理多线程运行的结果
         * 创建Thread类的对象，并启动（表示线程）
         * */

        MyCallable mc = new MyCallable();

        FutureTask<Integer> futureTask = new FutureTask<>(mc);

        Thread t5 = new Thread(futureTask);
        t5.start();
        System.out.println(futureTask.get());


    }
}
