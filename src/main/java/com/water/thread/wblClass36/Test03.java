package com.water.thread.wblClass36;

import java.util.Random;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Destription:
 * Author: pengzuyao
 * Time: 2019-06-28
 */
public class Test03 {

    private BlockingQueue<Integer> bq = new LinkedBlockingQueue(10);

    //生产者
    public class Producer implements Runnable{
        @Override
        public void run() {
            while (true){
                try {
                    Thread.sleep(1);
                    bq.put(produce());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }

        public Integer produce(){
            System.out.println(Thread.currentThread().getName() + "生产者生产，目前总共有" + bq.size());
            return 1;
        }

    }

    //消费者
    public class Consumer implements Runnable {
        @Override
        public void run() {
            while (true) {
                try {
                    Thread.sleep(3);
                    consume(bq.take());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }

        public void consume(Integer integer){
            System.out.println(Thread.currentThread().getName() + "消费者消费，目前总共有" + bq.size());
        }
    }

    public static void main(String[] args) {
       Test03 test01 = new Test03();
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
