package com.water.thread.wblClass05;

import com.many.thread.annotations.NotThreadSafe;

/**
 * Destription:
 * Author: pengzuyao
 * Time: 2019-06-24
 */
@NotThreadSafe(desc = "账户A账户B互相持有对方的锁，等待对方释放锁，导致永久阻塞的现象：死锁")
public class C05Account01 {

    private int balance;

    void transfer(C05Account01 target , int amt){
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
    }
}
