

import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;

/**
 * @author Lihu
 * @PROJECT_NAME: learnDistributed
 * @DESCRIPTION:
 * @USER: Irene-Jisoo
 * @DATE: 2023/3/28 18:37
 */

public class QqSendMsg02 {
    public static void main(String[] args) {
        try {
            //机器类实现电脑自动
            Robot robot = new Robot();
            //Clipboard:实现使用剪切/复制/粘贴操作传输数据的机制的类。
            //getDefaultToolkit():获取默认工具包。
            //getSystemClipboard():获取与本地平台提供的剪贴板工具接口的系统剪贴板的单例实例。
            Clipboard systemClipboard = Toolkit.getDefaultToolkit().getSystemClipboard();

            //delay(int ms)睡在指定的时间。为了有时间让光标指向QQ\微信等
            //有六秒钟时间把光标移到QQ输入框，按下Ctrl+V+Enter
            robot.delay(6000);

            //编辑想发送的消息文字
            String msg = "你好,帅小伙,学Java吗";
            //split(String regex）:根据给定正则表达式的匹配拆分此字符串。
            //实现文字多行发送
            String[] strings = msg.split(",");
            //发100次，当然你可以死循环炸屏...
            for (int i = 0; i < 20; i++) {
                for (int j = 0; j < strings.length; j++) {

                    StringSelection word = new StringSelection(strings[j]);
                    //setContents(Transferable contents, ClipboardOwner owner)
                    // 将剪贴板的当前内容设置为指定的可转移对象，并将指定的剪贴板所有者注册为新内容的所有者。
                    systemClipboard.setContents(word, null);

                    //keyPress(int keycode):按一个给定的键。
                    //KeyEvent:指示组件中发生击键的事件。
                    //keyRelease(int keycode)释放给定的键
                    //按下键盘上的Ctrl
                    robot.keyPress(KeyEvent.VK_CONTROL);
                    //按下键盘上的V
                    robot.keyPress(KeyEvent.VK_V);
                    //松开键盘上的Ctrl
                    robot.keyRelease(KeyEvent.VK_CONTROL);
                    //松开键盘上的V
                    robot.keyRelease(KeyEvent.VK_V);
                    //按下Enter键
                    robot.keyPress(KeyEvent.VK_ENTER);
                    //松开Enter键
                    robot.keyRelease(KeyEvent.VK_ENTER);
                    //消息发出的时间间隔
                    robot.delay(100);
                }
            }
        } catch (AWTException e) {
            e.printStackTrace();
        }
    }
}