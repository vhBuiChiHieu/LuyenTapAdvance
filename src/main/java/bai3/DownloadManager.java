package bai3;

import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class DownloadManager {
    private ExecutorService service;
    private final int[] listDownload;
    private final int SPEED;
    private final Random random;
    private final ExecutorService downloadThreads;

    public DownloadManager() {
        random = new Random();
        SPEED = 25;
        listDownload = new int[]{getRandom(), getRandom(), getRandom(), getRandom(), getRandom()};
        downloadThreads = Executors.newFixedThreadPool(listDownload.length);
    }

    private int getRandom() {
        return random.nextInt(100, 501);
    }

    public void run() {
        for (int i = 0; i < listDownload.length; i++) {
            int index = i;
            downloadThreads.submit(() -> {
                int dataSize = listDownload[index];
                int dataDownloaded = 0;
                int percent;
                System.out.println("Thread[" + index + "] bat dau tai file " + (index + 1) + ":" + dataSize + "MB");
                while (dataDownloaded < dataSize){
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        System.out.println("Thread[" + index + "] bi gian doan");
                        Thread.currentThread().interrupt();
                        return;
                    }
                    dataDownloaded += SPEED;
                    percent = Math.min(100, (dataDownloaded * 100 / dataSize));
                    System.out.println("Thread[" + index + "]: " + percent + "%");
                }
                System.out.println("\nFile " + (index + 1) + ":" + dataSize + "MB Da Tai Xong!\n");
            });
        }
        //dung nhan task
        downloadThreads.shutdown();
    }

    public static void main(String[] args) {
        new DownloadManager().run();
    }
}
