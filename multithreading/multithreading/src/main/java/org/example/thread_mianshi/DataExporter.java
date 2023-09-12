package org.example.thread_mianshi;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
/**
 * @author Lihu
 * @PROJECT_NAME: learnDistributed
 * @DESCRIPTION:
 * @USER: Irene-Jisoo
 * @DATE: 2023/9/5 19:38
 */

public class DataExporter {
    // 模拟数据库查询函数，返回数据总量和指定页的数据
    public static CompletableFuture<List<String>> queryDataAsync(int page, int pageSize) {
        // 实际应用中需要连接数据库并执行查询操作
        // 这里简化为返回一些示例数据
        List<String> data = new ArrayList<>();
        for (int i = (page - 1) * pageSize + 1; i <= page * pageSize; i++) {
            data.add("Row " + i);
        }

        return CompletableFuture.completedFuture(data);
    }

    public static void main(String[] args) {
        int totalPages = 10; // 总页数
        int pageSize = 100; // 每页数据量

        ExecutorService executorService = Executors.newFixedThreadPool(5); // 创建线程池

        List<CompletableFuture<List<String>>> futures = new ArrayList<>();

        // 异步查询并导出数据
        for (int page = 1; page <= totalPages; page++) {
            CompletableFuture<List<String>> future = queryDataAsync(page, pageSize)
                    .thenApplyAsync(data -> {
                        // 处理导出逻辑，例如将数据写入文件或导出到Excel
                        System.out.println("Exporting page " + page + "...");
                        return data;
                    }, executorService);

            futures.add(future);
        }

        // 等待所有导出操作完成
        CompletableFuture<Void> allOf = CompletableFuture.allOf(futures.toArray(new CompletableFuture[0]));

        allOf.thenRun(() -> {
            // 所有导出操作完成后的处理
            System.out.println("All export tasks completed.");
            executorService.shutdown(); // 关闭线程池
        });

        // 阻塞主线程，等待所有导出操作完成
        allOf.join();
    }
}
