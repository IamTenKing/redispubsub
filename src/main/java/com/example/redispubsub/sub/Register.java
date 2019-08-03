package com.example.redispubsub.sub;

import org.springframework.data.redis.listener.ChannelTopic;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;
import org.springframework.data.redis.listener.adapter.MessageListenerAdapter;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * @author jt
 * @date 2019-8-3
 */
@Component
public class Register {


    @Resource
    private RedisMessageListenerContainer redisMessageListenerContainer;

    @Resource
    private MessageListenerAdapter messageListenerAdapter;

    public void register(){
        System.out.println("注册一个主题");
        String channelStr="1";
        ChannelTopic topic = new ChannelTopic(channelStr);
        redisMessageListenerContainer.addMessageListener(messageListenerAdapter, topic);

    }




}
