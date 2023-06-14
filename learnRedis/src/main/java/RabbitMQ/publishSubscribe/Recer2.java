package RabbitMQ.publishSubscribe;

import RabbitMQ.ConnectionUtil;
import com.rabbitmq.client.*;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * @author Lihu
 * @PROJECT_NAME: learnDistributed
 * @DESCRIPTION:
 * @USER: Irene-Jisoo
 * @DATE: 2022/12/16 14:22
 */
public class Recer2 {
    public static void main(String[] args) throws IOException, TimeoutException {
        Connection connection = ConnectionUtil.getConnection();
        Channel channel = connection.createChannel();
        // 声明队列
        channel.queueDeclare("test_exchange_fanout_queue_2", false, false, false, null);
        // 绑定路由
        /**
         * 参数1：队列名
         * 参数2：交换器名称
         * 参数3：路由key（暂时无用，""即可）
         */
        channel.queueBind("test_exchange_fanout_queue_2", "test_exchange_fanout", "");
        DefaultConsumer consumer = new DefaultConsumer(channel){
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope,
                                       AMQP.BasicProperties properties, byte[] body) throws IOException {
                String s = new String(body);
                System.out.println("消费者1： " + s);

            }
        };
        // 4.监听队列 true:自动消息确认
        channel.basicConsume("test_exchange_fanout_queue_2", true, consumer);
    }
}
