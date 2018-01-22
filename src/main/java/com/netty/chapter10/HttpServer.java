package com.netty.chapter10;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpRequestDecoder;
import io.netty.handler.codec.http.HttpResponseEncoder;

import java.net.InetSocketAddress;

/**
 * Created by PataPon on 2018/1/21.
 */
public class HttpServer {


    public static void main(String[] args) {


        ServerBootstrap bootstrap = new ServerBootstrap();

        int port = 6789;
        NioEventLoopGroup group = new NioEventLoopGroup();
        NioEventLoopGroup workers = new NioEventLoopGroup();
        try {

            bootstrap.group(group).channel(NioServerSocketChannel.class)
                    .childHandler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel ch) throws Exception {
                            ChannelPipeline pipeline = ch.pipeline();
                            pipeline.addLast("encoder", new HttpResponseEncoder());
                            pipeline.addLast("decoder", new HttpRequestDecoder());
                            pipeline.addLast("aggregator", new HttpObjectAggregator(10 * 1024 * 1024));
                            pipeline.addLast("myhandler", new NettyServerHandler());

                        }
                    });
//            // 服务器绑定端口监听
            ChannelFuture f = bootstrap.bind(new InetSocketAddress(port)).sync();
            System.out.println("服务端启动成功,端口是:" + port);
            // 监听服务器关闭监听
            f.channel().closeFuture().sync();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            group.shutdownGracefully(); //关闭EventLoopGroup，释放掉所有资源包括创建的线程
        }
    }
}
