import org.springframework.amqp.rabbit.connection.RabbitAccessor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Lihu
 * @PROJECT_NAME: learnDistributed
 * @DESCRIPTION:
 * @USER: Irene-Jisoo
 * @DATE: 2022/12/30 15:55
 */
public class Sender {
    public static void main(String[] args) {
        //1。创建spring 容器
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("spring/spring-rabbitmq-producer.xml");
        //2.从容器中获得 rabbit 模板对象
        RabbitTemplate rabbitTemplate = context.getBean(RabbitTemplate.class);
        Map<String, String> map = new HashMap<>();
        map.put("name", "Irene");
        map.put("group", "red velvet");
        rabbitTemplate.convertAndSend("msg.user", map);
        System.out.println("消息已发送");
    }
}
