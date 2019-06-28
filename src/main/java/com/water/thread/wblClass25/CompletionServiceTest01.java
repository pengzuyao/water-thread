package com.water.thread.wblClass25;

import java.util.concurrent.*;

/**
 * Destription:使用阻塞队列优化询价并保存数据库过程
 * Author: pengzuyao
 * Time: 2019-06-26
 */
public class CompletionServiceTest01 {


    public static void main(String[] args) throws Exception {
        //创建线程池
        ExecutorService executor = Executors.newFixedThreadPool(3);
        //异步向电商 S1 询价
        Future<Integer> f1 = executor.submit(()->getPriceByS1());
        //异步向电商 S2 询价
        Future<Integer> f2 = executor.submit(()->getPriceByS1());
        //异步向电商 S3 询价
        Future<Integer> f3 = executor.submit(()->getPriceByS1());

        /*//获取电商 S1 报价并保存
        Integer r1 = f1.get();
        executor.execute(()->save(r1));
        //获取电商 S2 报价并保存
        Integer r2 = f1.get();
        executor.execute(()->save(r2));
        //获取电商 S3 报价并保存
        Integer r3 = f1.get();
        executor.execute(()->save(r3));*/


        //改良：创建阻塞队列
        BlockingQueue<Integer> bq = new LinkedBlockingQueue<>();
        //电商 S1 报价异步进入阻塞队列
        executor.execute(()-> {
            put(bq , f1);
        });
        executor.execute(()-> {
            put(bq , f2);
        });
        executor.execute(()-> {
            put(bq , f3);
        });

        for (int i = 0; i < 3; i++) {
            Integer r = bq.take();
            executor.execute(()->save(r));
        }

    }

    static void put( BlockingQueue<Integer> bq,Future<Integer> f){
        try {
            bq.put(f.get());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
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
