package com.netty.chapter2;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.util.CharsetUtil;

/**
 * Created with IntelliJ IDEA.
 * User: za-lvjian
 * Date: 2018/1/5 16:14
 * DESC:
 */
public class EchoServerHandler extends ChannelInboundHandlerAdapter {

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {


//        super.channelRead(ctx, msg);

        ByteBuf in = (ByteBuf) msg;

        System.out.println("Server received:" + in.toString(CharsetUtil.UTF_8));

        //需要将消息发送给客户端,write是异步的,在这个时间点上不会释放消息,消息在下面释放
        ctx.write(Unpooled.copiedBuffer("哈哈,你好客户端", CharsetUtil.UTF_8));
    }

    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
        System.out.println("channelReadComplete");
        //消息在调用writeAndFlush时释放
        ctx.writeAndFlush(Unpooled.EMPTY_BUFFER).addListener(ChannelFutureListener.CLOSE);
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
//        super.exceptionCaught(ctx, cause);
        cause.printStackTrace();
        ctx.close();
    }
}
