package com.netty.chapter8;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;
import io.netty.handler.codec.TooLongFrameException;

import java.util.List;

/**
 * Created by PataPon on 2018/1/20.
 */
public class FrameChunkDecoder extends ByteToMessageDecoder {


    private final int maxSize;

    public FrameChunkDecoder(int maxSize) {
        this.maxSize = maxSize;
    }

    @Override
    protected void decode(ChannelHandlerContext ctx, ByteBuf in, List<Object> out) throws Exception {


        int readableBytes = in.readableBytes();

        if (readableBytes > maxSize) {

            in.clear();

            throw new TooLongFrameException("超长了,老铁");
        }

        ByteBuf bytes = in.readBytes(readableBytes);

        out.add(bytes);

    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        super.exceptionCaught(ctx, cause);
    }
}
