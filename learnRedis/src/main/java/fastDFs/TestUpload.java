package fastDFs;

import org.csource.common.MyException;
import org.csource.common.NameValuePair;
import org.csource.fastdfs.*;

import java.io.IOException;

/**
 * @author Lihu
 * @PROJECT_NAME: learnDistributed
 * @DESCRIPTION:
 * @USER: Irene-Jisoo
 * @DATE: 2022/12/5 14:40
 */
public class TestUpload {
    public static void main(String[] args) {
        try {
            // 加载配置文件
            ClientGlobal.initByProperties("config/ fastdfs-client.properties");
            //创建tracker 客户端
            TrackerClient trackerClient = new TrackerClient();
            // 通过tracker 客户端获取tracker的连接服务并返回
            TrackerServer trackerServer = trackerClient.getConnection();
            // 声明 storage 服务
            StorageServer storageServer = null;
            // 定义storage 客户端
            StorageClient1 client = new StorageClient1(trackerServer, storageServer);
            //定义文件元信息
            NameValuePair[] list = new NameValuePair[1];
            list[0] = new NameValuePair("fileName", "1.jpg");
            String fileID = client.upload_appender_file1("C:\\Users\\流年安好\\Desktop\\Iren16.jpg", "jpg", list);
            System.out.println("fileID = " + fileID);
            trackerServer.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (MyException e) {
            throw new RuntimeException(e);
        }
    }
}
