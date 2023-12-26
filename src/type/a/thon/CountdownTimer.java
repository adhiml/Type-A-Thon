/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package type.a.thon;
//
//import java.awt.event.ActionListener;
//import java.util.concurrent.Executors;
//import java.util.concurrent.ScheduledExecutorService;
//import java.util.concurrent.TimeUnit;
//import javax.swing.JPanel;
//
//
//class Stopwatch extends JPanel implements ActionListener {
//    
//    private int countdown;
//    ScheduledExecutorService executorService = Executors.newSingleThreadScheduledExecutor();
//    
//    
//    private static final int countdownSecs = 30; // in seconds
//    
//    public Stopwatch ()
//    {
//        this.countdown = countdownSecs;
//    }
//    
//    
//    private static final int Initial_Delay = 0;
//    private static final int period = 1; // update every 1 second
//    
//    public void start ()
//    {
//        executorService.scheduleAtFixedRate(this :: updateCountdown, Initial_Delay, period, TimeUnit.SECONDS);
//    }
//    
//    private void updateCountdown ()
//    {
//        if (countdown > 0)
//        {
//            System.out.println("Time remaining:" + countdown + " seconds");
//            countdown--;
//        }
//        else
//        {
//            System.out.println("Time's up!");
//            executorService.shutdown();
//        }
//    }
//    
//}
import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class CountdownTimer extends JFrame {

    private JLabel label;
    private int counter = 30; // set initial to 30 seconds
    private Timer timer;

    public CountdownTimer() {
        label = new JLabel("Time Remaining: " + counter);
        add(label, BorderLayout.CENTER);

        // Initialize the timer
        timer = new Timer(1000, (actionEvent) -> {
            counter--;
            label.setText("Time Remaining: " + counter);
            if (counter <= 0) {
                timer.stop();
                label.setText("Time's up!");
            }
        });

        // Add KeyListener to the frame
        addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                super.keyPressed(e);
                startCountdown();
            }
        });

        setTitle("Timer");
        setSize(400, 100);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
    }

    public void startCountdown() {
        if (!timer.isRunning()) {
            timer.start();
        }
    }
}
