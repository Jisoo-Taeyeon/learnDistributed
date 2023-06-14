import redis.clients.jedis.Jedis;
import redis.clients.jedis.Transaction;

/**
 * @author Lihu
 * @PROJECT_NAME: learnDistributed
 * @DESCRIPTION: 模拟redis 事务
 * @USER: Irene-Jisoo
 * @DATE: 2022/11/4 9:57
 *
 */
public class AffairsTest {
    public static void main(String[] args) throws InterruptedException {
        Jedis jedis = new Jedis("192.168.190.129", 6379);
        int yue = Integer.parseInt(jedis.get("yue"));
        int zhichu = 10;
        jedis.watch("yue");
        Thread.sleep(10000);
        if (yue < zhichu) {
            // 解除监控
            jedis.unwatch();
            System.out.println("余额不足");

        } else {
            // 开启事务
            Transaction transaction = jedis.multi();
            //余额减少
            transaction.decrBy("yue", zhichu);
            transaction.incrBy("zhichu", zhichu);
            transaction.exec();
            System.out.println("余额：" + jedis.get("yue"));
            System.out.println("累计支出： " + jedis.get("zhichu"));
        } 
        
    }
}
