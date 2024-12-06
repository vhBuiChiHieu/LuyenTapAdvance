package queue.bai1;

import java.util.Scanner;

public class Costumer {
    private String name;
    private int age;
    private String address;

    public void getInfo() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Name: ");
        name = scanner.nextLine();
        System.out.print("Age: ");
        age = scanner.nextInt();
        scanner.nextLine();
        System.out.print("Address: ");
        address = scanner.nextLine();
    }

    public void showInfo() {
        System.out.println("\nName: " + name);
        System.out.println("Age: " + age);
        System.out.println("Address: " + address + "\n");
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
