package com.water.thread.wblClass39;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.sctp.nio.NioSctpServerChannel;
import io.netty.channel.socket.SocketChannel;

/**
 * Destription:
 * Author: pengzuyao
 * Time: 2019-06-30
 */
public class NettyTest01 {


    //socket 连接处理器
    public class EchoServerHandler extends ChannelInboundHandlerAdapter {

        //处理读事件
        @Override
        public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
            ctx.write(msg);
        }

        //处理读完成事件
        @Override
        public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
            ctx.flush();
        }

        //处理异常事件
        @Override
        public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
            ctx.close();
        }
    }

    public static void main(String[] args) throws InterruptedException {

        NettyTest01 nettyTest01 = new NettyTest01();
        //事件处理器
        final EchoServerHandler serverHandler = nettyTest01.new EchoServerHandler();
        //boss 线程组
        EventLoopGroup bossGroup = new NioEventLoopGroup(1);
        //worker 线程组
        EventLoopGroup workerGroup = new NioEventLoopGroup();
        try {
            ServerBootstrap b = new ServerBootstrap();
            b.group(bossGroup , workerGroup).channel(NioSctpServerChannel.class).childHandler(new ChannelInitializer<SocketChannel>() {

                @Override
                protected void initChannel(SocketChannel socketChannel) throws Exception {
                    socketChannel.pipeline().addLast(serverHandler);
                }
            });
           //bind 服务端端口
            ChannelFuture f = b.bind(9090).sync();
            f.channel().closeFuture().sync();
        }finally {
            //终止 boss 线程组
            bossGroup.shutdownGracefully();
            //终止 boss 线程组
            workerGroup.shutdownGracefully();
        }

    }

}
