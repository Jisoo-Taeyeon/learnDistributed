package RabbitMQ;

import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * @author Lihu
 * @PROJECT_NAME: learnDistributed
 * @DESCRIPTION: 获取 mq 连接
 * @USER: Irene-Jisoo
 * @DATE: 2022/12/15 14:47
 */
public class ConnectionUtil {
    public static Connection getConnection() throws IOException, TimeoutException {
        //1、创建连接厂
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("1.15.121.102");
        factory.setPort(5672);
        factory.setVirtualHost("/lh");
        factory.setUsername("Irene");
        factory.setPassword("2292410586");
        //3、 通过工厂获得与MQ的连接
        Connection connection = factory.newConnection();
        return connection;
    }

    public static void main(String[] args) throws IOException, TimeoutException {
        Connection connection = getConnection();
        System.out.println("connection = " + connection);
        connection.close();
    }
}
