package com.netty.chapter2;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;

import java.net.InetSocketAddress;

/**
 * Created with IntelliJ IDEA.
 * User: za-lvjian
 * Date: 2018/1/5 16:28
 * DESC:
 */
public class EchoServer {


    private final int port;

    public EchoServer(int port) {
        this.port = port;
    }

    public static void main(String[] args) throws InterruptedException {
        int port = 8888;

        new EchoServer(port).start();
    }


    public void start() throws InterruptedException {
        NioEventLoopGroup group = new NioEventLoopGroup();
        try {
            EchoServerHandler handler = new EchoServerHandler();


            ServerBootstrap bootstrap = new ServerBootstrap();


            bootstrap.group(group).channel(NioServerSocketChannel.class)
                    .localAddress(new InetSocketAddress(port))
                    .childHandler(new ChannelInitializer<SocketChannel>() {
                        //每当一个新的连接被接受,就会创建一个channel,channelInitializer会把
                        //会把handler添加到该channel的channelPipeLine中


                        @Override
                        protected void initChannel(SocketChannel ch) throws Exception {
                            ch.pipeline().addLast(handler);
                        }
                    });


            ChannelFuture future = bootstrap.bind().sync();//异步绑定服务器 调用sync()方法阻塞等待直到绑定完成

            future.channel().closeFuture().sync();  //获取到channel的closeFuture,并且阻塞当前线程直到完成

        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            group.shutdownGracefully().sync();
        }
    }
}
