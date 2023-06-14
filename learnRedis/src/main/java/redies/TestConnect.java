package redies;

import redis.clients.jedis.Jedis;

/**
 * @author Lihu
 * @PROJECT_NAME: lagou-dubbo
 * @DESCRIPTION:
 * @USER: Irene-Jisoo
 * @DATE: 2022/11/1 15:23
 */
public class TestConnect {
    public static void main(String[] args) {
//        Jedis jedis = new Jedis("127.0.0.1", 6379);
        Jedis jedis = new Jedis("1.15.121.102", 6379);
        jedis.auth("2292410586");
        String ping = jedis.ping();
        System.out.println(ping);
    }

}
