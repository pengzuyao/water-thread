package com.water.thread.wblClass04;

import com.many.thread.annotations.NotThreadSafe;
import com.many.thread.annotations.ThreadSafe;

/*
 * @Description:
 * @Author: pengzuyao
 * @Time: 2019/06/24
 */
@NotThreadSafe(desc = "balance、password 都是不可变对象，一旦对他们赋值就会变成新的对象，加的锁就失效了")
public class C04Account04 {

    //账户余额
    private Integer balance;
    //账户密码
    private String password;

    //取款
    void withdraw(Integer amt){
        synchronized (balance){
            if (this.balance > amt){
                this.balance -= amt;
            }
        }
    }

    //查看余额
    Integer getBalance(){
        synchronized (this.balance){
            return balance;
        }
    }

    //更改密码
    void updatePassword(String pw){
        synchronized (this.password){
            this.password = pw;
        }
    }

    //查看密码
    String getPassword(){
        synchronized (this.password){
            return password;
        }
    }
}
