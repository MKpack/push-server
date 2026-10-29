package com.zack.push.server.netty;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class EchoHandler extends SimpleChannelInboundHandler<String> {

    private final Logger log = LoggerFactory.getLogger(EchoHandler.class);

    @Override
    public void channelActive(ChannelHandlerContext ctx) {
        DeviceChannelHolder.channel = ctx.channel();
        log.info("connected: {}", ctx.channel().remoteAddress());
    }

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, String msg) {
        log.info("recv from {}: {}", ctx.channel().remoteAddress(), msg);
        ctx.writeAndFlush("echo: " + msg + "\n");
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) {
        if (DeviceChannelHolder.channel == ctx.channel()) {
            DeviceChannelHolder.channel = null;
        }
        log.info("disconnected: {}", ctx.channel().remoteAddress());
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
        log.warn("error on {}", ctx.channel().remoteAddress(), cause);
        ctx.close();
    }
}
