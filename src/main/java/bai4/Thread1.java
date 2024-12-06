package bai4;

import java.util.Random;

public class Thread1 extends Thread {
    private final SharedData data;

    public Thread1(SharedData data) {
        this.data = data;
    }

    @Override
    public void run() {
        try {
            synchronized (data) {

                while (data.checkAvailable()) {
                    while (data.getFlag() != 1) {
                        data.wait();    //
                    }
                    Random random = new Random();
                    int randomNumber = random.nextInt(100) + 1;
                    data.setRandomNumber(randomNumber);
                    System.out.println("T1 >> " + randomNumber + " [" + data.getTotal() + "]");
                    if (randomNumber % 3 == 0) {
                        data.setFlag(2);
                    } else if (randomNumber % 2 == 0) {
                        data.setFlag(3);
                    }
                    data.notifyAll();
                }

                System.out.println("T1 >> end");
                data.setFlag(2);
                data.notifyAll();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
