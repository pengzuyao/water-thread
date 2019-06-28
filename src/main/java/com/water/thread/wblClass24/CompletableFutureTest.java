package com.water.thread.wblClass24;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;

/**
 * @Description:用CompletableFuture实现烧水泡茶，将任务分成3个：
 * ①、洗水壶-》烧开水；
 * ②、洗茶壶-》洗茶杯-》拿茶叶；
 * ③、任务①和任务②完成后执行。
 * @Author: pengzuyao
 * @Time: 2019/06/26
 */
public class CompletableFutureTest {

    void sleep(int t , TimeUnit u){
        try {
            u.sleep(t);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }



    //洗水壶-》烧开水
    CompletableFuture<Void> f1 = CompletableFuture.runAsync(()->{
        System.out.println("T1:洗水壶。。。");
        sleep(1 , TimeUnit.SECONDS);
        System.out.println("T1:烧开水。。。");
        sleep(1 , TimeUnit.SECONDS);
    });

    //洗茶壶-》洗茶杯-》拿茶叶
    CompletableFuture<String> f2 = CompletableFuture.supplyAsync(()->{
        System.out.println("T2:洗茶壶。。。");
        sleep(1 , TimeUnit.SECONDS);
        System.out.println("T2:洗茶杯。。。");
        sleep(1 , TimeUnit.SECONDS);
        System.out.println("T2:拿茶叶。。。");
        sleep(1 , TimeUnit.SECONDS);
        return "龙井";
    });

    //任务①和任务②完成后执行
    CompletableFuture<String> f3 = f1.thenCombine(f2 ,(__ ,tf)->{
        System.out.println("T1:拿到茶叶"+ tf);
        System.out.println("T1:泡茶。。。");
        return "上茶：" + tf;
    });

    public static void main(String[] args) {
        /*CompletableFutureTest completableFutureTest = new CompletableFutureTest();
        System.out.println(completableFutureTest.f3.join());*/
        CompletableFuture<Integer> f0 = CompletableFuture.
                supplyAsync(()->7/0).
                thenApply(s->s *10).
                exceptionally(e -> 0);
        System.out.println(f0.join());
    }
}
