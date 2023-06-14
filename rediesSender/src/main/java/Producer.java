import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.util.UUID;

/**
 * @author Lihu
 * @PROJECT_NAME: learnDistributed
 * @DESCRIPTION:
 * @USER: Irene-Jisoo
 * @DATE: 2022/12/30 15:24
 */
public class Producer {


    public static void main(String[] args) {
        try {
            ConnectionFactory factory = new ConnectionFactory();

            factory.setHost("1.15.121.102");
            factory.setPort(5672);
            factory.setUsername("Irene");
            factory.setPassword("2292410586");
            factory.setVirtualHost("/lh");
            Connection connection = factory.newConnection();
            Channel channel = connection.createChannel();
            channel.exchangeDeclare("test_exchange", "fanout", true);
            channel.queueDeclare("test_queue", true, false, true, null);
            channel.queueBind("test_queue", "test_exchange", "test_bandingkey");
            String uuid = UUID.randomUUID().toString();
            System.out.println("提交信息：" + uuid);
            channel.basicPublish("test_exchange", "test_bandingkey", null, uuid.getBytes());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

}
