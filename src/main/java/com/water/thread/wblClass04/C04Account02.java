package com.water.thread.wblClass04;


import com.water.thread.annotations.ThreadSafe;

/*
 * @Description:
 * @Author: pengzuyao
 * @Time: 2019/06/24
 */
@ThreadSafe(desc = "创建Account时需要传入同一个lock对象，否则线程会不安全")
public class C04Account02 {

    private Object lock;
    private int balance;

    private C04Account02(){};

    //创建Account时传入同一个lock对象
    public C04Account02(Object lock){
        this.lock = lock;
    }

    //转账
    void transfer(C04Account02 target , int amt){
        //此处检查所有对象共享的锁
        synchronized (lock){
            if (this.balance > amt){
                this.balance -= amt;
                target.balance += amt;
            }
        }
    }
}
