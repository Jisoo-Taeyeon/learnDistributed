package controller;

import org.redisson.Redisson;
import org.redisson.api.RLock;
import org.redisson.config.Config;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Objects;
import java.util.concurrent.TimeUnit;

/**
 * @author Lihu
 * @PROJECT_NAME: learnDistributed
 * @DESCRIPTION:
 * @USER: Irene-Jisoo
 * @DATE: 2022/11/4 10:48
 */
@Controller
public class TestKill {

    @Autowired
    private Redisson redisson;
    @Autowired
    private StringRedisTemplate stringRedisTemplate;
    @RequestMapping("kill")
    public @ResponseBody synchronized String kill(){
        String productKey = "XIAOMI-12PRO";
        //通过redisson 获取锁  底层源码就是继承了setnx，过期时间操作等
        RLock rLock = redisson.getLock(productKey);
        // 上锁  过期时间为30miao
        rLock.lock(30, TimeUnit.SECONDS);
        try {
            //从redis中获取手机的库存数量
            int phoneCount = Integer.parseInt(Objects.requireNonNull(stringRedisTemplate.opsForValue().get("phone")));
            //2.判断手机数量是否够秒杀的
            if (phoneCount > 0) {
                phoneCount--;
                //库存减少后，再将库存的值保存回redis
                stringRedisTemplate.opsForValue().set("phone", phoneCount + "");
                System.out.println("库存-1，剩余：" + phoneCount);

            } else {
                System.out.println("库存不足！");

            }
        } catch (NumberFormatException e) {
            e.printStackTrace();
        } finally {
            //释放锁
            rLock.unlock();
        }
        return "over";
    }
    @Bean
    public Redisson redisson(){
        Config config = new Config();
        //使用单个redis服务器
        config.useSingleServer().setAddress("").setDatabase(0);
        //使用集群redis
//        config.useClusterServers().setScanInterval(2000).addNodeAddress("", "", "");
        return (Redisson) Redisson.create(config);
    }
}
