package com.water.thread.wblClass34;

import com.many.thread.annotations.NotThreadSafe;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @Description:
 * @Author: pengzuyao
 * @Time: 2019/06/28
 */
@NotThreadSafe(desc = "外部线程会由于内部线程 submit.get 而阻塞，" +
        "占有single线程池的唯一worker资源，从而导致内部线程永远无法执行，形成活锁。解决方案：拆分成两个线程池")
public class WorkThreadTest02 {

    public static void main(String[] args) {
        ExecutorService pool = Executors.newSingleThreadScheduledExecutor();
        pool.submit(()->{
           try {
               String qq = pool.submit(() -> "QQ").get();
               System.out.println(qq);
           }catch (Exception e){
               e.printStackTrace();
           }
        });
    }
}
