package bai4;

public class Thread2 extends Thread {
    private final SharedData data;

    public Thread2(SharedData data) {
        this.data = data;
    }

    @Override
    public void run() {
        try {
            synchronized (data) {

                while (data.checkAvailable()) {
                    while (data.getFlag() != 2) {
                        data.wait(); //
                    }
                    if (data.checkAvailable()) {
                        int number = data.getRandomNumber();
                        System.out.println("T2 >> " + (number * number));
                        data.setFlag(1);
                    }
                    data.notifyAll();
                }

                data.setFlag(3);
                data.notifyAll();
                System.out.println("T2 >> end");
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
