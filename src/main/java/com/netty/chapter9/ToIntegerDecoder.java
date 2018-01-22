package com.netty.chapter9;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;

import java.util.List;

/**
 * Created by PataPon on 2018/1/20.
 */
public class ToIntegerDecoder extends ByteToMessageDecoder {


    @Override
    protected void decode(ChannelHandlerContext ctx, ByteBuf in, List<Object> out) throws Exception {

        while (in.readableBytes() >= 4) {

            out.add(in.readInt());
        }

    }


    /**
     * 当channel变为inactive时会被调用de
     *
     * @param ctx
     * @param in
     * @param out
     * @throws Exception
     */

    @Override
    protected void decodeLast(ChannelHandlerContext ctx, ByteBuf in, List<Object> out) throws Exception {
        System.err.println("调用了decodeLast方法");
        super.decodeLast(ctx, in, out);
    }
}
