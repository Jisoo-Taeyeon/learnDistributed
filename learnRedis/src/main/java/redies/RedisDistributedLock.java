package redies;


import redis.clients.jedis.Jedis;

/**
 * 复述,分布式锁
 *
 * @author 流年安好
 * @date 2023/04/25
 */
public class RedisDistributedLock {
    private static final String LOCK_KEY = "my_lock";
    private static final int ACQUIRE_TIMEOUT = 10000; // 获取锁的超时时间，单位为毫秒
    private static final int LOCK_TIMEOUT = 5000; // 锁的过期时间，单位为毫秒

    private Jedis jedis;

    public RedisDistributedLock() {
        // 创建 Redis 连接
        jedis = new Jedis("localhost", 6379);
    }

    /**
     * 获得锁
     *
     * @return boolean
     */
    public boolean acquireLock() {
        long startTime = System.currentTimeMillis();
        while (true) {
            // 尝试设置键值对，当键不存在时设置成功，返回 1
            long result = jedis.setnx(LOCK_KEY, String.valueOf(System.currentTimeMillis() + LOCK_TIMEOUT));
            if (result == 1) {
                // 设置锁的过期时间
                jedis.pexpire(LOCK_KEY, LOCK_TIMEOUT);
                return true;
            } else if (jedis.pttl(LOCK_KEY) == -1) {
                // 如果锁没有设置过期时间，则设置过期时间
                jedis.pexpire(LOCK_KEY, LOCK_TIMEOUT);
            }

            long elapsedTime = System.currentTimeMillis() - startTime;
            if (elapsedTime > ACQUIRE_TIMEOUT) {
                // 超时未能获取锁，返回失败
                return false;
            }

            try {
                Thread.sleep(100); // 等待一段时间后重试
            } catch (InterruptedException e) {
                // 忽略中断异常
            }
        }
    }

    /**
     * 释放锁
     */
    public void releaseLock() {
        // 使用 Lua 脚本在 Redis 中原子地删除锁
        String script = "if redis.call('get', KEYS[1]) == ARGV[1] then return redis.call('del', KEYS[1]) else return 0 end";
        jedis.eval(script, 1, LOCK_KEY, jedis.get(LOCK_KEY));
    }

    public static void main(String[] args) {
        RedisDistributedLock lock = new RedisDistributedLock();
        if (lock.acquireLock()) {
            try {
                // 在锁保护的临界区执行需要保护的操作
                System.out.println("获取到锁，执行操作...");
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                // 忽略中断异常
            } finally {
                lock.releaseLock();
            }
        } else {
            System.out.println("获取锁失败");
        }
    }
}

