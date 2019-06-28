package com.water.thread.wblClass10;

import com.google.common.collect.Maps;
import com.many.thread.annotations.ThreadSafe;

import java.util.Map;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * @Description:
 * @Author: pengzuyao
 * @Time: 2019/06/25
 */
@ThreadSafe(desc = "ReadWriteLock不支持锁升级，支持锁降级")
public class Cache<K , V> {

    final Map<K ,V> m = Maps.newHashMap();
    final ReadWriteLock rwl = new ReentrantReadWriteLock();
    /**
     * 读锁
     */
    final Lock r = rwl.readLock();
    /**
     * 写锁
     */
    final Lock w = rwl.writeLock();
    /**
     * 读缓存
     */
    V get(K key){
        r.lock();
        try {
            return m.get(key);
        }finally {
            r.unlock();
        }
    }

    /**
     * 写缓存
     */
    V put(K key , V v){
        w.lock();
        try {
           return m.put(key ,v);
        }finally {
            w.unlock();
        }
    }
}
