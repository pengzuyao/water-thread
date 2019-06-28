package com.water.thread.wblClass26;

import java.util.Map;
import java.util.concurrent.ForkJoinPool;

/**
 * @Description:
 * @Author: pengzuyao
 * @Time: 2019/06/27
 */
public class ForkJoinTest02 {

    public static void main(String[] args) {
        String[] fc = {"hello world" , "hello me" , "hello fork" , "hello join" , "fork join in world"};
        //创建 ForkJoin 线程池
        ForkJoinPool fjp = new ForkJoinPool(3);
        //创建任务
        MR mr = new MR(fc ,0 ,fc.length);
        //启动任务
        Map<String, Long> result = fjp.invoke(mr);
        //输出结果
        result.forEach((k , v)->{
            System.out.println(k + ":" + v);
        });
    }
}
