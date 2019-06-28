package com.water.thread.wblClass01;


/**
 * Destription:
 * Author: pengzuyao
 * Time: 2019-06-21
 */
public class Test01 {

    private static long count = 0;
    private void add10k(){
        int idx = 0;
        while (idx++ < 10000){
            count ++;
        }
    }

    public static long calc() throws InterruptedException {
        final Test01 test01 = new Test01();
        Thread th1 = new Thread(()->{
            test01.add10k();
        });

        Thread th2 = new Thread(()->{
            test01.add10k();
        });
        th1.start();
        th2.start();
        th1.join();
        th2.join();
        return  count;
    }


    public static void main(String[] args) throws InterruptedException {
        System.out.println(Test01.calc());
    }
}
