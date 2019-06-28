package com.water.thread.wblClass32;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * @Description: Balking模式：业务逻辑的改变依赖于某个共享变量的状态，
 * 当状态满足某个条件时，执行某个业务逻辑（本质上就是一个多线程版的if）,
 * 对于这种问题，可以把对共享变量的改变单独抽离出来，通过一个方法改变共享变量的状态，从而与业务逻辑隔离。
 * @Author: pengzuyao
 * @Time: 2019/06/28
 */
public class AutoSaveEditor {

    /**
     * 文件是否被修改过
     */
    boolean changed = false;
    /**
     * 定时任务线程池
     */
    ScheduledExecutorService sec = Executors.newSingleThreadScheduledExecutor();
    /**
     * 定时执行自动保存
     */
    void startAutoSave(){
        sec.scheduleWithFixedDelay(()->{
            autoSave();
        } , 5 ,5 , TimeUnit.SECONDS);
    }
    //自动存盘操作
    void autoSave(){
        if (!changed){
            return;
        }
        changed = false;
        //执行存盘操作
        //省略且实现
        //this.execSave();
    }
    //编辑操作
    void edit(){
        //省略编辑逻辑

    }
    //balking模式经典实现(将业务逻辑与状态改变分离)
    void change(){
        synchronized (this){
            changed = true;
        }
    }
}
