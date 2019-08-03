package com.example.redispubsub.config;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * Created by zhangxin on 2017/7/31.
 */
@Component
@Data
public class RedisProperties {

    @Value(value = "${spring.redis.database}")
    private int database;

    @Value(value = "${spring.redis.host}")
    private String host;

    @Value(value = "${spring.redis.port}")
    private int port;

    @Value(value = "${spring.redis.password}")
    private String password;

}
