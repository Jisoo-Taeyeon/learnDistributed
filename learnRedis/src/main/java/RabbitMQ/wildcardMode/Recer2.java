package RabbitMQ.wildcardMode;

import RabbitMQ.ConnectionUtil;
import com.rabbitmq.client.*;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * @author Lihu
 * @PROJECT_NAME: learnDistributed
 * @DESCRIPTION: 通配符模式 消费者 1
 * @USER: Irene-Jisoo
 * @DATE: 2022/12/23 17:22
 */
public class Recer2 {
    public static void main(String[] args) throws IOException, TimeoutException {
        Connection connection = ConnectionUtil.getConnection();
        Channel channel = connection.createChannel();

        // 声明路由
        channel.queueDeclare("test_exchange_topic_queue_2", false, false, false, null);
        // 绑定路由( 绑定  用户相关 的信息）
        channel.queueBind("test_exchange_topic_queue_2", "test_exchange_topic", "product.#");
        channel.queueBind("test_exchange_topic_queue_2", "test_exchange_topic", "order.#");
        DefaultConsumer consumer = new DefaultConsumer(channel){
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                String s = new String(body);
                System.out.println("消费者2 ：" + s);
            }
        };
        // 4. 监听队列 true：自动消息确认
        channel.basicConsume("test_exchange_topic_queue_2", true, consumer);

    }
}
