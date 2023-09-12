package org.example.thread_mianshi;
import java.util.HashMap;
import java.util.Map;
/**
 * @author Lihu
 * @PROJECT_NAME: learnDistributed
 * @DESCRIPTION:
 * @USER: Irene-Jisoo
 * @DATE: 2023/9/5 19:42
 * 要防止用户恶意地重复发送验证码，你可以采取以下思路和关键代码：
 *
 * 设置发送频率限制：限制同一手机号发送验证码的频率，确保用户不能在短时间内多次请求验证码。
 *
 * 验证码有效期：确保验证码的有效期不过长，一般情况下验证码的有效期应该在几分钟到十几分钟之间，这样即使用户多次请求验证码，之前的验证码也会在一定时间后失效。
 *
 * 记录发送次数：记录每个手机号请求验证码的次数，如果超过一定次数就不再发送验证码。
 *
 * 图形验证码：引入图形验证码，要求用户在请求短信验证码之前先通过图形验证码的验证，这可以有效减少自动化程序的恶意请求。
 *
 * 下面是一个简单的示例代码，演示如何实现验证码发送的频率限制和记录发送次数：
 */
public class SMSVerificationService {
    private Map<String, Integer> requestCounts = new HashMap<>();
    private Map<String, Long> lastRequestTimestamps = new HashMap<>();

    // 发送短信验证码，带有频率限制和发送次数记录
    public boolean sendVerificationCode(String phoneNumber) {
        // 检查是否超过发送次数限制
        int maxRequestCount = 3; // 最大请求次数
        if (requestCounts.containsKey(phoneNumber) && requestCounts.get(phoneNumber) >= maxRequestCount) {
            return false; // 超过发送次数限制，不再发送
        }

        // 检查发送频率
        long minRequestInterval = 60 * 1000; // 60秒，最小请求间隔时间
        if (lastRequestTimestamps.containsKey(phoneNumber)) {
            long lastRequestTime = lastRequestTimestamps.get(phoneNumber);
            long currentTime = System.currentTimeMillis();
            if (currentTime - lastRequestTime < minRequestInterval) {
                return false; // 发送频率过快，不再发送
            }
        }

        // 生成并发送验证码逻辑（这里省略具体发送逻辑）
        String verificationCode = generateVerificationCode();
        sendSMS(phoneNumber, verificationCode);

        // 记录请求次数和时间
        requestCounts.put(phoneNumber, requestCounts.getOrDefault(phoneNumber, 0) + 1);
        lastRequestTimestamps.put(phoneNumber, System.currentTimeMillis());

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
        SMSVerificationService smsService = new SMSVerificationService();

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

