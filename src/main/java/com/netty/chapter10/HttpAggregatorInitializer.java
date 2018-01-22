package com.netty.chapter10;

import io.netty.channel.Channel;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.handler.codec.http.HttpClientCodec;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpServerCodec;

/**
 * Created by PataPon on 2018/1/21.
 */
public class HttpAggregatorInitializer extends ChannelInitializer {


    private final boolean client;

    public HttpAggregatorInitializer(boolean client) {
        this.client = client;
    }

    @Override
    protected void initChannel(Channel ch) throws Exception {


        ChannelPipeline pipeline = ch.pipeline();
        if (client) {
            pipeline.addLast("codec", new HttpClientCodec());
        } else {

            pipeline.addLast("codec", new HttpServerCodec());

        }

        pipeline.addLast("aggregator", new HttpObjectAggregator(512 * 1024));
    }
}
