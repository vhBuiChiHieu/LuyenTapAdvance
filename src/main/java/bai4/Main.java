package bai4;

public class Main {
    public static void main(String[] args) {
        SharedData sharedData = new SharedData();
        Thread1 thread1 = new Thread1(sharedData);
        Thread2 thread2 = new Thread2(sharedData);
        Thread3 thread3 = new Thread3(sharedData);
        thread1.start();
        thread2.start();
        thread3.start();
        try {
            thread1.join();
            thread2.join();
            thread3.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Chuong trinh ket thuc");
    }
}
