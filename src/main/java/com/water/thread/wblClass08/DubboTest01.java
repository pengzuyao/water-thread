package com.water.thread.wblClass08;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @Description:
 * @Author: pengzuyao
 * @Time: 2019/06/25
 */
public class DubboTest01 {

    //创建锁与条件变量
    private final Lock lock = new ReentrantLock();
    private final Condition done = lock.newCondition();

    //调用通过该方法等待结果
    Object get(long timeout) {
        long start = System.nanoTime();
        lock.lock();
        try {
            while (!isDone()){
                try {
                    done.await(timeout , TimeUnit.SECONDS);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                long cur = System.nanoTime();
                if (isDone() || cur-start > timeout){
                    break;
                }
            }
        }finally {
            lock.unlock();
        }
        return null;
    }

    private boolean isDone() {
        return false;
    }


}
