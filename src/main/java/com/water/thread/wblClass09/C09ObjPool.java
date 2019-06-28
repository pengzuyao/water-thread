package com.water.thread.wblClass09;

import java.util.List;
import java.util.Vector;
import java.util.concurrent.Semaphore;
import java.util.function.Function;

/**
 * @Description:宁姚喜欢之人，陈平安
 * @Author: pengzuyao
 * @Time: 2019/06/25
 */
public class C09ObjPool<T ,R> {

    final List<T> pool;
    /**
     * 用信号量实现限流器
     */
    final Semaphore sem;

    public C09ObjPool(int size , T t) {
        pool = new Vector<T>(){};
        for (int i = 0; i < size; i++) {
            pool.add(t);
        }
        sem = new Semaphore(size);
    }

    /**
     * 利用对象池的对象，调用 func
     */
    R exec(Function<T ,R> func){
        T t = null;
        try {
            sem.acquire();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        try {
            t = pool.remove(0);
            return func.apply(t);
        }finally {
            pool.add(t);
            sem.release();
        }
    }

    public static void main(String[] args) {
        //创建对象池
        C09ObjPool<Long , String> pool = new C09ObjPool<Long ,String>(10 , (long) 2);
        //通过对象池获取 t,之后执行
        pool.exec(t->{
            System.out.println(t);
            return t.toString();
        });
    }


}
