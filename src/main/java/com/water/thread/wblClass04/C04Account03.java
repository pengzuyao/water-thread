package com.water.thread.wblClass04;


import com.water.thread.annotations.ThreadSafe;

/*
 * @Description:
 * @Author: pengzuyao
 * @Time: 2019/06/24
 */
@ThreadSafe(desc = "jvm加载的时候只会存在一个class文件，从而保证锁的唯一性,粗粒度锁，会影响性能")
public class C04Account03 {

    private int balance;
    //转账
    void transfer(C04Account03 target , int amt){
        synchronized (C04Account03.class){
            if (this.balance > amt){
                this.balance -= amt;
                target.balance += amt;
            }
        }
    }
}
