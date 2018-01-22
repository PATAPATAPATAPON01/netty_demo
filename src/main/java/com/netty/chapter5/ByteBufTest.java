package com.netty.chapter5;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufUtil;
import io.netty.buffer.Unpooled;
import org.junit.Test;

import java.nio.charset.Charset;

/**
 * Created by PataPon on 2018/1/13.
 */
public class ByteBufTest {

    @Test
    public void method() {

        Charset charset = Charset.forName("utf-8");

        String string = "buffer 学习";
        ByteBuf copiedBuffer = Unpooled.copiedBuffer(string.toCharArray(), charset);

        ByteBuf wrappedBuffer = Unpooled.wrappedBuffer(string.getBytes());

        //返回一个堆存储的bytebuf
        ByteBuf heapByteBuf = Unpooled.buffer();


        //返回一个直接存储的bytebuf
        ByteBuf directBuffer = Unpooled.directBuffer();
        System.out.println();


        String dump = ByteBufUtil.hexDump(string.getBytes());
        System.out.println("十六进制表示是: " + dump);

    }


    @Test
    public void method2() {

        ByteBuf copiedBuffer = Unpooled.copiedBuffer("hha".getBytes());

        System.out.println(copiedBuffer.refCnt());

        copiedBuffer.release(); //释放引用计数

        System.out.println(copiedBuffer.refCnt());

    }
}
