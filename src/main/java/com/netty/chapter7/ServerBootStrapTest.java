package com.netty.chapter7;

import io.netty.bootstrap.Bootstrap;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.oio.OioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.channel.socket.oio.OioDatagramChannel;
import io.netty.util.AttributeKey;
import org.junit.Test;

import java.io.IOException;
import java.net.InetSocketAddress;

/**
 * Created by PataPon on 2018/1/17.
 */
public class ServerBootStrapTest {


    @Test
    public void method() {

        NioEventLoopGroup eventLoopGroup = new NioEventLoopGroup();
        ServerBootstrap serverBootstrap = new ServerBootstrap();
        serverBootstrap.group(eventLoopGroup, new NioEventLoopGroup())
                .channel(NioServerSocketChannel.class)
                .childHandler(new SimpleChannelInboundHandler<ByteBuf>() {


                    @Override
                    public void channelActive(ChannelHandlerContext ctx) throws Exception {
                        Bootstrap bootstrap = new Bootstrap();


                        //一旦channel被注册到eventLoopGroup上面,就会调用initChannel方法,在这个方法返回之后,channelInitializer将自己从
                        //pipeline中移除
                        bootstrap.channel(NioSocketChannel.class).handler(new ChannelInitializer<SocketChannel>() {
                            @Override
                            protected void initChannel(SocketChannel ch) throws Exception {

                            }
                        });

                        bootstrap.group(ctx.channel().eventLoop());

                        bootstrap.connect(new InetSocketAddress("wwww.baidu.com", 80));
                    }

                    @Override
                    protected void channelRead0(ChannelHandlerContext ctx, ByteBuf msg) throws Exception {

                    }
                });

        ChannelFuture future = serverBootstrap.bind(new InetSocketAddress(8080));
        future.addListener(new ChannelFutureListener() {
            @Override
            public void operationComplete(ChannelFuture future) throws Exception {
                if (future.isSuccess()) {

                    System.out.println("Server bound");
                } else {

                    System.out.println("bound attempt failed");
                    future.cause().printStackTrace();
                }
            }
        });


    }

    @Test
    public void testAttr() {


        AttributeKey<Integer> id = AttributeKey.valueOf("id");

        Bootstrap bootstrap = new Bootstrap();

        bootstrap.group(new NioEventLoopGroup()).channel(NioSocketChannel.class)
                .handler(new SimpleChannelInboundHandler<ByteBuf>() {
                    @Override
                    public void channelRegistered(ChannelHandlerContext ctx) throws Exception {
                        System.out.println("获得属性值是" + ctx.channel().attr(id).get());
                    }

                    @Override
                    protected void channelRead0(ChannelHandlerContext ctx, ByteBuf msg) throws Exception {

                    }
                });

        bootstrap.option(ChannelOption.SO_KEEPALIVE, true)
                .option(ChannelOption.CONNECT_TIMEOUT_MILLIS, 5000);

        bootstrap.attr(id, 123456);

        ChannelFuture future = bootstrap.connect(new InetSocketAddress("wwww.baidu.com", 80));

        future.addListener(new ChannelFutureListener() {
            @Override
            public void operationComplete(ChannelFuture future) throws Exception {
                if (future.isSuccess()) {

                    System.out.println("连接成功");
                } else {
                    System.out.println("连接失败");
                }
            }
        });

    }

    @Test
    public void testDataGram() throws IOException {

        Bootstrap bootstrap = new Bootstrap();

        bootstrap.group(new OioEventLoopGroup())
                .channel(OioDatagramChannel.class)
                .handler(new SimpleChannelInboundHandler<ByteBuf>() {
                    @Override
                    protected void channelRead0(ChannelHandlerContext ctx, ByteBuf msg) throws Exception {

                    }
                });

        ChannelFuture channelFuture = bootstrap.bind(new InetSocketAddress(0));

        channelFuture.addListener(new ChannelFutureListener() {
            @Override
            public void operationComplete(ChannelFuture future) throws Exception {

                if(future.isSuccess()){

                    System.out.println("bind success");
                }else{

                    System.out.println("attempt failed");
                }
            }
        });

        System.in.read();

    }
}
