package Part2;

import java.util.Random;

public class Consumer implements Runnable {
    private Drop drop;

    public Consumer(Drop drop) {
        this.drop = drop;
    }

    public void run() {
        Random random = new Random();
        int receivedTotal = 0;
        for (int message = drop.take();
             message != -1;
             message = drop.take()) {
            receivedTotal++;
            System.out.format("MESSAGE RECEIVED: %s, TOTAL RECEIVED: %s%n", message, receivedTotal);
            try {
                Thread.sleep(random.nextInt(500));
            } catch (InterruptedException e) {}
        }
    }
}
