package com.netty.chapter2;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;

import java.net.InetSocketAddress;

/**
 * Created by PataPon on 2018/1/7.
 */
public class EchoClient {

    private final String host;
    private final int port;


    public EchoClient(String host, int port) {
        this.host = host;
        this.port = port;
    }

    public void start() throws InterruptedException {


        Bootstrap bootstrap = new Bootstrap();


        NioEventLoopGroup group = new NioEventLoopGroup();
        //绑定线程处理客户端的连接和读写事件
        bootstrap.group(group);

        //绑定通道
        bootstrap.channel(NioSocketChannel.class);

        //绑定Handler处理事件

        bootstrap.handler(new ChannelInitializer<SocketChannel>() {
            @Override
            protected void initChannel(SocketChannel ch) throws Exception {
                ch.pipeline().addLast(new EchoClientHandler());
            }
        });

        //阻塞直到连接完成
        ChannelFuture future = bootstrap.remoteAddress(new InetSocketAddress(host, port)).connect().sync();

        //阻塞直到channel关闭
        future.channel().closeFuture().sync();

        //关闭线程池 并且释放所有资源
        group.shutdownGracefully().sync();

    }

    public static void main(String[] args) throws InterruptedException {
        new EchoClient("localhost",8888).start();
    }

}
