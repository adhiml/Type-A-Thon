/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tapfinal2;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.io.File;
import java.io.IOException;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.SwingUtilities;

public class Profile {
    static String username;
    static FileManager fileManager = new FileManager();
    
    private JFrame mainFrame;

    public Profile() {
        
        mainFrame = new JFrame("Type-A-Thon");
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainFrame.setSize(700, 400);
        
        createProfile();
    }

    public void createProfile() {
//        JFrame frame = new JFrame("Sign In");
//        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        frame.setSize(300, 200);

        JPanel signInPanel = new JPanel ();
        JTextField textField = new JTextField(20);
        JButton button = new JButton("Sign In");
        button.setBackground(new Color(0xB8906D));
        button.setForeground(Color.white);


        button.addActionListener(e -> {
            username = textField.getText();
            if (usernameExists(username)) {
                JOptionPane.showMessageDialog(mainFrame, "Welcome back, " + username + "!");
                fileManager.loadUserData(username);
                
                showMenu ();
            } else {
                fileManager.createUserDirectory(username);
                UserData newUserData = new UserData();
                saveUserData(username, newUserData);
                JOptionPane.showMessageDialog(mainFrame, "Profile created successfully, " + username + "!");
            }
        });
        
        String car = "\uD83D\uDE97"; // Car emoji Unicode
        String cloud ="\uD83D\uDCA8";	//Dashing Away 
        String collision="\uD83D\uDCA5";
        
        ImageIcon image= new ImageIcon("racing2.jpg");

        JLabel label1 = new JLabel();
        label1.setText("Enter your username");
        label1.setFont(new Font("Courier New", Font.BOLD, 15));
        label1.setForeground(Color.BLACK);

        JLabel label2 = new JLabel();
        label2.setText("-  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -");
        label2.setFont(new Font("Arial", Font.BOLD, 20));
        label2.setForeground(Color.white);

        JLabel label3 = new JLabel("<html><font size='30'>" + car + cloud +  " &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; " + car +" &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; " + car + cloud + "&nbsp;&nbsp;&nbsp;</font></html>");      
        label3.setBounds(150, 230, 400, 200);
        label3.setForeground(Color.WHITE);
        
        JLabel label4= new JLabel(image);
        label4.setIcon(image);
        label4.setBounds(0, 0, 110, 110);
        
        JLabel label5= new JLabel(image);
        label5.setIcon(image);
        label5.setBounds(0, 110, 110, 110);
        
        JLabel label6= new JLabel(image);
        label6.setIcon(image);
        label6.setBounds(0, 210, 110, 110);
        
        JLabel label7= new JLabel();
        label7.setText("LET'S PLAY: TYPE-A-THON ! ");
        label7.setFont(new Font("Gill Sans Ultra Bold",Font.TRUETYPE_FONT, 22));
        label7.setForeground(new Color(0xFFA51E));
        label7.setBounds(200,50,500,100);
        
        JLabel label8= new JLabel("<html><font size='30'>" + collision + "&nbsp;&nbsp;</font></html>");
        label8.setBounds(570, 100, 40, 80);
        label8.setForeground(Color.YELLOW);

        
        JPanel panel1 = new JPanel();//login user
        panel1.setBounds(210, 160, 350, 80);
        panel1.setBackground(new Color(0xFAF7CC));
        panel1.add(label1);
        panel1.add(textField);
        panel1.add(button);
        panel1.setBorder(BorderFactory.createEtchedBorder(new Color(0xB8906D), new Color(0xB8906D)));

        JPanel panel2 = new JPanel();
        panel2.setBounds(0, 320, 683, 40);
        panel2.setBackground(Color.LIGHT_GRAY);
        panel2.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        panel2.add(label2);
        
        mainFrame.getContentPane().add(label3);
        mainFrame.getContentPane().add(label4);
        mainFrame.getContentPane().add(label5);
        mainFrame.getContentPane().add(label6);
        mainFrame.getContentPane().add(label7);
        mainFrame.getContentPane().add(panel1);
        mainFrame.getContentPane().add(panel2);
        mainFrame.getContentPane().add(label8);
        mainFrame.getContentPane().setBackground(new Color(0x233A6C));
        mainFrame.setVisible(true);
        mainFrame.getContentPane().add(label3);
        mainFrame.getContentPane().add(label4);
        mainFrame.getContentPane().add(label5);
        mainFrame.getContentPane().add(label6);
        mainFrame.getContentPane().add(label7);
        mainFrame.getContentPane().add(panel1);
        mainFrame.getContentPane().add(panel2);
        mainFrame.getContentPane().add(label8);


    }

    
    private void showMenu () {
        
        mainFrame.getContentPane().removeAll();
        
//        Leaderboard leaderboard = new Leaderboard();
//        String leaderboardText = leaderboard.Leaderboard();
//        
//        JTextArea leaderboardTextArea = new JTextArea(leaderboardText);
//        leaderboardTextArea.setEditable(false);
    
        JPanel menuPanel = new JPanel(new GridLayout(2, 2));
        
        JButton defaultGameButton = new JButton("Default Game");
  defaultGameButton.setBorder(BorderFactory.createLineBorder(new Color(0x49B74), 15));
  defaultGameButton.setFont(new Font("Gill Sans Ultra Bold",Font.PLAIN, 15));
  defaultGameButton.setBackground(new Color(0xE5D598));
  defaultGameButton.setForeground(Color.BLACK);

      
        JButton suddenDeathButton = new JButton("Sudden Death");
   suddenDeathButton.setFont(new Font("Gill Sans Ultra Bold",Font.PLAIN, 15));
  suddenDeathButton.setBorder(BorderFactory.createLineBorder(new Color(0x79616F), 15));
  suddenDeathButton.setBackground(new Color(0xEAB595));
  suddenDeathButton.setForeground(Color.black);

     
        JButton alternativeGameButton = new JButton("Alternative Game Mode");
        alternativeGameButton.setBorder(BorderFactory.createLineBorder(new Color(0x49B74), 15));
  alternativeGameButton.setFont(new Font("Gill Sans Ultra Bold",Font.PLAIN, 13));
  alternativeGameButton.setBackground(new Color(0xE5D598));
  alternativeGameButton.setForeground(Color.black);

        JButton correctionFacilityButton = new JButton("Correction Facility");       
  correctionFacilityButton.setFont(new Font("Gill Sans Ultra Bold",Font.PLAIN, 15));
  correctionFacilityButton.setBorder(BorderFactory.createLineBorder(new Color(0x79616F), 15));
  correctionFacilityButton.setBackground(new Color(0xEAB595));
  correctionFacilityButton.setForeground(Color.black);

        JButton playerprofileButton = new JButton("Player Profile");
        playerprofileButton.setBorder(BorderFactory.createLineBorder(new Color(0x49B74), 15));
  playerprofileButton.setFont(new Font("Gill Sans Ultra Bold",Font.PLAIN, 15));
  playerprofileButton.setBackground(new Color(0xE5D598));
  playerprofileButton.setForeground(Color.BLACK);

        JButton leaderboardButton = new JButton("Leaderboard");
        leaderboardButton.setFont(new Font("Gill Sans Ultra Bold",Font.PLAIN, 15));
  leaderboardButton.setBorder(BorderFactory.createLineBorder(new Color(0x79616F), 15));
  leaderboardButton.setBackground(new Color(0xEAB595));
  leaderboardButton.setForeground(Color.black);
        

        // Add action listeners to the buttons
        defaultGameButton.addActionListener(e -> startGame(GameMode.DEFAULT));
        suddenDeathButton.addActionListener(e -> startGame(GameMode.SUDDEN_DEATH));
        alternativeGameButton.addActionListener(e -> startGame(GameMode.ALTERNATIVE));
        correctionFacilityButton.addActionListener(e -> startGame(GameMode.CORRECTION_FACILITY));
        playerprofileButton.addActionListener( e -> startGame(GameMode.PLAYER_PROFILE));
        leaderboardButton.addActionListener( e -> startGame(GameMode.LEADERBOARD));


        // Add buttons to the panel
        menuPanel.add(defaultGameButton);
  menuPanel.add(suddenDeathButton);
  menuPanel.add(alternativeGameButton);
  menuPanel.add(correctionFacilityButton);
  menuPanel.add(playerprofileButton);
        menuPanel.add(leaderboardButton);

        // Add the panel to the frame
//        mainFrame.getContentPane().add(new JScrollPane(leaderboardTextArea));
//        mainFrame.setVisible(true);
    
        mainFrame.getContentPane().add(menuPanel);
        mainFrame.setVisible(true);
    }
    
    private void startGame(GameMode gameMode) {
        
        
        // Create and start the corresponding game based on the chosen game mode
        SwingUtilities.invokeLater(() -> {
            switch (gameMode) {
                case DEFAULT:
                    try {
                        new Game();
                    } catch (IOException e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case SUDDEN_DEATH:
                    try {
                        new SuddenDeath();
                    } catch (IOException e) {
                        System.out.println(e.getMessage());
                    }
                    break;  // Add this break statement
                case ALTERNATIVE:
                    AlternativeGamemode.showGameModeSelection();
                    
                    break;
                case PLAYER_PROFILE:
                    new PlayerProfile().setVisible(true);
                    break;
                case LEADERBOARD:
                    new Leaderboard();
                    break;
                case CORRECTION_FACILITY:
                    try {
                        
                        // Example of creating CorrectionFacility with IncorrectWordsRandomGenerator
                        String userDirectory = DATA_DIRECTORY + File.separator + username + File.separator + username + "_IncorrectWords.txt";
                        FileManager.createUserDirectory(username);
                        IncorrectWordsRandomGenerator generator = new IncorrectWordsRandomGenerator(userDirectory);
                        new CorrectionFacility(generator);
                        
                    } catch (IOException e) {
                        System.out.println(e.getMessage());
                    }
                    break;
            }
        });
    }
    
    
    private enum GameMode {
        DEFAULT,
        SUDDEN_DEATH,
        ALTERNATIVE,
        CORRECTION_FACILITY,
        PLAYER_PROFILE,
        LEADERBOARD 
    }
    
    public final String DATA_DIRECTORY = "C:\\Users\\user\\Documents\\NetBeansProjects\\TAPFinal2\\Players";
                                               
    public boolean usernameExists(String u) {
        File userDirectory = new File(DATA_DIRECTORY + File.separator + username);
        return userDirectory.exists();
    }
    
    public static void saveUserData(String u, UserData data) {
        fileManager.saveUserData(u, data);
    }
    
    public UserData loadUserData(String u) {
        return fileManager.loadUserData(u);
    }

    public static String getUsername() {
        return username;
    }

    public static void setUsername(String username) {
        Profile.username = username;
    }
    
}
