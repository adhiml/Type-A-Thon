/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tapfinal2;


// files import
import java.io.IOException;

// timer import
import java.util.Timer;
import java.util.TimerTask;

//starting-as-soon-as-player-enter-input condition import
import java.awt.event.KeyEvent;             // tells the program which key is pressed
import java.awt.event.KeyListener;          // triggers the program to do a specific action when the key is pressed

// aesthetic / GUI import
import java.text.DecimalFormat;
import javax.swing.*;           // buttons, windows, labels
import java.awt.*;              // more basic GUI like colors, fonts etc

public class Game extends JFrame implements KeyListener{
    
    private long startTime;  // for big as hell integers
    private long time = 30;
    
    private static JLabel wordLabel;
    private static JLabel scoreLabel;
    private static JLabel accuracyLabel;
    private static JLabel wpmLabel;
    private static JLabel countdownLabel;
    
    private static RandomWordsGenerator g;
    
    
    public Game () throws IOException {
    super("Type-A-Thon");
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setLayout (new FlowLayout ());
    
    try {
            
            g = new RandomWordsGenerator ();
            
            System.out.println("Words succesfully loaded");
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    
    countdownLabel = new JLabel ("Time: " + time);
    add(countdownLabel);
    
    scoreLabel = new JLabel ("Score: " + score);
    add(scoreLabel);
    
    accuracyLabel = new JLabel ("Accuracy: ");
    add(accuracyLabel);
    
    wpmLabel = new JLabel ("WPM: ");
    add(wpmLabel);
    
    currentWord = g.displayRandomWords();
    wordLabel =  new JLabel(currentWord);
    add(wordLabel);
    
    addKeyListener(this);                                       // to tell the frame to pay attention to the keys input
    
    setFocusable(true);                                   // programming the frame to be an active recipient
                                                                  // input will be received without the user pressing "enter"
    
   setFocusTraversalKeysEnabled(false);       // allowing keys to not be used for navigation
    
    startGameTimer();
    
    setSize(300,200);
    setLocationRelativeTo(null);
    setVisible(true);
    
    } 
    
    private Timer countdownTimer;
    
    private void startCountdownTimer () {
        countdownTimer = new Timer ();
        startTime = System.currentTimeMillis();
        
        countdownTimer.schedule(new TimerTask() {
            
            @Override
            public void run () {
                time--;
                
                if (time >= 0) {
                    DecimalFormat df = new DecimalFormat ("00");
                    countdownLabel.setText ("Time: " + df.format(time));
                }
                else {
                    countdownTimer.cancel();
                    countdownLabel.setText("Time's up!");
                }
            }
            
            
        }, 0, 1000);
    }
    
    private int totalTypedCorrectly = 0;
    private boolean timeStarted = false;
    
    public void keyPressed (KeyEvent e) {
        
        
        if (!timeStarted) {
            startCountdownTimer();
            timeStarted = true;
        }
        
        if (time > 0) {
        if (e.getKeyChar() == currentWord.charAt(0) || e.getKeyChar() != currentWord.charAt(0)) {
            
            if (e.getKeyChar() == currentWord.charAt(0)) 
            {
                currentWord = currentWord.substring(1);
                wordLabel.setText(currentWord);              // updating characters

                if (currentWord.isEmpty()) {                     // keep up scores for correct words
                    updateScore();
                    updateWord();
                }
                
                totalTypedCorrectly++;                                   // for the correctly typed chars      
                updateAccuracy ();
            }
            totalTyped++;
            }
        }
    }
    
    private void startGameTimer () {
        
        Timer timer = new Timer (true); // set to true so that it'll work in the background
        startTime = System.currentTimeMillis();
        
        timer.scheduleAtFixedRate(new TimerTask() {  // new Timer Task is like saying, when the timer goes off, this is your task
            
            @Override
            public void run () {
                
                updateWPM();
            }
        }, 0, 30000);                    // update every 30 seconds
        
    }
    
    
    private void updateWPM () {
        
        long currentTime = System.currentTimeMillis();
        long elapsedTime = currentTime - startTime;  // elapsed time = amount of times that had went by
        double minutes = (double) elapsedTime / 60000;              // had to divide by 60000 to change seconds into minutes
        double wpm = score / minutes;                          // can change into int as well honestly
        
        DecimalFormat df = new DecimalFormat("#.##");
        wpmLabel.setText("WPM: " + df.format(wpm));
    }
    
    
    private static int score = 0;
    
    private static void updateScore() {
        score++;
        scoreLabel.setText("Score: " + score);
    }
    
    private int totalTyped = 0;
    
    private void updateAccuracy () {
        double accuracy = (double) totalTypedCorrectly / totalTyped * 100;     // to get percentage
        DecimalFormat df = new DecimalFormat("#.##");
        
        accuracyLabel .setText("Accuracy: " + df.format(accuracy));
    }
    
    
    public String currentWord;
    
    public void updateWord () {
        
        currentWord = g.displayRandomWords();
        wordLabel.setText(currentWord);      // for updating
    }

    
    // When implementing an interface (KeyListener), we need to declare
    // all of the methods whether used or not because it's part of the
    // "interface contract"
    
    @Override
    public void keyReleased(KeyEvent e) {
        // Unused
    }
    
    @Override
    public void keyTyped(KeyEvent e) {
        // Unused
    }
    
}
