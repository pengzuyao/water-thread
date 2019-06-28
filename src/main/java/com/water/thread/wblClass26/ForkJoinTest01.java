package com.water.thread.wblClass26;

import java.util.concurrent.ForkJoinPool;

/**
 * @Description: 任务分治/归并
 * @Author: pengzuyao
 * @Time: 2019/06/27
 */
public class ForkJoinTest01 {

    public static void main(String[] args) {
        //创建分治任务线程池
        ForkJoinPool fjp = new ForkJoinPool(3);
        //创建分治任务
        Fibonacci fib = new Fibonacci(5);
        //启动分治任务
        Integer result = fjp.invoke(fib);
        System.out.println(result);
    }
}
