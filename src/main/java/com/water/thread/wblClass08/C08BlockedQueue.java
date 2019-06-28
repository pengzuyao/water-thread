package com.water.thread.wblClass08;



import com.water.thread.annotations.ThreadSafe;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Destription:
 * Author: pengzuyao
 * Time: 2019-06-24
 */
@ThreadSafe
public class C08BlockedQueue<T> {

    /**
     * 锁对象
     */
    final Lock lock = new ReentrantLock();

    /**
     * 条件变量:队列不满
     */
    final Condition notFull = lock.newCondition();

    /**
     * 条件变量：队列不空
     */
    final Condition notEmpty =lock.newCondition();

    /**
     * 初始化一个长度为100的队列
     */
    final Object[] items = new Object[100];

    /**
     * 写索引，读索引，队列中存在的数据个数
     */
    int putptr ,takeptr ,count;


    /**
     * 入队
     * @param x
     * @throws InterruptedException
     */
    void enq(T x) throws InterruptedException {
        lock.lock();
        try {
            //队列已满
            while (count == items.length){
                //等待队列不满
                notFull.await();
            }
            //入队操作
            items[count] = x;
            count++;
            //入队后,通知可出队
            notEmpty.signal();
        }finally {
            lock.unlock();
        }
    }

    /**
     * 出队
     * @throws InterruptedException
     */
    void deq() throws InterruptedException {
        lock.lock();
        try {
            //队列已空
            while (count == 0){
                //等待队列不空
                notEmpty.await();
            }
            //出队操作
           items[count] = null;
            //出队后，通知可入队
            notFull.signal();
        }finally {
            lock.unlock();
        }
    }

    /**
     * hasen:执行完，再去唤醒另一个线程，能够保证线程的执行。
     * hoare:中断当前线程，唤醒另外一个线程，执行完再去唤醒，也能保证完成。
     * mesa:进入等待队列，不一定有机会能够执行。
     */
}
