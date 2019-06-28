package com.water.thread.wblClass08;



import com.water.thread.annotations.NotThreadSafe;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/*
 * @Description:
 * @Author: pengzuyao
 * @Time: 2019/06/25
 */
@NotThreadSafe(desc = "相互获取对方的锁失败，又相互释放锁，会造成无限加锁失败、释放锁的死循环。可通过增加一个随机时间，避免相互同时获取锁失败，同时释放锁的情况")
public class C08Account01 {

    private int balance;
    private final Lock lock = new ReentrantLock();

    //转账
    void transfer(C08Account01 target , int amt){
        while (true){
            if (this.lock.tryLock()){
                try {
                    if (target.lock.tryLock()){
                        try {
                            if (this.balance > amt){
                                this.balance -= amt;
                                target.balance += amt;
                            }
                        }finally {
                            target.lock.unlock();
                        }
                    }
                }finally {
                    this.lock.unlock();
                }
            }
        }
    }
}
