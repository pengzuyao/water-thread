package com.water.thread.wblClass06;



import com.water.thread.annotations.ThreadSafe;

import java.util.List;

/**
 * Destription:
 * Author: pengzuyao
 * Time: 2019-06-24
 */
@ThreadSafe(desc = "完美的等待通知机制")
public class C06Allocator01 {

    private List<Object> als;
    //一次性申请所有资源
    synchronized void apply(Object from , Object to) throws InterruptedException {
        //经典写法
        while (als.contains(from)|| als.contains(to)){
            try {
                wait();
            }catch (Exception e){
            }
        }
        als.add(from);
        als.add(to);
    }

    //归还资源
    synchronized void free(Object from , Object to){
        als.remove(from);
        als.remove(to);
        notifyAll();
    }
}
