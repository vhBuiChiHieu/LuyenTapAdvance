package queue.bai1;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Manager {
    private final Queue<Costumer> queue;
    private final Scanner scanner;
    public Manager(){
        queue = new LinkedList<>();
        scanner = new Scanner(System.in);
    }
    private void addCostumer(){
        Costumer tmpCostumer = new Costumer();
        tmpCostumer.getInfo();
        if (queue.offer(tmpCostumer)){
            System.out.println("\nThem Thanh Cong.\n");
        } else {
            System.out.println("\nThem That Bai.\n");
        }
    }
    private void getCostumer(){
        Costumer tmpCostumer;
        if ((tmpCostumer = queue.poll()) != null){
            tmpCostumer.showInfo();
        } else {
            System.out.println("\nKhong The Lay Thong Tin Khach Hang\n");
        }
    }
    private void getQueue(){
        if (queue.isEmpty()){
            System.out.println("\nDanh Sach Cho Dang Trong.\n");
            return;
        }
        for (Costumer costumer : queue){
            costumer.showInfo();
        }
    }
    public void run(){
        int option;
        do {
            System.out.println("---------------Menu--------------");
            System.out.println("1, Them khach hang.");
            System.out.println("2, Phuc vu khach hang hien tai.");
            System.out.println("3, Hien thi danh sach cho.");
            System.out.println("4, Thoat.");
            System.out.println("---------------------------------");
            option = scanner.nextInt();
            scanner.nextLine();
            switch (option) {
                case 1:
                    addCostumer();
                    break;
                case 2:
                    getCostumer();
                    break;
                case 3:
                    getQueue();
                    break;
                case 4:
                    break;
                default:
                    System.out.println("Lua chon khong hop le.");
            }
        } while (option != 4);
    }

    public static void main(String[] args) {
        new Manager().run();
    }
}
