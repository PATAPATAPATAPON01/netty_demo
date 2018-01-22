package com.netty.chapter9;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;

import java.util.List;

/**
 * Created by PataPon on 2018/1/20.
 */
public class ByteToCharDecoer extends ByteToMessageDecoder {
    @Override
    protected void decode(ChannelHandlerContext ctx, ByteBuf in, List<Object> out) throws Exception {


        while (in.readableBytes() >= 2) {
            out.add(in.readChar());
        }
    }
}
