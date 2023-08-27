package org.example.service;

/**
 *  服务方接口 （声明而已，具体实现会远程调用dubbo-server 的service 实现类）
 *
 * @author 流年安好
 * @date 2023/07/11
 */
public interface HelloService {

    /**
     * 说“你好”
     *
     * @param name 名字
     * @return {@link String}
     */
    String sayHello(String name);
}
