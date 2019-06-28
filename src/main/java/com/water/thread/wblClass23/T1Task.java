package com.water.thread.wblClass23;

import java.util.concurrent.Callable;
import java.util.concurrent.FutureTask;
import java.util.concurrent.TimeUnit;

/**
 * @Description:T1Task需要执行的任务：洗水壶、烧水、泡茶
 * @Author: pengzuyao
 * @Time: 2019/06/26
 */
public class T1Task implements Callable<String> {

    FutureTask<String> ft2;

    //T1 任务需要 T2 任务的 FutureTask
    public T1Task(FutureTask<String> ft2){
        this.ft2 = ft2;
    }

    @Override
    public String call() throws Exception {
        System.out.println("T1:洗水壶。。。");
        TimeUnit.SECONDS.sleep(1);
        System.out.println("T1:烧开水。。。");
        TimeUnit.SECONDS.sleep(1);
        //获取 T2 线程的茶叶
        String tf = ft2.get();
        System.out.println("T1:拿到茶叶：" + tf);
        System.out.println("T1:泡茶。。。");
        return "上茶:" + tf;
    }
}
