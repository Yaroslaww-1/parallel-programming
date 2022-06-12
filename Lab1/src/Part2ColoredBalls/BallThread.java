package Part2ColoredBalls;

import java.util.concurrent.Callable;

public class BallThread extends Thread {
    private Ball ball;
    private boolean isRunning;
    private final Callable onThreadStop;
    private BallThread secondaryThread;

    public BallThread(Ball ball, Callable onThreadStop) {
        this.ball = ball;
        isRunning = true;
        this.onThreadStop = onThreadStop;
    }

    public void setSecondaryThread(BallThread secondaryThread) {
        this.secondaryThread = secondaryThread;
    }

    public void run() {
        try {
            for (int i = 1; i < 10000; i++) {
                ball.move();

                if (secondaryThread != null) {
                    secondaryThread.join();
                }

//                System.out.println("Thread name = " + Thread.currentThread().getName() + " " + Thread.currentThread().getPriority());
                Thread.sleep(5);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}