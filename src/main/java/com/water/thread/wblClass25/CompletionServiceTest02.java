package com.water.thread.wblClass25;

import java.util.concurrent.*;

/**
 * Destription: CompletionService内部维护了一个阻塞队列，
 * 异步执行多个任务获取返回值保存数据时，可以从阻塞队列中快速按序获取异步返回结果值
 * Author: pengzuyao
 * Time: 2019-06-26
 */
public class CompletionServiceTest02 {

    public static void main(String[] args) throws Exception {

        //创建线程池
        ExecutorService executor = Executors.newFixedThreadPool(3);
        //创建CompletionService
        CompletionService<Integer> cs = new ExecutorCompletionService<>(executor);
        //异步向电商 S1 询价
        cs.submit(()->getPriceByS1());
        //异步向电商 S2 询价
        cs.submit(()->getPriceByS2());
        //异步向电商 S3 询价
        cs.submit(()->getPriceByS3());
        //将询价结果异步保存到数据库
        for (int i = 0; i < 3; i++) {
            Integer r = cs.take().get();
            executor.execute(()->save(r));
        }
    }


    static Integer getPriceByS1(){
        return null;
    }

    static Integer getPriceByS2(){
        return null;
    }

    static Integer getPriceByS3(){
        return null;
    }

    static void save(Integer r){}
}
