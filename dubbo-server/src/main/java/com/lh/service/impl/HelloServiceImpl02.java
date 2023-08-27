package com.lh.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.lh.service.HelloService;

/**
 * @author Lihu
 * @PROJECT_NAME: learnDistributed
 * @DESCRIPTION:
 * @USER: Irene-Jisoo
 * @DATE: 2023/8/26 16:00
 */
@Service
public class HelloServiceImpl02 implements HelloService {

    @Override
    public String sayHello(String name) {
        System.out.println(" 实现类 2 被调用了1次 ");
        try {
            Thread.sleep(300);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        return "hello " + name;
    }
}
