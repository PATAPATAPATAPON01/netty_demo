package com.netty.chapter9;

import io.netty.channel.CombinedChannelDuplexHandler;

/**
 * Created by PataPon on 2018/1/20.
 */
public class CombinedByteCharCodec extends CombinedChannelDuplexHandler<ByteToCharDecoer, CharToByteEncoder> {

    public CombinedByteCharCodec() {
        super(new ByteToCharDecoer(), new CharToByteEncoder());
    }

}
