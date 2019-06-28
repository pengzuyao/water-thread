package com.water.thread.wblClass02;

/**
 * Destription:
 * Author: pengzuyao
 * Time: 2019-06-23
 */
public class VolatileExample {

    int x = 0 ;
    volatile  boolean v = false;
    public void writer(){
        x = 42;
        v = true;
    }

    public void reader(){
        if (v == true){
            System.out.println(x);
        }
    }

    public static void main(String[] args) {
        for (int i = 0; i < 1000000; i++) {
            VolatileExample volatileExample = new VolatileExample();
            volatileExample.writer();
            volatileExample.reader();
        }
    }

}
