package RabbitMQ.routingMode;

import RabbitMQ.ConnectionUtil;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * @author Lihu
 * @PROJECT_NAME: learnDistributed
 * @DESCRIPTION:
 * @USER: Irene-Jisoo
 * @DATE: 2022/12/22 16:06
 */
public class Sender {
    public static void main(String[] args) throws IOException, TimeoutException {
        Connection connection = ConnectionUtil.getConnection();
        Channel channel = connection.createChannel();
        // 声明路由: 路由名 路由类型
        // direct：根据路由键进行定向分发消息
        channel.exchangeDeclare("test_exchange_direct", "direct");
        String msg = "用户注册，【userID： Irene】";
        channel.basicPublish("test_exchange_direct", "insert", null, msg.getBytes());
        System.out.println("用户系统： " + msg);
        channel.close();
        connection.close();
    }
}

