package src;

public class Clock {
    private long startTime = -1;
    private boolean running = false;

    public Clock() {
    }

    public void start() {
        if (!running) {
            startTime = System.currentTimeMillis();
            running = true;
        }
    }

    public long getElapsedTime() {
        if (running) {
            return System.currentTimeMillis() - startTime;
        }
        return 0;
    }
    public long getDownCountingTime() {
        if (running) {
            return startTime - System.currentTimeMillis();
        }
        return 0;
    }

    public String getFormattedTime() {
        long millis = getElapsedTime();
        long seconds = millis / 1000;
        long minutes = seconds / 60;
        seconds %= 60;
        return String.format("%02d:%02d", minutes, seconds);
    }
    public String getFormattedCountdown() {
        long millis = getElapsedTime();
        long seconds = millis / 1000;
        long minutes = seconds / 60;
        seconds %= 60;
        return String.format("%02d:%02d", minutes, seconds);
    }

    public boolean isRunning() {
        return running;
    }
}
