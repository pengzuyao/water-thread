package com.water.thread.wblClass11;

import java.util.concurrent.locks.StampedLock;

/**
 * @Description:
 * @Author: pengzuyao
 * @Time: 2019/06/25
 */
public class Point {

    private int x ,y;
    final StampedLock s1 = new StampedLock();

    /**
     * 计算到原点的距离
     */
    double distanceFromOrigin(){
        //乐观读
        long stamp = s1.tryOptimisticRead();
        //读入局部变量
        //读的过程数据可能被修改
        int curX = x , curY = y;
        //判断读操作期间
        //判断是否存在写操作，如果存在
        //则s1.validate 返回 false
        if (!s1.validate(stamp)){
            //升级为悲观读锁
            stamp = s1.readLock();
            try {
                curX = x;
                curY = y;
            }finally {
                //释放悲观读锁
                s1.unlockRead(stamp);
            }
        }
        return Math.sqrt(curX * curY +curY * curY);
    }
}
