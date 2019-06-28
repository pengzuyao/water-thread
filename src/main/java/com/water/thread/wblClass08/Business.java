package com.water.thread.wblClass08;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/*
 * @Description:使用lock/condition实现等待通知机制优化
 * @Author: pengzuyao
 * @Time: 2019/06/25
 */
public class Business {

    private Lock lock = new ReentrantLock();
    private Condition conditionA = lock.newCondition();
    private Condition conditionB = lock.newCondition();
    private Condition conditionC = lock.newCondition();
    private String type = "A";

    private static void run() {
    }

    public void printA() throws InterruptedException {
        lock.lock();
        try {
            while (type != "A"){
                conditionA.await();
            }
            System.out.println(Thread.currentThread().getName() +"正在打印A");
            type = "B";
            //唤醒在等待conditionB对象上的线程，将信号传递出去
            conditionB.signal();
        }finally {
            lock.unlock();
        }
    }

    public void printB() throws InterruptedException {
        lock.lock();
        try {
            while (type != "B"){
                conditionB.await();
            }
            System.out.println(Thread.currentThread().getName() +"正在打印B");
            type = "C";
            //唤醒在等待conditionC对象上的线程，将信号传递出去
            conditionC.signal();
        }finally {
            lock.unlock();
        }
    }

    public void printC() throws InterruptedException {
        lock.lock();
        try {
            while (type != "C"){
                conditionC.await();
            }
            System.out.println(Thread.currentThread().getName() +"正在打印C");
            type = "A";
            //唤醒在等待conditionA对象上的线程，将信号传递出去
            conditionA.signal();
        }finally {
            lock.unlock();
        }
    }

    public static void main(String[] args) {
        final Business business = new Business();
        Thread thread1 = new Thread(() -> {
            try {
                for (int i = 0; i < 10; i++) {
                    business.printA();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        Thread thread2 = new Thread(() -> {
            try {
                for (int i = 0; i < 10; i++) {
                    business.printB();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        Thread thread3 = new Thread(() -> {
            try {
                for (int i = 0; i < 10; i++) {
                    business.printC();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        thread1.setName("线程1");
        thread2.setName("线程2");
        thread3.setName("线程3");

        thread1.start();
        thread2.start();
        thread3.start();
    }
}
