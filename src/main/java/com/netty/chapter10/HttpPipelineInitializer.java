package com.netty.chapter10;

import io.netty.channel.Channel;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.handler.codec.http.HttpRequestDecoder;
import io.netty.handler.codec.http.HttpRequestEncoder;
import io.netty.handler.codec.http.HttpResponseDecoder;
import io.netty.handler.codec.http.HttpResponseEncoder;

/**
 * Created by PataPon on 2018/1/21.
 */
public class HttpPipelineInitializer extends ChannelInitializer {

    private final boolean client;

    public HttpPipelineInitializer(boolean client) {
        this.client = client;

    }

    @Override
    protected void initChannel(Channel ch) throws Exception {


        ChannelPipeline pipeline = ch.pipeline();
        if (client) {
            pipeline.addLast("encoder", new HttpRequestEncoder());
            pipeline.addLast("decoder", new HttpResponseDecoder());
        } else {
            pipeline.addLast("encoder", new HttpResponseEncoder());
            pipeline.addLast("decoder", new HttpRequestDecoder());
        }
    }
}
