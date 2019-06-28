package com.water.thread.wblClass22;

import com.sun.istack.internal.NotNull;

import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @Description:
 * @Author: pengzuyao
 * @Time: 2019/06/26
 */
public class MyThreadFactory implements ThreadFactory {

    /**
     * 线程池编号(static修饰)，容器里面所有线程池的数量
     */
    private  final static AtomicInteger POOLNUMBER = new AtomicInteger(1);

    /**
     * 线程编号(当前线程池线程的数量)
     */
    private final AtomicInteger threadNumber = new AtomicInteger(1);

    /**
     * 线程组
     */
    private final ThreadGroup group;

    /**
     * 业务名称前缀
     */
    private final String namePrefix;

    public MyThreadFactory(@NotNull String prefix){
        SecurityManager s= System.getSecurityManager();
        group = (s != null)? s.getThreadGroup() : Thread.currentThread().getThreadGroup();
        //组装线程前缀
        namePrefix = prefix +"_poolNumber:"+POOLNUMBER.getAndIncrement() + "_threadNumber:";
    }

    @Override
    public Thread newThread(Runnable r) {
        Thread t = new Thread(group , r ,namePrefix + threadNumber.getAndIncrement() ,0);
        if (t.isDaemon()){
            t.setDaemon(false);
        }
        if (t.getPriority() != Thread.NORM_PRIORITY){
            t.setPriority(Thread.NORM_PRIORITY);
        }
        return t;
    }
}
