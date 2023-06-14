package test;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.IOException;

/**
 * @author Lihu
 * @PROJECT_NAME: learnDistributed
 * @DESCRIPTION:
 * @USER: Irene-Jisoo
 * @DATE: 2023/1/11 15:56
 */
public class TestRunner {
    public static void main(String[] args) throws IOException {
        // 获得容器
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("spring/spring-rabbitmq-consumer.xml");
        // 程序一直运行
        System.in.read();

    }


}
