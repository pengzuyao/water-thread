package com.water.thread.wblClass05;

import com.google.common.collect.Lists;

import java.util.List;

/*
 * @Description:
 * @Author: pengzuyao
 * @Time: 2019/06/24
 */
public class C05Allocator01 {

    private C05Allocator01(){}

    private static class Inner{
        public static C05Allocator01 install = new C05Allocator01();
    }

    public static C05Allocator01 getInstance(){
        return Inner.install;
    }

    private List<Object> als = Lists.newArrayList();

    //一次性申请所有资源
    synchronized boolean apply(Object from , Object to){
        if (als.contains(from) || als.contains(to)){
            return false;
        }else {
            als.add(from);
            als.add(to);
        }
        return true;
    }

    //归还资源
    synchronized void free(Object from ,Object to){
        als.remove(from);
        als.remove(to);
    }

}
