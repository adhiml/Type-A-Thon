/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tapfinal2;

 
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

public class SuddenDeath extends JFrame implements KeyListener{
    
    private long startTime;  // for big as hell integers
    private long time = 30;
    
    private static JLabel wordLabel;
    private static JLabel scoreLabel;
    private static JLabel accuracyLabel;
    private static JLabel wpmLabel;
    private static JLabel countdownLabel;
    
    
    private static RandomWordsGenerator g;
    
   public SuddenDeath () throws IOException {
    super("Type-A-Thon");
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setLayout(null);

    try {
            
            g = new RandomWordsGenerator ();
            
            System.out.println("Words succesfully loaded");
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    
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
        

        currentWord = g.displayRandomWords();
        wordLabel = new JLabel(currentWord);
        wordLabel.setFont(new Font("Courier New",Font.BOLD, 30));
        wordLabel.setForeground(Color.white);
        wordLabel.setHorizontalAlignment(SwingConstants.CENTER);
        wordLabel.setBounds(50, 50, 300, 50);

        addKeyListener(this);
        setFocusable(true);
        setFocusTraversalKeysEnabled(false);

        startGameTimer();
        setSize(700, 400);
        setLocationRelativeTo(null);
        setVisible(true);

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
                if(time>0 && !gameOver){
                time--;
                
                if (time >= 0) {
                    DecimalFormat df = new DecimalFormat ("00");
                    countdownLabel.setText ("Time: " + df.format(time));
                }
                
                }
                else {
                    countdownTimer.cancel();
                    endGame();
                }
            }
            
            
        }, 0, 1000);
    }
    
    private int totalTypedCorrectly = 0;
    private boolean timeStarted = false;
    private boolean gameOver=false;
    
   public void keyPressed(KeyEvent e) {
        if (!timeStarted) {
            startCountdownTimer();
            timeStarted = true;
        }

        if (gameOver) {
            return; // Stop processing key events if the game is over
        }

        if (e.getKeyChar() != currentWord.charAt(0)) {
            gameOver = true;
            endGame();
            return; // Stop processing key events for incorrect key presses
        }

        if (time > 0) {
            currentWord = currentWord.substring(1);
            wordLabel.setText(currentWord);

            if (currentWord.isEmpty()) {
                totalTypedCorrectly++;
                updateScore();
                updateWord();
            }

            totalTyped++;
            updateAccuracy();
        } else {
            gameOver = true;
            endGame();
        }
    }
    
    private void endGame() {
        gameOver = true;
        countdownTimer.cancel();
        countdownLabel.setText("Game Over!");
        
        updateAccuracy();
        updateWPM();
    }
    
    private void startGameTimer () {
        
        Timer timer = new Timer (true); // set to true so that it'll work in the background
        startTime = System.currentTimeMillis();
        
        timer.scheduleAtFixedRate(new TimerTask() {  // new Timer Task is like saying, when the timer goes off, this is your task
            
            @Override
            public void run () {
               if (!gameOver) {
                    updateWPM();
                }
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