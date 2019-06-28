package com.water.thread.wblClass35;

/**
 * @Description: 休眠状态：BLOCKED/WAITING/TIMED_WAITING ,
 * 调用线程的interrupt()方法，会将线程从休眠状态转换到RUNABLE状态。
 * @Author: pengzuyao
 * @Time: 2019/06/28
 */
public class Proxy {

    boolean started = false;
    /**
     * 采集线程
     */
    Thread rptThread;
    /**
     * 启动采集功能
     */
    synchronized void start(){
        //不允许同时启动多个采集线程
        if (started){
            return;
        }
        started = true;
        rptThread = new Thread(()->{
            while (!Thread.currentThread().isInterrupted()){
                //省略采集、会传实现
                //report();
                //每隔两秒钟采集、回传一次数据
                try {
                    Thread.sleep(2000);
                }catch (InterruptedException e){
                    //重新设置线程的中断状态
                    Thread.currentThread().isInterrupted();
                }
            }
            //执行到此处说明线程马上终止
            started = false;
        });
        rptThread.start();
    }
    //终止采集功能
    synchronized void stop(){
        //如何实现？
        rptThread.isInterrupted();
    }
}
