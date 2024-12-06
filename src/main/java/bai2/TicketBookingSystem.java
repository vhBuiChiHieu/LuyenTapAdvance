package bai2;

import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class TicketBookingSystem {
    private static int soVe = 100;

    public static synchronized void datVe(String ten, int soLuong) {
        if (soVe >= soLuong) {
            soVe -= soLuong;
            System.out.println(ten + ": Dat Ve Thanh Cong " + soLuong + " Ve.");
        } else {
            System.out.println(ten + ": Dat Khong Thanh Cong " + soLuong + " Ve.");
        }
        System.out.println("So Ve Con Lai: " + soVe);
    }

    public static void main(String[] args) {
        //70 khach
        ExecutorService threadPool = Executors.newFixedThreadPool(70);
        for (int i = 0; i < 70; i++) {
            int index = i;
            threadPool.submit(new Runnable() {
                @Override
                public void run() {
                    String threadName = Thread.currentThread().getName();
                    datVe("Thread " + index, new Random().nextInt(1, 4));
                }
            });
        }
    }
}
