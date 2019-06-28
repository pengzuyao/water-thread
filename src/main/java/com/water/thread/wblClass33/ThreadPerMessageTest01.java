package com.water.thread.wblClass33;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;

/**
 * @Description: Thread-Per-Message 模式：解决宏观的分工问题
 * @Author: pengzuyao
 * @Time: 2019/06/28
 */
public class ThreadPerMessageTest01 {


    public static void main(String[] args) throws IOException {
        final ServerSocketChannel ssc = ServerSocketChannel.open().bind(new InetSocketAddress(8080));
        try {
            //每个请求都创建一个线程
            new Thread(() -> {
                while (true) {
                    try {
                        SocketChannel sc = ssc.accept();
                        // 读 socket
                        ByteBuffer rb = ByteBuffer.allocateDirect(1024);
                        sc.read(rb);
                        //模拟处理请求
                        Thread.sleep(2000);
                        //写 Socket
                        ByteBuffer wb = (ByteBuffer) rb.flip();
                        sc.write(wb);
                        sc.close();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }).start();
        }finally {
            ssc.close();
        }
    }

}
