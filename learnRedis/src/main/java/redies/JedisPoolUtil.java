package redies;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

/**
 * @author Lihu
 * @PROJECT_NAME: lagou-dubbo
 * @DESCRIPTION:
 * @USER: Irene-Jisoo
 * @DATE: 2022/11/1 15:57
 */
public class JedisPoolUtil {
    private JedisPoolUtil(){}

    private volatile static JedisPool jedisPool = null;
    private volatile static Jedis jedis = null;

    private static JedisPool getInstance(){
        if (jedisPool == null) {
            synchronized (JedisPoolUtil.class) {
                if (jedisPool == null) {
                    JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
                    jedisPoolConfig.setMaxTotal(1000);
                    jedisPoolConfig.setMaxIdle(30);
                    jedisPoolConfig.setMaxWaitMillis(60*1000);
                    jedisPoolConfig.setTestOnBorrow(true);
                    jedisPool = new JedisPool(jedisPoolConfig, "192.168.190.129", 6379);
                }
            }
        }
        return jedisPool;
    }
    public static Jedis getJedis(){
        if (jedis == null) {
            jedis = getInstance().getResource();
        }
        return jedis;
    }
}
