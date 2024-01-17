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
import java.util.List;
import static tapfinal2.Game.incorrectWords;

public class Words extends JFrame implements KeyListener{
    
    private long startTime;  // for big as hell integers
    private long time = 30;
    private int numwords;
    
    private static JLabel wordLabel;
    private static JLabel scoreLabel;
    private static JLabel accuracyLabel;
    private static JLabel wpmLabel;
    private static JLabel countdownLabel;
    
    private static RandomWordsGenerator g;
    private UserData userData;
    
    public Words () throws IOException {
        super("Type-A-Thon");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout (null);
        
        FileManager fileManager = new FileManager ();
        userData = new UserData ();
        UserData existingUserData = fileManager.loadUserData(Profile.username);
        
        if (existingUserData != null) {
            userData = existingUserData;
        }
        
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

        getWordsToTypeFromUser();
        startGameStopwatch();      
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

    
    
    
   
    public void getWordsToTypeFromUser() {
        boolean validInput = false;
        
        while (!validInput) {
            try {
                // Prompt the user to enter the number of words to type
                String userInput = JOptionPane.showInputDialog(Words.this,
                        "Enter the number of words to type (10, 25, 50, or 100):");
                
                if (userInput == null) {
                    // Handle if the user clicks Cancel or closes the dialog
                    System.exit(0);
                }
                
                int chosenWordCount = Integer.parseInt(userInput);
                
                // Validate the input
                if (chosenWordCount == 10 || chosenWordCount == 25 || chosenWordCount == 50 || chosenWordCount == 100) {
                    numwords = chosenWordCount;
                    validInput = true;
                } else {
                    JOptionPane.showMessageDialog(Words.this,
                            "Invalid input. Please enter 10, 25, 50, or 100 words.");
                }
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(Words.this, "Invalid input. Please enter a valid number.");
            }
        }
    }
    
    private int totalTyped = 0;
    private int  totalTypedCorrectly=0;
    private String OGWord;
    private boolean timeStarted = false;
    
    @Override
   public void keyPressed(KeyEvent e) {
    if (!timeStarted) {
        startGameStopwatch();
        timeStarted = true;
    }

    if (numwords > score) {
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
    } else {
        endGame();
    }
}
    
    private void startGameStopwatch() {
    startTime = System.currentTimeMillis();

    Timer timer = new Timer(true); // set to true so that it'll work in the background

    timer.scheduleAtFixedRate(new TimerTask() {
        @Override
        public void run() {
            // Calculate elapsed time
            long currentTime = System.currentTimeMillis();
            long elapsedTime = currentTime - startTime;

            // Update the countdown label
            SwingUtilities.invokeLater(() -> countdownLabel.setText("Time: " + elapsedTime / 1000));

            if (numwords <= score) {
                timer.cancel(); // Stop the timer when the user finishes typing the specified number of words
                endGame();
            }
        }
    }, 0, 1000); // update every 1000 milliseconds (1 second)
}
    

        
        private void endGame () {
            TypingSession.updateUserData(Profile.username, userData, getWpm(), getAccuracy(),incorrectWords);
                                scoreLabel.setText("Finished");
                                updateWPM();

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
        
        public void updateWord () {
            
            currentWord = g.displayRandomWords();
            OGWord = currentWord;
            wordLabel.setText(currentWord);      // for updating
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
