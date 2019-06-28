package com.water.thread.wblClass12;

import com.google.common.collect.Lists;

import java.util.List;

/**
 * Destription:
 * Author: pengzuyao
 * Time: 2019-06-25
 */
public class SafeArrayList<T> {

    //封装ArrayList
    List<T> c = Lists.newArrayList();
    //控制访问路径
    synchronized T get(int idx){
        return c.get(idx);
    }

    synchronized void add(int idx , T t){
        c.add(idx ,t);
    }

    synchronized boolean addIfNotExist(T t){
        if (!c.contains(t)){
            c.add(t);
            return true;
        }
        return false;
    }
}
