package com.water.thread.wblClass01;

/**
 * Destription:
 * Author: pengzuyao
 * Time: 2019-06-23
 */
public class Test02 {

    private static Test02 instance = null ;

    private Test02(){}

    public  static  Test02 getInstance(){
        if (instance == null){
            synchronized (Test02.class){
                if (instance == null){
                    instance = new Test02();
                }
            }
        }
        return instance ;
    }


    /**
     * 1、理想情况：分配一块内存M;
     * 2、在内存M上初始化Test02对象
     * 3、然后M的地址复值给instance变量
     */
}
