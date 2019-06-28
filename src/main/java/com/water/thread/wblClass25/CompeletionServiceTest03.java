package com.water.thread.wblClass25;

import com.google.common.collect.Lists;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

/**
 * Destription: 当需要批量提交异步任务的时候建议你使用CompletionService,能够让批量异步任务的管理更简单
 * Author: pengzuyao
 * Time: 2019-06-26
 */
public class CompeletionServiceTest03 {

    public static Integer test() {
        //创建线程池
        ExecutorService executor = Executors.newFixedThreadPool(3);
        //创建CompletionService
        CompletionService<Integer> cs = new ExecutorCompletionService<>(executor);
        //用于保存Future 对象
        List<Future<Integer>> futures = new ArrayList<>(3);
        //提交异步任务，并报存 future 到 futures
        futures.add(cs.submit(()->getPriceByS1()));
        futures.add(cs.submit(()->getPriceByS2()));
        futures.add(cs.submit(()->getPriceByS3()));
        //获取最快返回的任务执行结果
        Integer r = null;
        try {
            //只要有一个成功返回 ，则break
            for (int i = 0; i < 3; i++) {
                r = cs.take().get();
                //简答的通过判空来检查是否成功返回
                if (r != null){
                    break;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            //取消所有任务
            for (Future<Integer> future : futures) {
                future.cancel(true);
            }
        }
        return r;
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

}
