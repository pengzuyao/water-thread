package com.water.thread.wblClass24;

import java.util.concurrent.TimeUnit;

/**
 * @Description: 异步化：利用多线程优化性能这个核心方案得以实施的基础
 * @Author: pengzuyao
 * @Time: 2019/06/26
 */
public class AsyncTest {


    public void printA(){
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("AAAAA");
    }

    public void printB(){
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("BBBBB");
    }

    public static void main(String[] args) {
        AsyncTest asyncTest = new AsyncTest();
        Thread t1 = new Thread(()->{
            asyncTest.printA();
        });
        Thread t2 = new Thread(()->{
            asyncTest.printB();
        });
        t1.start();
        t2.start();
        System.out.println("main");
    }
}
