package RabbitMQ;

import com.rabbitmq.client.*;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * @author Lihu
 * @PROJECT_NAME: learnDistributed
 * @DESCRIPTION:
 * @USER: Irene-Jisoo
 * @DATE: 2022/12/16 11:27
 */
public class MessageReceiver2 {
    static int i = 1;

    public static void main(String[] args) throws IOException, TimeoutException {
        Connection connection = ConnectionUtil.getConnection();
        final Channel channel = connection.createChannel();
        // 声明队列（此处为消费者，不是声明创建队列，而且获取，二者代码相同）出餐口排队
        channel.queueDeclare("test_work_queue",false,false,false,null);
        // 可以理解为：快递一个一个送，送完一个再送下一个，速度快的送件就多
        channel.basicQos(1);
        DefaultConsumer consumer = new DefaultConsumer(channel){
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                String msg = new String(body);
                System.out.println("粉丝" + msg + "进场" + "   总共进场：" + i++ + "个粉丝");
                try {
                    Thread.sleep(200);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                channel.basicAck(envelope.getDeliveryTag(), false);

            }
        };
        channel.basicConsume("test_work_queue", false, consumer);

    }
}
