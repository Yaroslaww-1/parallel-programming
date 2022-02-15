package Part1;

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
    public static final int WIDTH = 900;
    public static final int HEIGHT = 900;

    private void createBall(Ball ball) {
        canvas.add(ball);
        BallThread thread = new BallThread(ball, () -> {
            canvas.remove(ball);
            canvas.repaint();
            ballsInPocketsTextArea.setText("Balls in pockets: " + ballsInPockets.incrementAndGet());
            return null;
        });
        thread.start();
        System.out.println("Thread    name    =    " + thread.getName() + thread.getPriority());
    }

    public BounceFrame() {
        this.setSize(WIDTH, HEIGHT);
        this.setTitle("Part1.Bounce program");
        this.canvas = new BallCanvas();

        System.out.println("In Frame Thread name = " + Thread.currentThread().getName());

        Container content = this.getContentPane();
        content.add(this.canvas, BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel();
        buttonPanel.setBackground(Color.lightGray);

        var startBallButton = new JButton("Create Ball");
        var stopButton = new JButton("Stop");

        startBallButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                createBall(new Ball(canvas));
            }
        });

        stopButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

        buttonPanel.add(startBallButton);
        buttonPanel.add(stopButton);

        buttonPanel.add(ballsInPocketsTextArea);

        content.add(buttonPanel, BorderLayout.SOUTH);
    }
}