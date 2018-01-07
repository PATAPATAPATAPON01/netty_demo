package com.netty.chapter3;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.LengthFieldBasedFrameDecoder;
import io.netty.handler.codec.LengthFieldPrepender;
import io.netty.handler.codec.string.StringEncoder;

/**
 * Created by PataPon on 2018/1/6.
 */
public class NettyServer {


    public static void main(String[] args) throws InterruptedException {


        ServerBootstrap bootstrap = new ServerBootstrap();

        /**
         * 1.绑定两个线程组分别用来处理客户端的accept和读写事件
         * 2.绑定服务端通道NIOServerSocketChannel
         * 3.给读写事件绑定Handler去真正处理
         * 4.监听端口
         *
         */


        /**
         *  parentGroup用来处理accept
         *  childGroup用来处理读写事件
         */

        EventLoopGroup parentGroup = new NioEventLoopGroup();
        EventLoopGroup childGroup = new NioEventLoopGroup();
        bootstrap.group(parentGroup, childGroup);

        bootstrap.channel(NioServerSocketChannel.class);

        bootstrap.childHandler(new ChannelInitializer<SocketChannel>() {

            @Override
            protected void initChannel(SocketChannel ch) throws Exception {

//                ch.pipeline().addLast(new LengthFieldBasedFrameDecoder(Integer.MAX_VALUE,0,4,-1,4));
                ch.pipeline().addLast(new SimpleServerHandler());
//                ch.pipeline().addLast(new LengthFieldPrepender(4,false  ));
//                ch.pipeline().addLast(new StringEncoder());

            }
        });


        ChannelFuture channelFuture = bootstrap.bind(8080).sync();


        channelFuture.channel().closeFuture().sync(); //通道关闭 才继续向下走

    }


}
