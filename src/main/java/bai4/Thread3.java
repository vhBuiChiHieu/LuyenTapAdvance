package bai4;

public class Thread3 extends Thread {
    private final SharedData data;

    public Thread3(SharedData data) {
        this.data = data;
    }

    @Override
    public void run() {
        try {
            synchronized (data) {

                while (data.checkAvailable()) {
                    while (data.getFlag() != 3) {
                        data.wait();    //
                    }
                    if (data.checkAvailable()) {
                        int number = data.getRandomNumber();
                        if (number % 4 == 0) {
                            System.out.println("T3 >> " + number + " chia het cho 4");
                        } else {
                            System.out.println("T3 >> " + number + " khong chia het cho 4");
                        }
                        data.setFlag(1);
                    }
                    data.notifyAll();
                }
            }

            System.out.println("T3 >> end");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
