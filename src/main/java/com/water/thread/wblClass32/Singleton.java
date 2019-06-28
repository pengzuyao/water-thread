package com.water.thread.wblClass32;

/**
 * @Description:
 * @Author: pengzuyao
 * @Time: 2019/06/28
 */
public class Singleton {

    private static volatile Singleton singleton;
    private Singleton(){}
    public static Singleton getInstance(){
        if (singleton == null){
            synchronized (Singleton.class){
                if (singleton == null){
                    singleton = new Singleton();
                }
            }
        }
        return singleton;
    }

}
