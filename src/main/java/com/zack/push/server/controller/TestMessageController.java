package com.zack.push.server.controller;

import com.zack.push.server.common.ApiResponse;
import com.zack.push.server.common.BusinessException;
import com.zack.push.server.common.ErrorCode;
import com.zack.push.server.netty.DeviceChannelHolder;
import io.netty.channel.Channel;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestMessageController {
    @GetMapping("/send")
    public ApiResponse<Void> sendFixed() {
        return doSend("hello from push server");
    }

    @GetMapping("/send/{content}")
    public ApiResponse<Void> send(@PathVariable String content) {
        return doSend(content);
    }

    private ApiResponse<Void> doSend(String content) {
        Channel ch = DeviceChannelHolder.channel;
        if (ch == null || !ch.isActive()) {
            throw new BusinessException(ErrorCode.INTERNAL_ERROR);
        }
        ch.writeAndFlush(content + "\n");
        return ApiResponse.ok();
    }
}
