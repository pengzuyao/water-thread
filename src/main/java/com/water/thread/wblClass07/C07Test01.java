package com.water.thread.wblClass07;

import com.many.thread.annotations.NotThreadSafe;

/**
 * Destription:
 * Author: pengzuyao
 * Time: 2019-06-24
 */
@NotThreadSafe(desc = "存在竞态条件：程序的执行结果依赖线程执行的顺序")
public class C07Test01 {

    private long count = 0;
    synchronized long get(){
        return count;
    }

    synchronized void set(long v){
        count = v;
    }

    void add10K(){
        int idx = 0;
        while (idx ++ < 10000){
            set(get() + 1);
        }
    }
}
