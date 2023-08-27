package com.lh.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.lh.service.HelloService;

/**
 * @author Lihu
 * @PROJECT_NAME: learnDistributed
 * @DESCRIPTION:
 * @USER: Irene-Jisoo
 * @DATE: 2023/7/11 18:06
 */
@Service
public class HelloServiceImpl implements HelloService {

    @Override
    public String sayHello(String name) {
        return "hello " + name + "!!";
    }
}
