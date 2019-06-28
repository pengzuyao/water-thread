package com.water.thread.wblClass22;

import com.google.common.collect.Lists;

import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * @Description:简化的线程池，仅用来说明工作原理
 * @Author: pengzuyao
 * @Time: 2019/06/26
 */
public class MyThreadPool {

    /**
     * 利用阻塞队列实现生产者-消费者模式
     */
    BlockingQueue<Runnable> workQueue;
    /**
     * 保存内部工作线程
     */
    List<WorkerThread> threads = Lists.newArrayList();

    /**
     * 构造方法
     */
    public MyThreadPool(int poolSize , BlockingQueue<Runnable> workQueue ){
        this.workQueue = workQueue;
        //创建工作线程
        for (int idx = 0; idx < poolSize; idx++) {
            WorkerThread workerThread = new WorkerThread();
            workerThread.start();
            threads.add(workerThread);
        }
    }


    void execute(Runnable command){
        try {
            workQueue.put(command);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    class WorkerThread extends Thread {

        @Override
        public void run() {
            //循环取任务并执行
            while (true){
                try {
                    Runnable task = workQueue.take();
                    task.run();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static void main(String[] args) {
        //创建有界阻塞队列
        BlockingQueue<Runnable> workQueue = new LinkedBlockingQueue<>(2);
        //创建线程池
        MyThreadPool pool = new MyThreadPool(10 ,workQueue);
        pool.execute(()->{
            System.out.println("my thread pool start");
        });
    }
    /**
     * 线程池的拒绝策略
     * 1、CallerRunsPolicy:提交任务的线程自己去执行该任务
     * 2、AbortPoliy:默认拒绝策略，会throws RejectedExecutionException
     * 3、DiscardPolicy:直接丢弃任务，没有任何异常抛出
     * 4、DiscardOldersPolicy:丢弃最老的任务，其实就是把最早进入工作队列的任务丢弃，然后把新任务加到工作队列
     */
}
