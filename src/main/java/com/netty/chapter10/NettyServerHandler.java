package com.netty.chapter10;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.http.*;
import io.netty.util.CharsetUtil;

import java.net.InetAddress;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

/**
 * Created by PataPon on 2018/1/21.
 */
public class NettyServerHandler extends ChannelInboundHandlerAdapter {


    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {


        FullHttpRequest request = (FullHttpRequest) msg;

        String path = request.getUri();

        String body = getBody(request);

        HttpMethod method = request.method();
        String result;
//
//        if (!"/test".equals(path)) {
//            result = "非法请求";
//            send(ctx, result, HttpResponseStatus.BAD_REQUEST);
//            return;
//
//        }

        if (HttpMethod.GET.equals(method)) {
            //接受到的消息，做业务逻辑处理...
            System.out.println("body:" + body);
            result = "GET请求";
            send(ctx, result, HttpResponseStatus.OK);
            return;
        }

        if (HttpMethod.POST.equals(method)) {
            //接受到的消息，做业务逻辑处理...
            System.out.println("body:" + body);
            result = "POST请求";
            send(ctx, result, HttpResponseStatus.OK);
            return;
        }

    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {

        System.out.println("连接的客户端地址:" + ctx.channel().remoteAddress());
        ctx.writeAndFlush("客户端" + InetAddress.getLocalHost().getHostName() + "成功与服务器建立连接");
        super.channelActive(ctx);
    }

    private void send(ChannelHandlerContext ctx, String context, HttpResponseStatus status) {

        FullHttpResponse response = new DefaultFullHttpResponse(HttpVersion.HTTP_1_1, status, Unpooled.copiedBuffer(context, CharsetUtil.UTF_8));
        response.headers().set(HttpHeaderNames.CONTENT_TYPE, "text/plain; charset=UTF-8");
        ctx.writeAndFlush(response).addListener(ChannelFutureListener.CLOSE);
    }

    private String getBody(FullHttpRequest request) {

        ByteBuf content = request.content();

        return content.toString(StandardCharsets.UTF_8);
    }
}
