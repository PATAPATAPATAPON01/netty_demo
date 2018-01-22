package com.netty.chapter9;

import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToMessageEncoder;

import java.util.List;

/**
 * Created by PataPon on 2018/1/20.
 */
public class IntegerToStringEncoder extends MessageToMessageEncoder<Integer> {


    @Override
    protected void encode(ChannelHandlerContext ctx, Integer msg, List<Object> out) throws Exception {


        out.add(String.valueOf(msg));

    }
}
