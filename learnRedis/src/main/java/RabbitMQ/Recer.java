package RabbitMQ;

import com.rabbitmq.client.*;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * @author Lihu
 * @PROJECT_NAME: learnDistributed
 * @DESCRIPTION: 简单模式  消费者 C
 * @USER: Irene-Jisoo
 * @DATE: 2022/12/16 10:18
 */
public class Recer {
    public static void main(String[] args) throws IOException, TimeoutException {
        // 1、 获取连接
        Connection connection = ConnectionUtil.getConnection();
        // 2、 获得通道（信道）
        Channel channel = connection.createChannel();
        //3、从信道中获取信息
        DefaultConsumer consumer = new DefaultConsumer(channel) {
            // 交付处理（收件人信息，包裹上的快递标签，协议的配置，消息）
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                // body 就是从队列中获取消息
                String s = new String(body);
                System.out.println("接受 = " + s);

            }

        };
        // 4、监听队列 true：自动消息确认
        channel.basicConsume("queuel", true, consumer);
        // 手动确认
//        channel.basicConsume("queuel", false, consumer);

    }
}
