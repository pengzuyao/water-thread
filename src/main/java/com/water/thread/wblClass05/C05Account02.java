package com.water.thread.wblClass05;


import com.water.thread.annotations.ThreadSafe;

/**
 * Destription:
 * Author: pengzuyao
 * Time: 2019-06-24
 */
@ThreadSafe(desc = "破坏占有且等待条件，一次性申请所有资源")
public class C05Account02 {

    private C05Allocator01 actr;
    private int balance;

    void transfer(C05Account02 target , int amt){
        //一次性申请转出账户和转入账户，直到成功
        while (!actr.apply(this ,target)){
            try {
                //锁定转出账户
                synchronized (this){
                    //锁定转入账户
                    synchronized (target){
                        if (this.balance > amt){
                            this.balance -= amt;
                            target.balance += amt;
                        }
                    }
                }
            }finally {
                actr.free(this ,target);
            }
        }
    }
}
