package RabbitMQ;

import com.rabbitmq.client.*;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * @author Lihu
 * @PROJECT_NAME: learnDistributed
 * @DESCRIPTION:
 * @USER: Irene-Jisoo
 * @DATE: 2022/12/16 10:39
 */
public class RecerByAck {
    public static void main(String[] args) throws IOException, TimeoutException {
        //1、获得连接
        Connection connection = ConnectionUtil.getConnection();
        //2、 获取通道
        final Channel channel = connection.createChannel();
        //3、从通道中获取信息
        DefaultConsumer consumer = new DefaultConsumer(channel) {
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                String s = new String(body);
                System.out.println("接受： " + s);
                // 手动确认 （收件人信息，是否同时确认多个消息）
                channel.basicAck(envelope.getDeliveryTag(), false);
            }
        };
        //4、 监听队列，false： 手动确认信息
        channel.basicConsume("queuel", false, consumer);
    }
}
