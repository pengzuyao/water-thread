package com.water.thread.wblClass22;

/**
 * @Description: 采用一般意义上的池化资源的设计方法
 * @Author: pengzuyao
 * @Time: 2019/06/26
 */
public class ThreadPool {

    /**
     * 获取空闲线程
     * @return
     */
    Thread acquire(){
      return null;
    };

    void release(Thread t){}


    //期望的使用
    /*ThreadPool pool ;
    Thread t1 = pool.acquire();
    //传入 Runable 对象
        t1.execute(()->{
        //具体业务逻辑
            ......
    });*/

}
