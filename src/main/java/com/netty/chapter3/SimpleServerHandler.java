package com.netty.chapter3;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

import java.nio.charset.Charset;

/**
 * Created by PataPon on 2018/1/6.
 */
public class SimpleServerHandler extends ChannelInboundHandlerAdapter {

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {


        if (msg instanceof ByteBuf) {

            System.out.println(((ByteBuf) msg).toString(Charset.defaultCharset()));

            ctx.channel().writeAndFlush("收到了 Ok");
        }


    }
}
