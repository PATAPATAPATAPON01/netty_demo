package com.netty.chapter8;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.embedded.EmbeddedChannel;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by PataPon on 2018/1/20.
 */
public class ABSIntegerEncoderTest {


    /**
     * Java 运算优先级 单目乘除位关系，逻辑三目后赋值
     */
    @Test
    public void testEncode() {


        ByteBuf buffer = Unpooled.buffer();

        for (int i = 1; i < 10; i++) {


            buffer.writeInt(i * -1);

        }

        EmbeddedChannel channel = new EmbeddedChannel(new ABSIntegerEncoder());

        assertTrue(channel.writeOutbound(buffer));

        assertTrue(channel.finish());

        for (int i = 1; i < 10; i++) {


            System.out.println((Integer) channel.readOutbound());

        }

        assertNull(channel.readOutbound());


    }
}
