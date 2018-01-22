package com.netty.chapter8;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;

import java.util.List;

/**
 * Created by PataPon on 2018/1/18.
 */
public class FixedLengthFrameDecoder extends ByteToMessageDecoder {


    private final int frameLength;

    public FixedLengthFrameDecoder(int frameLength) {
        this.frameLength = frameLength;
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        super.channelRead(ctx, msg);
    }

    @Override
    protected void decode(ChannelHandlerContext ctx, ByteBuf in, List<Object> out) throws Exception {


        while (in.readableBytes() >= frameLength) {

            ByteBuf byteBuf = in.readBytes(frameLength);

            //将该帧添加到已被解码的消息列表中
            out.add(byteBuf);

        }
    }


}
