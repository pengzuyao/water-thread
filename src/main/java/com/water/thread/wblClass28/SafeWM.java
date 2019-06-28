package com.water.thread.wblClass28;

import java.util.concurrent.atomic.AtomicReference;

/**
 * @Description: 不可变类的三个要求：1、类和属性都是final的，所有方法均是只读的
 * @Author: pengzuyao
 * @Time: 2019/06/27
 */
public class SafeWM {

    class WMRange{
        final int upper;
        final int lower;
        WMRange(int upper , int lower){
            this.upper = upper;
            this.lower = lower;
        }
    }
    final AtomicReference<WMRange> rf = new AtomicReference<>(new WMRange(0 , 0));
    //设置库存上限
    void setUpper(int v){
       while (true){
           WMRange or = rf.get();
           //检查参数合法性
           if (v < or.lower){
               throw  new IllegalArgumentException();
           }
           WMRange nr = new WMRange(v , or.lower);
           if (rf.compareAndSet(or , nr)){
               return;
           }
       }
    }

}
