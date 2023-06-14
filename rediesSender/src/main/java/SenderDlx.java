import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author Lihu
 * @PROJECT_NAME: learnDistributed
 * @DESCRIPTION:
 * @USER: Irene-Jisoo
 * @DATE: 2022/12/30 15:35
 */
public class SenderDlx {
    public static void main(String[] args) {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("spring/spring-rabbitmq-producer-dlx.xml");
        RabbitTemplate rabbitTemplate = context.getBean(RabbitTemplate.class);
        rabbitTemplate.convertAndSend("dlx_max", "测试长度1".getBytes());

        System.out.println("消息已发出");

        context.close();
    }
}
