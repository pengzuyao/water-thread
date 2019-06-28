package com.water.thread.wblClass04;

import com.many.thread.annotations.ThreadSafe;


/*
 * @Description:
 * @Author: pengzuyao
 * @Time: 2019/06/24
 */
@ThreadSafe(desc = "不同的资源用不同的锁保护，各自管各自的，很简单，细粒度锁")
public class C04Account01 {

    //锁：保护账户余额
    private final Object balLock = new Object();
    //账户余额
    private Integer balance;
    //锁：保护账户密码
    private final Object pwLock = new Object();
    //账户密码
    private String password;

    //取款
    void withdraw(Integer amt){
        synchronized (balLock){
            if (this.balance > amt){
                this.balance -= amt;
            }
        }
    }

    //查看余额
    Integer getBalance(){
        synchronized (balLock){
            return balance;
        }
    }

    //更改密码
    void updatePassword(String pw){
        synchronized (pwLock){
            this.password = pw;
        }
    }

    //查看密码
    String getPassword(){
        synchronized (pwLock){
            return password;
        }
    }
}
