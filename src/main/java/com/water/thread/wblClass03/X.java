package com.water.thread.wblClass03;

/**
 * Destription:
 * Author: pengzuyao
 * Time: 2019-06-23
 */
public class X {

    //修饰非静态方法
    synchronized void foo(){
        //临界区
    }

    //修饰静态方法
    synchronized static void bar(){
        //非临界区
    }

    //修饰代码块
    Object obj = new Object();
    void baz(){
        synchronized (obj){
            //临界区
        }
    }
}
