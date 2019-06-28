package com.water.thread.wblClass03;

/**
 * Destription:
 * Author: pengzuyao
 * Time: 2019-06-23
 */
public class SafeCalc {

    long value = 0L;

    synchronized long get(){
        return value;
    }

    synchronized void addOne(){
        value += 1;
    }

}
