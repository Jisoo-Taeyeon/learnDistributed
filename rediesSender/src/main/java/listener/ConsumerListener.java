package listener;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.rabbitmq.client.Channel;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.listener.adapter.AbstractAdaptableMessageListener;
import org.springframework.stereotype.Component;

/**
 * @author Lihu
 * @PROJECT_NAME: learnDistributed
 * @DESCRIPTION:
 * @USER: Irene-Jisoo
 * @DATE: 2023/1/3 16:05
 */
@Component
public class ConsumerListener extends AbstractAdaptableMessageListener {
    // jackson 提供序列化和反序列中用最多的类，用来转换json的
    private static final ObjectMapper MAPPER = new ObjectMapper();

    @Override
    public void onMessage(Message message, Channel channel) throws Exception {
        String s = new String(message.getBody());
        System.out.println("str = " + s);
        long msgId = message.getMessageProperties().getDeliveryTag();
        
    }
}
