package org.test;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.IOException;

/**
 * @author Lihu
 * @PROJECT_NAME: learnDistributed
 * @DESCRIPTION: dubbo 启动时检查
 * @USER: Irene-Jisoo
 * @DATE: 2023/7/28 17:25
 */
public class TestCheckException {

    public static void main(String[] args) throws IOException {
        // 初始化spring

        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("classpath:spring.xml");
        System.in.read();

    }
}
