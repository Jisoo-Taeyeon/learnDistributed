package org.example.stub;

import org.example.service.HelloService;
import org.springframework.util.StringUtils;

/**
 * @author Lihu
 * @PROJECT_NAME: learnDistributed
 * @DESCRIPTION:
 * @USER: Irene-Jisoo
 * @DATE: 2023/8/26 16:19
 */
public class helloServiceStub implements HelloService {

    /**
     * 需特别注意本地存根必须以构造方法的形式注入 不同通过注解 getSet方法注入
     * 是helloService 的代理对象
     * **/
    private HelloService helloService;


    public helloServiceStub(HelloService helloService) {
        this.helloService = helloService;
    }

    @Override
    public String sayHello(String name) {
        if (!StringUtils.isEmpty(name)) {
            return helloService.sayHello(name);
        }
        return " red velvet";

    }
}
