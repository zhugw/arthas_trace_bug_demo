package com.example.arthas_trace_bug_demo;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicLong;

@SpringBootApplication
public class ArthasTraceBugDemoApplication implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(ArthasTraceBugDemoApplication.class, args);
    }

    private BlockingQueue<Long> queue = new LinkedBlockingQueue<>();

    @Override
    public void run(String... args) throws Exception {
        // 生产者
        CompletableFuture.runAsync(() -> {
            long i = 0;
            while (true) {
                queue.add(i++);
                if (i % 2000 == 0) {
                    try {
                        Thread.sleep(500);
                    } catch (InterruptedException e) {
                    }
                }
            }
        });
        // 消费者
        final int nThreads = 6;
        ExecutorService threadPool = Executors.newFixedThreadPool(nThreads);
        AtomicLong total = new AtomicLong();
        for (int i = 0; i < nThreads; i++) {
            threadPool.submit(() -> {
                try {
                    while (true) {
                        List<Long> idList = new ArrayList<>();
                        for (int j = 0; j < 200; j++) {
                            idList.add(queue.poll(1, TimeUnit.SECONDS));
                        }
                        int result = process(idList);
                        long processedCount = total.addAndGet(result);
                        System.out.printf("Processed count: %d%n", processedCount);
                    }
                } catch (Throwable e) {
                    e.printStackTrace();
                    System.exit(-1);
                }

            });
        }
    }

    private int process(List<Long> idList) throws Throwable {
        Thread.sleep(100);
        System.out.printf("size: %d from: %d to: %d%n", idList.size(), idList.get(0), idList.get(idList.size() - 1));
        Thread.sleep(200);
        return idList.size();
    }
}
