package com.water.thread.wblClass36;

import java.util.Random;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Destription:
 * Author: pengzuyao
 * Time: 2019-06-28
 */
public class Test02 {

    private static Integer count = 0;
    private final static Integer EMPTY = 0;
    private final static Integer FULL = 10;
    private static Lock lock = new ReentrantLock();
    private final Condition notEmpty = lock.newCondition();
    private final Condition notFull = lock.newCondition();


    //生产者
    public class Producer implements Runnable{
        @Override
        public void run() {
            while (true){
                try {
                    Thread.sleep(new Random().nextInt(5));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                lock.lock();
                try {
                    while (count == FULL){
                        try {
                            notFull.await();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    count ++;
                    System.out.println(Thread.currentThread().getName() + "生产者生产，目前总共有" + count);
                    //唤醒消费者
                    notEmpty.signalAll();
                }finally {
                    lock.unlock();
                }


            }
        }
    }

    //消费者
    public class Consumer implements Runnable{
        @Override
        public void run() {
            while (true) {
                try {
                    Thread.sleep(new Random().nextInt(5));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                lock.lock();
                try {
                    while (count == EMPTY) {
                        try {
                            notEmpty.await();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    count--;
                    System.out.println(Thread.currentThread().getName() + "消费者消费，目前总共有" + count);
                    //唤醒生产者
                    notFull.signalAll();
                }finally {
                lock.unlock();
                }
            }
        }
    }

    public static void main(String[] args) {
       Test02 test01 = new Test02();
       Thread th1 = new Thread(test01.new Producer());
       th1.setName("001");
       Thread th2 = new Thread(test01.new Consumer());
       th2.setName("002");
       Thread th3 = new Thread(test01.new Consumer());
       th3.setName("003");

       th1.start();
       th2.start();
       th3.start();
    }

}
