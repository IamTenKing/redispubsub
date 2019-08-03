package com.example.redispubsub.sub;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

/**
 * @author jt
 * @date 2019-8-3
 */
@Component
public class MyApplicationListener implements ApplicationListener<ContextRefreshedEvent> {


    @Autowired
    private Register register;

    /**
     * 容器启动即订阅一个主题
     * @param event
     */
    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
            register.register();
    }
}
