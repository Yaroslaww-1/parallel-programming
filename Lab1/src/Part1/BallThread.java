package Part1;

import java.util.concurrent.Callable;

public class BallThread extends Thread {
    private Ball ball;
    private boolean isRunning;
    private final Callable onThreadStop;

    public BallThread(Ball ball, Callable onThreadStop) {
        this.ball = ball;
        isRunning = true;
        this.onThreadStop = onThreadStop;
    }

    public void run() {
        try {
            for (int i = 1; i < 50000; i++) {
                if (!isRunning) {
                    this.onThreadStop.call();
                    break;
                }

                ball.move();

                var isInPocket = ball.isInPocket();
                if (isInPocket) {
                    isRunning = false;
                }

//                System.out.println("Thread name = " + Thread.currentThread().getName() + " " + Thread.currentThread().getPriority());
                Thread.sleep(5);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}