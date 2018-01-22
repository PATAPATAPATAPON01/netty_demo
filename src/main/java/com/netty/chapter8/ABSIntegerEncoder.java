package com.netty.chapter8;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToMessageEncoder;

import java.util.List;

/**
 * Created by PataPon on 2018/1/20.
 */
public class ABSIntegerEncoder extends MessageToMessageEncoder<ByteBuf> {
    @Override
    protected void encode(ChannelHandlerContext ctx, ByteBuf in, List out) throws Exception {

        while (in.readableBytes() >= 4) { //检查是否有足够的字节来进行编码


            int abs = Math.abs(in.readInt());
            out.add(abs);
        }
    }
}
