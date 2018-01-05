package com.netty.chapter1;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;

/**
 * Created with IntelliJ IDEA.
 * User: za-lvjian
 * Date: 2018/1/5 15:51
 * DESC:
 */
public class Client {

    public static void main(String[] args) throws IOException {

        SocketChannel socketChannel = SocketChannel.open();
        socketChannel.connect(new InetSocketAddress("localhost",8999));

        ByteBuffer buffer = ByteBuffer.allocate(1024);

        buffer.put("哈哈".getBytes());
        buffer.flip();
        socketChannel.write(buffer);

        System.in.read();
        socketChannel.close();

        System.out.println("客户端发送完毕---");

    }
}
