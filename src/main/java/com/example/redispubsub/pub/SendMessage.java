package com.example.redispubsub.pub;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @author jt
 * @date 2019-8-3
 */
@Controller
public class SendMessage {


    @Autowired
    private RedisTemplate redisTemplate;

    @GetMapping("/test/{message}")
    public void test(@PathVariable("message") String message){
        System.out.println("发送内容："+message);
        redisTemplate.convertAndSend("1",message);
    }


}
