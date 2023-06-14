package RabbitMQ.routingMode;

import RabbitMQ.ConnectionUtil;
import com.rabbitmq.client.*;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * @author Lihu
 * @PROJECT_NAME: learnDistributed
 * @DESCRIPTION:
 * @USER: Irene-Jisoo
 * @DATE: 2022/12/22 18:55
 */
public class Receive1 {
    public static void main(String[] args) throws IOException, TimeoutException {
        Connection connection = ConnectionUtil.getConnection();
        Channel channel = connection.createChannel();
        // 声明队列
        channel.queueDeclare("test_exchange_direct_queue_1", false, false, false, null);
        // 绑定路由（如果路由键的类型是 添加，删除，修改的话，绑定到这个队列 上）
        channel.queueBind("test_exchange_direct_queue_1", "test_exchange_direct", "insert");
        channel.queueBind("test_exchange_direct_queue_1", "test_exchange_direct", "update");
        channel.queueBind("test_exchange_direct_queue_1", "test_exchange_direct", "delete");

        DefaultConsumer consumer = new DefaultConsumer(channel){
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                String s = new String(body);
                System.out.println("[消费者1]  = " + s);
            }
        };
        //4、监听队列 true：自动消息消息确认
        channel.basicConsume("test_exchange_direct_queue_1", true, consumer);
    }
}
