package mophongdownload;

import org.apache.log4j.Logger;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;

public class DownloadSim {
    private static final Logger logger = Logger.getLogger(DownloadSim.class);

    public static void main(String[] args) throws InterruptedException {
        final int MAX_QUEUE = 4;
        final int MAX_DOWNLOAD_THREAD = 2;
        //khoi tao danh sach file muon tai
        BlockingQueue<FileDownload> queue = new LinkedBlockingQueue<>(MAX_QUEUE);
        FileDownload[] files = {
                new FileDownload("File1", 3000),
                new FileDownload("File2", 7000),
                new FileDownload("File3", 1000),
                new FileDownload("File4", 12000),
                new FileDownload("File5", 5000),
                new FileDownload("File6", 5000),
                new FileDownload("File7", 9000),
                new FileDownload("File8", 10000),
                new FileDownload("File9", 2000),
                new FileDownload("File10", 9000)
        };
        //Them file vao danh sach tai
        Thread addFileToQueue = new Thread(() -> {
            for (FileDownload file : files) {
                try {
                    queue.put(file);
                    logger.info("add " + file.getName() + " to Queue\n" + queue);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        });
        addFileToQueue.start();
        //tai file trong queue
        ExecutorService downloadThreadPool = Executors.newFixedThreadPool(MAX_DOWNLOAD_THREAD);
        for (int i = 0; i < files.length; i++) {
            downloadThreadPool.submit(() -> {
                try {
                    FileDownload file = queue.take();
                    //Gia lap thoi gian tai
                    logger.info("Starting download " + file.toString() + ".");
                    Thread.sleep(file.timeDownload);
                    System.out.println("Download " + file.getName() + " hoan tat");
                    queue.notifyAll();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            });
        }
        downloadThreadPool.shutdown();
    }

    private static class FileDownload {
        private String name;
        private long timeDownload;

        public FileDownload(String name, long timeDownload) {
            this.name = name;
            this.timeDownload = timeDownload;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public long getTimeDownload() {
            return timeDownload;
        }

        public void setTimeDownload(long timeDownload) {
            this.timeDownload = timeDownload;
        }

        @Override
        public String toString() {
            return name + ":" + timeDownload;
        }
    }
}
