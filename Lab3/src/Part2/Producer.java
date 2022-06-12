package Part2;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Producer implements Runnable {
    private Drop drop;
    private final int IMPORTANT_INFO_SIZE = 100;

    public Producer(Drop drop) {
        this.drop = drop;
    }

    public void run() {
        Random random = new Random();

        List<Integer> importantInfo = new ArrayList<>();
        for (int i = 0; i < IMPORTANT_INFO_SIZE; i++) {
            importantInfo.add(random.nextInt(5000));
        }

        for (int i = 0;
             i < importantInfo.size();
             i++) {
            drop.put(importantInfo.get(i));
            try {
                Thread.sleep(random.nextInt(500));
            } catch (InterruptedException e) {}
        }
        drop.put(-1);
    }
}
