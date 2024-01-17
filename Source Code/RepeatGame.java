/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tapfinal2;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

/**
 *
 * @author saada
 */
public class RepeatGame extends JFrame implements KeyListener{
    
    private long startTime;  // for big as hell integers
    private long time = 30;
    
    private static JLabel wordLabel;
    private static JLabel scoreLabel;
    private static JLabel accuracyLabel;
    private static JLabel wpmLabel;
    private static JLabel countdownLabel;
    
    private static RandomWordsGenerator g;
    private UserData userData;
    
    public RepeatGame (Game game) throws IOException {
    super("Type-A-Thon");
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setLayout (null);
    
    FileManager fileManager = new FileManager();
    userData = new UserData ();
    UserData existingUserData = fileManager.loadUserData(Profile.username);
    
    if (existingUserData != null) {
        userData = existingUserData;
    }
    
    sameWordsList = game.sameWordsList;
    
        ImageIcon image =new ImageIcon("racing2.jpg");

    
   countdownLabel = new JLabel("Time: " + time);
        countdownLabel.setFont(new Font("Gill Sans Ultra Bold",Font.PLAIN, 15));
        countdownLabel.setForeground(new Color(0x881E36));

        scoreLabel = new JLabel("Score: " + score);
        scoreLabel.setHorizontalAlignment(SwingConstants.RIGHT);
        scoreLabel.setFont(new Font("Courier New",Font.BOLD, 15));
        scoreLabel.setForeground(new Color(0x294361));

        accuracyLabel = new JLabel("Accuracy: ");
        accuracyLabel.setHorizontalAlignment(SwingConstants.CENTER);
        accuracyLabel.setFont(new Font("Courier New",Font.BOLD, 15));
        accuracyLabel.setForeground(new Color(0x294361));

        wpmLabel = new JLabel("WPM: ");
        wpmLabel.setHorizontalAlignment(SwingConstants.LEFT);
        wpmLabel.setFont(new Font("Courier New",Font.BOLD, 15));
        wpmLabel.setForeground(new Color(0x294361));       
        
        JLabel label=new JLabel();
        label.setText("Default Game");
        label.setFont(new Font("Gill Sans Ultra Bold", Font.PLAIN,23));
        label.setForeground(new Color(0x6C4506));
        label.setBounds(260,75,300,20);
        
        JPanel panel1 = new JPanel();//word
        panel1.setBackground(new Color(0x072533));
        panel1.setBounds(180, 130, 300, 100);
        panel1.setLayout(new BorderLayout());

        JPanel panel2 = new JPanel();// grey panel
        panel2.setBackground(Color.LIGHT_GRAY);
        panel2.setBounds(20, 310, 635, 30);
        panel2.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 5));

        JPanel panel3 = new JPanel();// pink panel
        panel3.setBackground(new Color(0xE9CDCE));
        panel3.setBounds(280, 260, 120, 30);
        panel3.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 5));
                       
        JLayeredPane layer1 = new JLayeredPane();
        layer1.setBounds(0, 0, 680, 375);
        layer1.setBorder(BorderFactory.createMatteBorder(20, 20, 20, 25, image));
        
    
    currentWord = updateWord();
    wordLabel = new JLabel(currentWord);
        wordLabel.setFont(new Font("Courier New",Font.BOLD, 30));
        wordLabel.setForeground(Color.white);
        wordLabel.setHorizontalAlignment(SwingConstants.CENTER);
        wordLabel.setBounds(50, 50, 300, 50);

        
    addKeyListener(this);                                       // to tell the frame to pay attention to the keys input
    
    setFocusable(true);                                   // programming the frame to be an active recipient
                                                                  // input will be received without the user pressing "enter"
    
    setFocusTraversalKeysEnabled(false);       // allowing keys to not be used for navigation
    
    startGameTimer();
    
    setSize(700, 400);
        setLocationRelativeTo(null);
        setVisible(true);
        
        add(label);
        add(countdownLabel);
        add(scoreLabel);
        add(accuracyLabel);
        add(wpmLabel);
        add(wordLabel);
        add(layer1);
        add(panel1);
        add(panel2);
        add(panel3);
        //add(panel4);
        panel1.add(wordLabel, BorderLayout.CENTER);
        panel2.add(scoreLabel);
        panel2.add(accuracyLabel);
        panel2.add(wpmLabel);
        panel3.add(countdownLabel);
        getContentPane().setBackground(new Color(0x9DD4D1));
    
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
                    try {
                        endGame();
                    } catch (IOException ex) {
                        System.out.println(ex.getMessage());
                    }
                }
            }
            
            
        }, 0, 1000);
    }
    
    private void endGame () throws IOException {
        TypingSession.updateUserData(Profile.username, userData, getWpm(), getAccuracy(),incorrectWords);
        String username = Profile.getUsername();
        saveScore scoreSaver = new saveScore(username);
        scoreSaver.saveScore();
    }
    
    
    private int totalTyped = 0;
    private int totalTypedCorrectly = 0;
    private boolean timeStarted = false;
    private String OGWord;
    static List<String> incorrectWords = new ArrayList<>();
    
    public void keyPressed (KeyEvent e) {
        
        if (!timeStarted) {
            startCountdownTimer();
            timeStarted = true;
        }
        
        if (time > 0) {
            
        if (e.getKeyChar() == currentWord.charAt(0) || e.getKeyChar() != currentWord.charAt(0)) {
            
            if (e.getKeyChar() != currentWord.charAt(0)){
                incorrectWords.add(OGWord);
            }
            else
            {
                currentWord = currentWord.substring(1);
                wordLabel.setText(currentWord);              // updating characters
                
                if (currentWord.isEmpty()) {
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
    
    
    private void updateAccuracy () {
        double accuracy = (double) totalTypedCorrectly / totalTyped * 100;     // to get percentage
        DecimalFormat df = new DecimalFormat("#.##");
        
        accuracyLabel .setText("Accuracy: " + df.format(accuracy));
    }
    
    
    public String currentWord;
    Random r = new Random();
    private List<String> sameWordsList;
    
    public String updateWord() {
    if (!sameWordsList.isEmpty()) {
        currentWord = sameWordsList.get(r.nextInt(sameWordsList.size()));
        OGWord = currentWord;

        if (wordLabel != null) {
            wordLabel.setText(currentWord);
        } else {
            System.out.println("wordLabel in updateWord is null");
        }
    } else {
        // Handle the case when sameWordsList is empty
        System.out.println("sameWordsList is empty");
    }

    return currentWord;
}
    
    
    private static double wpm;
    private static double accuracy;
    
    public static double getWpm() {
        return wpm;
    }

    public static double getAccuracy() {
        return accuracy;
    }
    
    public String getCurrentWord() {
        return currentWord;
    }

    public void setCurrentWord(String currentWord) {
        this.currentWord = currentWord;
    }

    public JLabel getWordLabel() {
        return wordLabel;
    }

    public JLabel getCountdownLabel() {
        return countdownLabel;
    }

    public long getTime() {
        return time;
    }
    
    // When implementing an interface (KeyListener), we need to declared the 
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
