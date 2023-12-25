/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package type.a.thon;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class Stopwatch {
    
    private static final int Initial_Delay = 0;
    private static final int period = 1; // update every 1 second
    private static final int countdownSecs = 30; // in seconds
    
    private int countdown;
    ScheduledExecutorService executorService = Executors.newSingleThreadScheduledExecutor();
    
    public Stopwatch ()
    {
        this.countdown = countdownSecs;
    }
    
    public void start ()
    {
        executorService.scheduleAtFixedRate(this :: updateCountdown, Initial_Delay, period, TimeUnit.SECONDS);
    }
    
    private void updateCountdown ()
    {
        if (countdown > 0)
        {
            System.out.println("Time remaining:" + countdown + " seconds");
            countdown--;
        }
        else
        {
            System.out.println("Time's up!");
            executorService.shutdown();
        }
    }
}
