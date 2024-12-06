package counter;

public class TimeHandler implements Runnable{
    private int hour;
    private int minute;
    private int second;
    private boolean isRunning = true;
    private String timeToString(){
        return String.format("%02d:%02d:%02d", hour, minute, second);
    }
    @Override
    public void run() {
        while (isRunning){
            System.out.print("\r" + timeToString());
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                isRunning = false;
                break;
            }
            second = (second + 1) % 60;
            if (second == 0){
                minute = (minute + 1) % 60;
                if (minute == 0)
                    hour = (hour + 1) % 24;
            }
        }
    }
}
