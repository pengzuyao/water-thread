package com.water.thread.wblClass36;

import java.util.Random;
/**
 * Destription:
 * Author: pengzuyao
 * Time: 2019-06-28
 */
public class Test01 {

    private static Integer count = 0;
    private final static Integer EMPTY = 0;
    private final static Integer FULL = 10;
    private final static String LOCK = "lock";

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
                synchronized (LOCK){
                    while (count == FULL){
                        try {
                            LOCK.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    count ++;
                    System.out.println(Thread.currentThread().getName() + "生产者生产，目前总共有" + count);
                    LOCK.notifyAll();
                }
            }
        }
    }

    //消费者
    public class Consumer implements Runnable{
        @Override
        public void run() {
            while (true){
                try {
                    Thread.sleep(new Random().nextInt(5));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                synchronized (LOCK){
                    while (count == EMPTY){
                        try {
                            LOCK.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    count --;
                    System.out.println(Thread.currentThread().getName() + "消费者消费，目前总共有" + count);
                    LOCK.notifyAll();
                }
            }
        }
    }

    public static void main(String[] args) {
       Test01 test01 = new Test01();
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
