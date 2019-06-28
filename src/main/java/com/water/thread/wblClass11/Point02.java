package com.water.thread.wblClass11;

import java.util.concurrent.locks.LockSupport;
import java.util.concurrent.locks.StampedLock;

/**
 * @Description:
 * @Author: pengzuyao
 * @Time: 2019/06/25
 */
public class Point02 {


    public void exec() throws InterruptedException {
        final StampedLock lock = new StampedLock();
        Thread t1 = new Thread(()->{
            //获取写锁
            lock.writeLock();
            //永远阻塞在此处，不释放写锁
            LockSupport.park();
        });
        t1.start();
        //保证 T1 获取写锁
        Thread.sleep(100);

        Thread t2 = new Thread(()->{
            //阻塞在悲观读锁
            lock.readLock();
        });
        t2.start();
        //保证 T2 阻塞在读锁
        Thread.sleep(100);
        //中断线程T2
        //会导致线程T2所在 cpu 飙升
        t2.interrupt();
        t2.join();
    }
    
}
