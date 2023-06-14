package RabbitMQ;

import com.rabbitmq.client.*;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * @author Lihu
 * @PROJECT_NAME: learnDistributed
 * @DESCRIPTION: 消费生产者  简单模式
 * @USER: Irene-Jisoo
 * @DATE: 2022/12/15 15:49
 *
 */
public class Sender {
    public static void main(String[] args) throws IOException, TimeoutException {
        String msg = " red velvet";
        //1、 获得连接
        Connection connection = ConnectionUtil.getConnection();
        //2、 获得通道 （信道）
        Channel channel = connection.createChannel();
        //3、 创建消息队列（1，2,3,4,5）
        /*
            参数1:队列的名称
            参数2:队列中的数据是否持久化
            参数3:是否排外（是否支持扩展，当前队列只能自己用，不能给别人用）
            参数4:是否自动删除（当队列的连接数为0时，队列会销毁，不管队列是否还存保存数据）
            参数5:队列参数（没有参数为null）
        */
        channel.queueDeclare("queuel", false, false, false, null);
        //4、向指定的队列发送消息（1,2,3,4）
        /**
         * 参数1:交换机名称，当前是简单模式，也就是P2P模式，没有交换机，所以名称为""
         * 参数2:目标队列的名称
         * 参数3:设置消息的属性（没有属性则为null）
         * 参数4:消息的内容(只接收字节数组)
         */
        channel.basicPublish("", "queuel", null, msg.getBytes());
        System.out.println("发送：" + msg);
        //5、 释放资源
        channel.close();
        connection.close();

    }
}
