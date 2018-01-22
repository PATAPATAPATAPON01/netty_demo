package com.netty.chapter10;

import io.netty.channel.Channel;
import io.netty.channel.ChannelInitializer;
import io.netty.handler.ssl.SslContext;
import io.netty.handler.ssl.SslHandler;

import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLEngine;

/**
 * Created by PataPon on 2018/1/21.
 */
public class SSLChannelInitializer extends ChannelInitializer {


    private final SslContext context;

    public SSLChannelInitializer(SslContext context, boolean startTls) {
        this.context = context;
        this.startTls = startTls;
    }

    private final boolean startTls;

    @Override
    protected void initChannel(Channel ch) throws Exception {

        SSLEngine sslEngine = context.newEngine(ch.alloc());

        ch.pipeline().addFirst("ssl", new SslHandler(sslEngine, startTls));
    }
}
