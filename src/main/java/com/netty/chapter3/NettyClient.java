package com.netty.chapter3;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.LengthFieldPrepender;
import io.netty.handler.codec.string.StringEncoder;

import java.net.InetSocketAddress;

/**
 * Created by PataPon on 2018/1/6.
 */
public class NettyClient {


    public static void main(String[] args) throws InterruptedException {

        Bootstrap client = new Bootstrap();
        /**
         *
         *
         * 1.绑定线程组,处理读写和连接事件
         * 2.绑定客户端通道
         * 3.绑定hander
         */

        NioEventLoopGroup group = new NioEventLoopGroup();
        client.group(group);

        client.channel(NioSocketChannel.class);

        //绑定handler
        client.handler(new ChannelInitializer<NioSocketChannel>() {
            @Override
            protected void initChannel(NioSocketChannel ch) throws Exception {

                ch.pipeline().addLast(new SimpleClientHandler());
//                ch.pipeline().addLast(new LengthFieldPrepender(4, false));
//                ch.pipeline().addLast(new StringEncoder());
            }
        });

        ChannelFuture future = client.connect(new InetSocketAddress("localhost", 8080));

        future.channel().writeAndFlush("你好,我是客户端".getBytes());

        future.channel().closeFuture().sync();

        System.out.println("客户端关闭");

    }

}
