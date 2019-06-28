package com.water.thread.wblClass09;

import java.util.Queue;

/**
 * @Description:
 * @Author: pengzuyao
 * @Time: 2019/06/25
 */
public class C09Semaphore01 {

    /**
     * 计数器
     */
    int count;
    /**
     * 等待队列
     */
    Queue queue;
    /**
     * 初始化操作
     */
    C09Semaphore01(int c){
        this.count = c;
    }

    void down(){
        this.count--;
        if (this.count < 0){
            //将当前线程插入等待队列
            //阻塞当前线程
        }
    }

    void up(){
        this.count++;
        if (this.count <= 0){
            //移除等待队列中的某个线程
            //唤醒线程
        }
    }
}
