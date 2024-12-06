package mophongnhahang;

import java.util.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/*
 * tô tả: Khi khách gọi món, có Max_PRODUCER đầu bếp (producer).
 * Nấu mỗi món tốn ngẫu nhiên thời gian và đặt vào bàn (queue) Max_queue món tối đa.
 * có Max_CONSUMER nhân viên phục vụ (consumer) sẽ lấy món ăn từ bàn ra cho khách.
 */
public class Main {
    public static void main(String[] args) throws InterruptedException {
        String[] orders = {"Pizza", "Soup", "Mi Cay", "Kimchi", "Pho", "Bun Bo", "Ha Cao", "Mi Hai San", "Mi Tron", "Bun Ca", "Nem", "Ca Kho", "Ga Luoc", "Com Chay"};
        final int MAX_QUEUE = 4;
        final int Max_PRODUCER = 5;
        final int Max_CONSUMER = 2;
        Queue<String> foodQueue = new LinkedList<>();
        //5 dau bep
        ExecutorService producers = Executors.newFixedThreadPool(Max_PRODUCER);
        //3 phuc vu
        ExecutorService consumers = Executors.newFixedThreadPool(Max_CONSUMER);
        //
        for (int i = 0; i < orders.length; i++) {
            int index = i;
            producers.submit(() -> {
                Random random = new Random();
                String food = orders[index];
                try {
                    //giả định làm món ăn tốn ngẫu nhiên 4-9s
                    Thread.sleep(random.nextInt(4, 10) * 1000L);
                    synchronized (foodQueue) {
                        while (true) {
                            if (foodQueue.size() < MAX_QUEUE) {
                                foodQueue.add(food);
                                String producerName = Thread.currentThread().getName();
                                System.out.println("Producer " + producerName.charAt(producerName.length() - 1) + " da them " + food + " ");
                                foodQueue.notifyAll();
                                break;
                            } else {
                                foodQueue.wait();
                            }
                        }
                    }
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            });
            //
            consumers.submit(() -> {
                try {
                    synchronized (foodQueue) {
                        String food;
                        while (true) {
                            if (!foodQueue.isEmpty()) {
                                food = foodQueue.poll();
                                String consumerName = Thread.currentThread().getName();
                                System.out.print("\t\tConsumer " + consumerName.charAt(consumerName.length() - 1) + " da lay " + food + " ");
                                System.out.println(foodQueue);
                                foodQueue.notifyAll();
                                break;
                            } else {
                                foodQueue.wait();
                            }
                        }
                    }
                    //Thời gian di chuyển sau khi lấy đồ ăn là khoảng 3-5s
                    Thread.sleep(new Random().nextInt(3, 6) * 1000L);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            });
            //Khoảng cách giữa mỗi lần order là 1-2 s vì gọi liền mạch các món trong danh sách
            Thread.sleep(new Random().nextInt(1, 3) * 1000L);
        }
        producers.shutdown();
        consumers.shutdown();
        producers.awaitTermination(1, TimeUnit.MINUTES);
        consumers.awaitTermination(1, TimeUnit.MINUTES);

        System.out.println("Chuong trinh ket thuc");
    }
}
