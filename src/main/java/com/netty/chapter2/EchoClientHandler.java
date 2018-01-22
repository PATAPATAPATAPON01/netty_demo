package com.netty.chapter2;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.util.CharsetUtil;
import io.netty.util.ReferenceCountUtil;
import io.netty.util.ReferenceCounted;

/**
 * Created with IntelliJ IDEA.
 * User: za-lvjian
 * Date: 2018/1/5 16:44
 * DESC:  SimpleChannelInboundHandler负责释放保存该消息的bytebuf的内存引用
 */
public class EchoClientHandler extends SimpleChannelInboundHandler<ByteBuf> {

    /*
     每当接受数据时,都会调用这个方法
     */
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, ByteBuf msg) throws Exception {

        System.out.println("channelRead0 Client received: " + msg.toString(CharsetUtil.UTF_8));

        ReferenceCountUtil.release(msg); //显示释放ByteBuf实例的内存

    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        super.channelInactive(ctx);
        System.out.println("调用了channelInActive");
    }

    @Override
    public void channelRegistered(ChannelHandlerContext ctx) throws Exception {
        super.channelRegistered(ctx);
        System.out.println("调用了chanelRegistred");
    }

    @Override
    public void channelUnregistered(ChannelHandlerContext ctx) throws Exception {
        super.channelUnregistered(ctx);
        System.out.println("调用了channelUnregistered");

    }

    @Override
    public void handlerAdded(ChannelHandlerContext ctx) throws Exception {
        super.handlerAdded(ctx);
        System.out.println("调用了handlerAdded");
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception { //在到服务器的连接建立之后调用

        //当通道是活动的时候发送一条消息
        ctx.writeAndFlush(Unpooled.copiedBuffer("Netty rocks!", CharsetUtil.UTF_8));

        System.out.println("调用了channelActive");
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {

        System.out.println("调用了exceptionCaught");

    }

    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {

        super.channelReadComplete(ctx);

    }
}
