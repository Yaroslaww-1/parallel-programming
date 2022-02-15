package Part2ColoredBalls;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicInteger;

public class BounceFrame extends JFrame {
    private BallCanvas canvas;
    private JTextArea ballsInPocketsTextArea = new JTextArea("Balls in pockets: 0");
    private AtomicInteger ballsInPockets = new AtomicInteger(0);
    private ArrayList<Thread> ballThreads = new ArrayList<>();
    public static final int WIDTH = 1450;
    public static final int HEIGHT = 1350;

    private void createBall(Ball ball) {
        canvas.add(ball);
        BallThread thread = new BallThread(ball, () -> {
            canvas.remove(ball);
            canvas.repaint();
            ballsInPocketsTextArea.setText("Balls in pockets: " + ballsInPockets.incrementAndGet());
            return null;
        });
        thread.setPriority(ball.isRed ? Thread.MAX_PRIORITY : Thread.MIN_PRIORITY);
        ballThreads.add(thread);
        System.out.println("Thread    name    =    " + thread.getName() + thread.getPriority());
    }

    private void startAllBalls() {
        for (var ballThread : ballThreads) {
            ballThread.start();
        }
        ballThreads.clear();
    }

    public BounceFrame() {
        this.setSize(WIDTH, HEIGHT);
        this.setTitle("Part2ColoredBalls.Bounce program");
        this.canvas = new BallCanvas();

        System.out.println("In Frame Thread name = " + Thread.currentThread().getName());

        Container content = this.getContentPane();
        content.add(this.canvas, BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel();
        buttonPanel.setBackground(Color.lightGray);

        var start1BlueBallsJoinButton = new JButton("1 Blue join");
        var start1RedAnd100BlueBallsButton = new JButton("1 Red And 100 Blue");
        var start1RedAnd4000BlueBallsButton = new JButton("1 Red And 4000 Blue");
        var stopButton = new JButton("Stop");

        start1BlueBallsJoinButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new Thread(() -> {
                    createBall(new Ball(canvas, false));
                    ballThreads.get(0).start();
                    try {
                        ballThreads.get(0).join();
                    } catch (InterruptedException ex) {
                        ex.printStackTrace();
                    }
                    ballThreads.clear();
                }).start();
            }
        });

        start1RedAnd100BlueBallsButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                createBall(new Ball(canvas, true));
                for (int i = 0; i < 100; i++) {
                    createBall(new Ball(canvas, false));
                }
                startAllBalls();
            }
        });

        start1RedAnd4000BlueBallsButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                createBall(new Ball(canvas, true));
                for (int i = 0; i < 4000; i++) {
                    createBall(new Ball(canvas, false));
                }
                startAllBalls();
            }
        });

        stopButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

        buttonPanel.add(start1BlueBallsJoinButton);
        buttonPanel.add(start1RedAnd100BlueBallsButton);
        buttonPanel.add(start1RedAnd4000BlueBallsButton);

        buttonPanel.add(stopButton);

        buttonPanel.add(ballsInPocketsTextArea);

        content.add(buttonPanel, BorderLayout.SOUTH);
    }
}