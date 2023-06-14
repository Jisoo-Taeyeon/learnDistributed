package redies;

import redis.clients.jedis.Jedis;

/**
 * @author Lihu
 * @PROJECT_NAME: lagou-dubbo
 * @DESCRIPTION:
 * @USER: Irene-Jisoo
 * @DATE: 2022/11/2 9:53
 */
public class Test_JedisPool {
    public static void main(String[] args) {
        Jedis jedis = JedisPoolUtil.getJedis();
        Jedis jedis1 = JedisPoolUtil.getJedis();
        System.out.println(jedis + "   " + jedis1);
        System.out.println(jedis == jedis1);
    }
}
