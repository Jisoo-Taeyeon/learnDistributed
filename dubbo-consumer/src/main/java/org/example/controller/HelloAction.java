package org.example.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import org.example.service.HelloService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author Lihu
 * @PROJECT_NAME: learnDistributed
 * @DESCRIPTION:
 * @USER: Irene-Jisoo
 * @DATE: 2023/7/11 18:31
 */
@Controller
public class HelloAction {
    /**
     * 远程去服务方 将service 的实现类注入进来
     */
    @Reference
    private HelloService helloService;
    @GetMapping("hello")
    @ResponseBody
    public String sayHello(String name) {
        return helloService.sayHello(name);
    }

}
