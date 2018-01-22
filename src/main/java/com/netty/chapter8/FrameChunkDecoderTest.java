package com.netty.chapter8;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.embedded.EmbeddedChannel;
import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;


/**
 * Created by PataPon on 2018/1/20.
 */
public class FrameChunkDecoderTest {

    @Test
    public void testFramChunkDecoder() {


        ByteBuf buffer = Unpooled.buffer();

        for (int i = 0; i < 9; i++) {
            buffer.writeByte(i);
        }


        ByteBuf input = buffer.duplicate();

        EmbeddedChannel channel = new EmbeddedChannel(new FrameChunkDecoder(3));

        assertTrue(channel.writeInbound(input.readBytes(2)));

        try {

            channel.writeInbound(input.readBytes(4));


            Assert.fail();
        } catch (Exception e) {


            e.printStackTrace();
        }

        assertTrue(channel.writeInbound(input.readBytes(3)));

        assertTrue(channel.finish());


        ByteBuf read = channel.readInbound();

        assertEquals(buffer.readSlice(2), read);

        read.release();


        read = channel.readInbound();

        assertEquals(buffer.skipBytes(4).readSlice(3), read);

        System.err.println("");
        
        read.release();


        buffer.release();

    }
}
