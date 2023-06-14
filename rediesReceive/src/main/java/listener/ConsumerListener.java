package listener;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.amqp.core.Message;

import com.rabbitmq.client.Channel;

import java.io.IOException;

/**
 * @author Lihu
 * @PROJECT_NAME: learnDistributed
 * @DESCRIPTION: 消费者监听队列
 * @USER: Irene-Jisoo
 * @DATE: 2023/1/11 16:03
 */
public class ConsumerListener {
    /**
     *  jackson 提供序列化和反序列中使用最多的类，用来转换json的
     */
    private static final ObjectMapper MAPPER = new ObjectMapper();

    public void onMessage(Message message, Channel channel) {
        try {



            //手动确认消息(参数1，参数2)
            /*
            参数1：RabbitMQ想该channel投递的这条消息的唯一标识ID，此ID是一个单调递增的正整数
            参数2：为了减少网络流量，手动确认可以被批量处理，当该参数为true时，则可以一次性确认小于等于msgId值的所有消息
             */
            String str = new String(message.getBody());
            System.out.println("str = " + str);
            long msgId = message.getMessageProperties().getDeliveryTag();
            channel.basicAck(msgId, true);
            Thread.sleep(300);
            System.out.println("休息三秒再继续 接收消息");

        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
