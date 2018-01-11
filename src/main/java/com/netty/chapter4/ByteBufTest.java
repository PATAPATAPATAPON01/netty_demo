package com.netty.chapter4;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.CompositeByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.util.ByteProcessor;
import org.junit.Test;
import org.springframework.util.Assert;

import java.nio.charset.Charset;

/**
 * Created by PataPon on 2018/1/11.
 */
public class ByteBufTest {

    @Test
    public void method() {

        CompositeByteBuf byteBufs = Unpooled.compositeBuffer();


        ByteBuf buf = null;

        while (buf.isReadable()) {


            System.out.println(buf.readByte());


        }


        buf.markReaderIndex();

        buf.markWriterIndex();

        buf.clear();


        buf.forEachByte(ByteProcessor.FIND_CRLF);

    }


    @Test
    public void method2() throws ClassNotFoundException {

        Charset charset = Charset.forName("utf-8");

        ByteBuf buf = Unpooled.copiedBuffer("Netty in Action Rocks", charset);

        ByteBuf sliced = buf.slice(0, 15);


        System.out.println(sliced.toString(charset));

        buf.setByte(0, (byte) 'J');

        assert buf.getByte(0) == sliced.getByte(0) : "不等~";


    }


    @Test
    public void method3() {


        Charset charset = Charset.forName("utf-8");

        ByteBuf buf = Unpooled.copiedBuffer("Netty in rocks", charset);

        System.out.println((char) buf.getByte(0));

        int readerIndex = buf.readerIndex();

        int writerIndex = buf.writerIndex();


        buf.setByte(0, (byte) 'B');
        System.out.println((char) buf.getByte(0));

        assert readerIndex == buf.readerIndex();
        assert writerIndex == buf.writerIndex();

    }

    @Test
    public void method4() {

        Charset charset = Charset.forName("utf-8");

        ByteBuf buf = Unpooled.copiedBuffer("Netty in rocks", charset);

        System.out.println((char) buf.readByte());

        int readerIndex = buf.readerIndex();

        int writerIndex = buf.writerIndex();

        buf.writeByte('?');

        assert readerIndex == buf.readerIndex();

        assert writerIndex != buf.writerIndex();


        Assert.isTrue(1!=1,"验证失败，这是失败信息");
    }
}
