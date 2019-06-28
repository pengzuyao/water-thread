package com.water.thread.wblClass26;

import java.util.concurrent.RecursiveTask;

/**
 * @Description: 斐波那契数列
 * @Author: pengzuyao
 * @Time: 2019/06/27
 */
public class Fibonacci extends RecursiveTask<Integer> {

    final int n;

    public Fibonacci(int n){
        this.n = n;
    }

    @Override
    protected Integer compute() {
        if (n <= 2){
            return 1;
        }
        Fibonacci f1 = new Fibonacci(n-1);
        //创建子任务f1
        f1.fork();
        Fibonacci f2 = new Fibonacci(n-2);
        //创建子任务f2
        f2.fork();
        return f2.join() + f1.join();
    }
}
