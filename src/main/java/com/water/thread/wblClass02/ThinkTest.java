package com.water.thread.wblClass02;

/**
 * Destription:
 * Author: pengzuyao
 * Time: 2019-06-23
 */
public class ThinkTest {

    public static int abc = 0;

    public static void set(){
        abc = 3;
    }

    /**
     * 1、使用volatile关键字
     * 2、使用synchronized对代码块的赋值进行加锁
     * 3、使用当前线程的join(),执行完后对其它线程可见（串行化）
     * 4、使用原子变量：Atomicinteger
     */

}
