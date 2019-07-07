package com.water.thread.wblClass32;

/**
 * @Description:
 * @Author: pengzuyao
 * @Time: 2019/06/28
 */
public class InitTest {


    boolean inited = false;
    synchronized void init(){
        if (inited){
            return;
        }
        //省略 doInit 的实现
        //doInit()
        inited = true;
    }

}
