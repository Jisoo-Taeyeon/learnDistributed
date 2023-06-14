package RabbitMQ.wildcardMode;

import RabbitMQ.ConnectionUtil;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * @author Lihu
 * @PROJECT_NAME: learnDistributed
 * @DESCRIPTION: 通配符模式 生产者
 * @USER: Irene-Jisoo
 * @DATE: 2022/12/23 17:18
 */
public class Sender {
    public static void main(String[] args) throws IOException, TimeoutException {
        Connection connection = ConnectionUtil.getConnection();
        Channel channel = connection.createChannel();
        // 声明路由（路由名，路由类型）
        // topic: 模糊匹配的定向分发
        channel.exchangeDeclare("test_exchange_topic", "topic");
        //支持持久化
        // channel.exchangeDeclare("test_exchange_topic", "topic",true);
        String msg = "red velvet psycho";
        channel.basicPublish("test_exchange_topic", "product.price", null, msg.getBytes());
        System.out.println("用户系统： " + msg);
        channel.close();
        connection.close();
    }
}
