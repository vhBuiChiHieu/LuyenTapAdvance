package counter;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        TimeHandler timeHandler = new TimeHandler();
        Thread timeCounter = new Thread(timeHandler);

        timeCounter.start();
        //
        new Scanner(System.in).nextLine();
        timeCounter.interrupt();
    }
}
