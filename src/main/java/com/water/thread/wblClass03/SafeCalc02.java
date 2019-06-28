package com.water.thread.wblClass03;


import com.water.thread.annotations.NotThreadSafe;

/**
 * Destription:
 * Author: pengzuyao
 * Time: 2019-06-23
 */
@NotThreadSafe
public class SafeCalc02 {

    long value = 0L;

    long get(){
        synchronized (new Object()){
            return value;
        }
    }

    void addOne(){
        synchronized (new Object()){
            value += 1;
        }
    }
}
