package com.water.thread.wblClass05;


import com.water.thread.annotations.ThreadSafe;

/**
 * Destription:
 * Author: pengzuyao
 * Time: 2019-06-24
 */
@ThreadSafe(desc = "破坏循环等待条件：对资源进行排序，然后按序申请资源")
public class C05Account03 {

    private int id ;
    private int balance;
    //转账
    void transfer(C05Account03 target , int amt){
        C05Account03 left = this;
        C05Account03 right = target;
        if (this.id > target.id){
            left = target;
            right = this;
        }
        //锁定序号小的账户
        synchronized (left){
            //锁定序号大的账户
            synchronized (right){
                if (this.balance > amt){
                    this.balance -= amt;
                    target.balance += amt;
                }
            }
        }
    }

}
