package com.example.redispubsub.config;

import com.example.redispubsub.sub.MyMessageListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.CachingConfigurerSupport;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.MessageListener;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;
import org.springframework.data.redis.listener.adapter.MessageListenerAdapter;
import org.springframework.util.StringUtils;
import redis.clients.jedis.JedisPoolConfig;

import javax.annotation.Resource;


@Configuration
@EnableCaching
public class RedisConfig extends CachingConfigurerSupport {

    @Autowired
    private RedisProperties redisProperties;

    @Resource
    private MessageListener mymessageListener;


    @Value(value = "${redis.pool.maxldle}")
    private int maxldle;

    @Value(value = "${redis.pool.maxTotal}")
    private int maxTotal;

    @Value(value = "${redis.pool.minIdle}")
    private int minIdle;

    @Value(value = "${redis.pool.maxWaitMillis}")
    private int maxWaitMillis;

    @Bean
    JedisConnectionFactory jedisConnectionFactory() {
        JedisConnectionFactory factory = new JedisConnectionFactory();

        factory.setHostName(redisProperties.getHost());
        factory.setDatabase(redisProperties.getDatabase());
        factory.setPort(redisProperties.getPort());
        if(!StringUtils.isEmpty(redisProperties.getPassword())){
            factory.setPassword(redisProperties.getPassword());
        }
        factory.setPoolConfig(poolCofig(maxldle,maxTotal,maxWaitMillis,minIdle,true));
        factory.setUsePool(true);
        return factory;
    }

    public JedisPoolConfig poolCofig(int maxIdle, int maxTotal, int minIdle, long maxWaitMillis, boolean testOnBorrow) {
        JedisPoolConfig poolCofig = new JedisPoolConfig();
        poolCofig.setMaxIdle(maxIdle);
        poolCofig.setMinIdle(minIdle);
        poolCofig.setMaxTotal(maxTotal);
        poolCofig.setMaxWaitMillis(maxWaitMillis);
        poolCofig.setTestOnBorrow(testOnBorrow);
        return poolCofig;
    }


    @Bean
    MessageListenerAdapter messageListener() {
        return new MessageListenerAdapter(mymessageListener);
    }

    @Bean
    RedisMessageListenerContainer redisContainer() {
        final RedisMessageListenerContainer container = new RedisMessageListenerContainer();
        container.setConnectionFactory(jedisConnectionFactory());
        return container;
    }
//
//    @Bean(name = "pushMessageRedisTemplate")
//    public RedisTemplate<String, PushMessage> pushMessageRedisTemplate() {
//        RedisTemplate<String, PushMessage> redisTemplate = new RedisTemplate<String, PushMessage>();
//        redisTemplate.setConnectionFactory(jedisConnectionFactory());
//        redisTemplate.setKeySerializer(new StringRedisSerializer());
//        redisTemplate.setValueSerializer(new Jackson2JsonRedisSerializer<PushMessage>(PushMessage.class));
//        redisTemplate.setDefaultSerializer(new StringRedisSerializer());
//        return redisTemplate;
//    }

    @Bean(name = "redisTemplate")
    RedisTemplate<String, String> redisTemplate() {
        RedisTemplate<String, String> redisTemplate = new RedisTemplate<String, String>();
        redisTemplate.setConnectionFactory(jedisConnectionFactory());
        return redisTemplate;
    }



}
