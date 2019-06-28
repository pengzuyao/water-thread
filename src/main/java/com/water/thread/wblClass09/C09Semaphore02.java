package com.water.thread.wblClass09;

import java.util.concurrent.Semaphore;

/**
 * @Description:
 * @Author: pengzuyao
 * @Time: 2019/06/25
 */
public class C09Semaphore02 {

    //初始化信号量
    static int count;
    final static Semaphore s = new Semaphore(1);
    //用信号量保证互斥
    static void addOne(){
        try {
            s.acquire();
            count += 1;
        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally {
            s.release();
        }
    }
}
