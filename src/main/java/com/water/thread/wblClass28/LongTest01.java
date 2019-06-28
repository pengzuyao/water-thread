package com.water.thread.wblClass28;

/**
 * @Description:将使用频率最高的数据做缓存
 * @Author: pengzuyao
 * @Time: 2019/06/27
 */
public class LongTest01 {

    Long valueOf(long l){
        final int offset = 128;
        //[-128 ,127]直接的数字做了缓存
        if (l >= -128 && l <= 127 ){
            return LongCache.cache[(int)l + offset];
        }
        return new Long(l);
    }

    //缓存等价于对象池
    //仅缓存[-128 ,127] 直接的数字
    static class LongCache{
        static final Long[] cache = new Long[-(-128) + 127 + 1];
        static {
            for (int i = 0 ; i < cache.length ; i++){
                cache[i] = new Long(i-128);
            }
        }
    }


    /**
     * 基本类型的包装类不适合做锁，他们类部用到了享元模式，导致看上去是私有的锁，其实是公有的锁
     */
    class A {
        Long a1 = Long.valueOf(1);
        public void setA1(){
            synchronized (a1){
               //省略代码无数
            }
        }
    }

    class B{
        Long b1 = Long.valueOf(1);
        public void setB1(){
            synchronized (b1){
                //省略代码无数
            }
        }
    }
}
