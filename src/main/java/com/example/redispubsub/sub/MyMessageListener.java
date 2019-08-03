package com.example.redispubsub.sub;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.connection.MessageListener;
import org.springframework.stereotype.Component;

/**
 * @author jt
 * @date 2019-8-3
 */
@Slf4j
@Component
public class MyMessageListener implements MessageListener {
    @Override
    public void onMessage(Message message, byte[] bytes) {
        System.out.println("接收到内容："+message);
        log.info("接收到调度命令，现在就处理该条消息");

    }
}
