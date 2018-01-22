package com.netty.chapter9;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;

/**
 * Created by PataPon on 2018/1/20.
 */
public class ShortToByteEncoder extends MessageToByteEncoder<Short> {


    @Override
    protected void encode(ChannelHandlerContext ctx, Short msg, ByteBuf out) throws Exception {

        out.writeShort(msg);
    }
}
