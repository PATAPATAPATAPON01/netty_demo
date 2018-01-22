package com.netty.chapter8;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.embedded.EmbeddedChannel;
import org.junit.Test;


import static org.junit.Assert.*;

/**
 * Created by PataPon on 2018/1/18.
 */
public class TestClass {


    @Test
    public void testFrameDecode() {
        ByteBuf buffer = Unpooled.buffer();

        for (int i = 0; i < 9; i++) {

            buffer.writeByte(i);

        }

        ByteBuf input = buffer.duplicate();

        EmbeddedChannel embeddedChannel = new EmbeddedChannel(new FixedLengthFrameDecoder(3));

//        System.out.println(embeddedChannel.writeInbound(1));

        assertTrue(embeddedChannel.writeInbound(input.retain()));

        assertTrue(embeddedChannel.finish());

        //read message


        ByteBuf read = (ByteBuf) embeddedChannel.readInbound();

        assertEquals(buffer.readSlice(3), read);

        read.release();

        read = (ByteBuf) embeddedChannel.readInbound();
        assertEquals(buffer.readSlice(3), read);

        read.release();

        read = (ByteBuf) embeddedChannel.readInbound();
        assertEquals(buffer.readSlice(3), read);

        read.release();

        assertNull(embeddedChannel.readInbound());

        buffer.release();
    }


    @Test
    public void testFramDecoder2() {

        ByteBuf buffer = Unpooled.buffer();

        for (int i = 0; i < 9; i++) {


            buffer.writeByte(i);
        }


        ByteBuf input = buffer.duplicate();

        EmbeddedChannel embeddedChannel = new EmbeddedChannel(new FixedLengthFrameDecoder(3));


        assertFalse(embeddedChannel.writeInbound(input.readBytes(2)));
        assertTrue(embeddedChannel.writeInbound(input.readBytes(7)));


        assertTrue(embeddedChannel.finish());

        ByteBuf read = embeddedChannel.readInbound();

        assertEquals(buffer.readSlice(3), read);

        read.release();

        read = embeddedChannel.readInbound();

        assertEquals(buffer.readSlice(3), read);

        read.release();

        read = embeddedChannel.readInbound();

        assertEquals(buffer.readSlice(3), read);

        read.release();


        assertNull(embeddedChannel.readInbound());

        buffer.release();


    }
}
