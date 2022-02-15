package Part2ColoredBalls;

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
//            Thread.currentThread().setPriority(ball.isRed ? Thread.MAX_PRIORITY : Thread.MIN_PRIORITY);

            for (int i = 1; i < 10000; i++) {
                ball.move();

//                System.out.println("Thread name = " + Thread.currentThread().getName() + " " + Thread.currentThread().getPriority());
                Thread.sleep(5);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}