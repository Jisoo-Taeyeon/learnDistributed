package RabbitMQ;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * @author Lihu
 * @PROJECT_NAME: learnDistributed
 * @DESCRIPTION: 工作队列模式 生产者P
 * @USER: Irene-Jisoo
 * @DATE: 2022/12/16 10:49
 */
public class MessageSender {
    public static void main(String[] args) throws IOException, TimeoutException {
        Connection connection = ConnectionUtil.getConnection();
        Channel channel = connection.createChannel();

        // 声明队列 （此处为生产者，创建队列） 注明出餐口位置，通知大家来排队
        channel.queueDeclare("test_work_queue", false, false, false, null);
        for (int i = 0; i < 100; i++) {
            String msg = "red velvet fans --> " + i;
            channel.basicPublish("", "test_work_queue", null, msg.getBytes());
            System.out.println("消息已产生：" + msg);
        }
        channel.close();
        connection.close();
    }
}
