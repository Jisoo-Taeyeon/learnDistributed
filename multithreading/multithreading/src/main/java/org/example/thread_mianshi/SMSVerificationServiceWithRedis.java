package org.example.thread_mianshi;

/**
 * @author Lihu
 * @PROJECT_NAME: learnDistributed
 * @DESCRIPTION: 使用redis 防止短信多发
 * @USER: Irene-Jisoo
 * @DATE: 2023/9/5 19:45
 */
import redis.clients.jedis.Jedis;

public class SMSVerificationServiceWithRedis {
    private Jedis jedis; // Redis客户端

    public SMSVerificationServiceWithRedis() {
        // 连接到本地的Redis服务器，默认端口为6379
        jedis = new Jedis("localhost", 6379);
    }

    // 发送短信验证码，带有频率限制和发送次数记录
    public boolean sendVerificationCode(String phoneNumber) {
        int maxRequestCount = 3; // 最大请求次数
        long minRequestInterval = 60; // 60秒，最小请求间隔时间

        // 获取上一次请求的时间和请求次数
        String key = "sms:" + phoneNumber;
        String lastRequestTime = jedis.hget(key, "last_request_time");
        String requestCount = jedis.hget(key, "request_count");

        long currentTime = System.currentTimeMillis() / 1000; // 转为秒

        // 如果没有记录或者已过间隔时间，则初始化记录
        if (lastRequestTime == null || (currentTime - Long.parseLong(lastRequestTime)) >= minRequestInterval) {
            jedis.hset(key, "last_request_time", String.valueOf(currentTime));
            jedis.hset(key, "request_count", "1");
            jedis.expire(key, (int) minRequestInterval);
        } else {
            int count = Integer.parseInt(requestCount);
            if (count >= maxRequestCount) {
                return false; // 超过发送次数限制，不再发送
            }

            jedis.hincrBy(key, "request_count", 1); // 增加请求次数
        }

        // 生成并发送验证码逻辑（这里省略具体发送逻辑）
        String verificationCode = generateVerificationCode();
        sendSMS(phoneNumber, verificationCode);

        return true;
    }

    // 生成随机验证码（示例）
    private String generateVerificationCode() {
        // 实际应用中可以生成随机数字验证码
        return "123456";
    }

    // 发送短信验证码（示例，需要替换为实际的短信发送逻辑）
    private void sendSMS(String phoneNumber, String code) {
        System.out.println("Sending SMS to " + phoneNumber + ": " + code);
    }

    public static void main(String[] args) {
        SMSVerificationServiceWithRedis smsService = new SMSVerificationServiceWithRedis();

        // 模拟用户请求验证码
        String phoneNumber = "1234567890";
        boolean sent = smsService.sendVerificationCode(phoneNumber);

        if (sent) {
            System.out.println("Verification code sent successfully.");
        } else {
            System.out.println("Failed to send verification code due to rate limit.");
        }
    }
}

