package RabbitMQ.publishSubscribe;

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
 * @DATE: 2022/12/16 14:16
 */
public class Sender {
    public static void main(String[] args) throws IOException, TimeoutException {
        Connection connection = ConnectionUtil.getConnection();
        Channel channel = connection.createChannel();
        // 声明路由: 路由名 路由类型
        //fanout 不处理路由器 只需要将队列绑定到路由上 发送到 路由的消息都会被转发到与该路由绑定的所有队列上
        channel.exchangeDeclare("test_exchange_fanout", "fanout");
        String msg = "hello Red velvet";
        channel.basicPublish("test_exchange_fanout", "", null, msg.getBytes());
        System.out.println("生产者： " + msg);
        channel.close();
        connection.close();

    }
}
