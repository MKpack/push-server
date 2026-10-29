package com.zack.push.server.netty;

import io.netty.channel.Channel;
import org.springframework.stereotype.Component;

import java.util.concurrent.atomic.AtomicReference;

@Component
public class DeviceChannelHolder {
    public static volatile Channel channel;
}
