/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tapfinal2;

import javax.swing.*;
import java.awt.*;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import static tapfinal2.FileManager.DATA_DIRECTORY;

public class PlayerProfile extends JFrame {

    String username;
    private double averageWpmAllTime;
    private double averageAccuracyAllTime;
    private double averageWpmLast10Games;
    private double averageAccuracyLast10Games;
    BadgeOfShame bos = new BadgeOfShame();
    
    public PlayerProfile() {
        // Use Swing input dialog for entering the username
        this.username = JOptionPane.showInputDialog("Enter the username");

        // Ensure the username is not null
        if (username == null || username.trim().isEmpty()) {
            // Handle case where username is empty or canceled
            System.exit(0);
        }
        
        try {
            String userDirectory = DATA_DIRECTORY + File.separator + username + File.separator + username + "_IncorrectWords.txt";
            bos.misspelledWordsList(userDirectory);
            
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        
        calculateAverages();
        createUI();
        
    }

    public PlayerProfile(String username) {
        this.username = username;
        calculateAverages();
        createUI();
    }

    private void calculateAverages() {
        Map<String, Double> allTimeData = getAverageData(username);
        Map<String, Double> last10GamesData = getLastNGamesAverageData(username, 10);

        averageWpmAllTime = allTimeData.get("wpm");
        averageAccuracyAllTime = allTimeData.get("accuracy");
        averageWpmLast10Games = last10GamesData.get("wpm");
        averageAccuracyLast10Games = last10GamesData.get("accuracy");
    }

    private Map<String, Double> getAverageData(String username) {
        Map<String, Double> result = new HashMap<>();
        double totalWpm = 0;
        double totalAccuracy = 0;
        int count = 0;

        try (BufferedReader reader = new BufferedReader(new FileReader("score.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 3 && parts[0].equals(username)) {
                    totalWpm += Double.parseDouble(parts[1]);
                    totalAccuracy += Double.parseDouble(parts[2]);
                    count++;
                }
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

        if (count > 0) {
            result.put("wpm", totalWpm / count);
            result.put("accuracy", totalAccuracy / count);
        }

        return result;
    }

    private Map<String, Double> getLastNGamesAverageData(String username, int n) {
        Map<String, Double> result = new HashMap<>();
        double totalWpm = 0;
        double totalAccuracy = 0;
        int count = 0;

        try (BufferedReader reader = new BufferedReader(new FileReader("score.txt"))) {
            String line;
            while ((line = reader.readLine()) != null && count < n) {
                String[] parts = line.split(",");
                if (parts.length == 3 && parts[0].equals(username)) {
                    totalWpm += Double.parseDouble(parts[1]);
                    totalAccuracy += Double.parseDouble(parts[2]);
                    count++;
                }
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

        if (count > 0) {
            result.put("wpm", totalWpm / count);
            result.put("accuracy", totalAccuracy / count);
        }

        return result;
    }

    private void createUI() {
        setTitle("Player Profile");
        setSize(700, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        

        JPanel mainPanel = new JPanel(new GridLayout(4, 2));
        mainPanel.setLayout(new GridLayout(5, 2)); 
        mainPanel.setBackground(new Color(0xE5D598));    
        mainPanel.setBorder(BorderFactory.createLineBorder(new Color(0x79616F), 10));


        mainPanel.add(new JLabel("Username:"));
        mainPanel.add(new JLabel(username));

        mainPanel.add(new JLabel("Average WPM (All Time):"));
        mainPanel.add(new JLabel(String.valueOf(averageWpmAllTime)));

        mainPanel.add(new JLabel("Average Accuracy (All Time):"));
        mainPanel.add(new JLabel(String.valueOf(averageAccuracyAllTime)));

        mainPanel.add(new JLabel("Average WPM (Last 10 Games):"));
        mainPanel.add(new JLabel(String.valueOf(averageWpmLast10Games)));

        mainPanel.add(new JLabel("Average Accuracy (Last 10 Games):"));
        mainPanel.add(new JLabel(String.valueOf(averageAccuracyLast10Games)));
        
        mainPanel.add(new JLabel(""));
                mainPanel.add(new JLabel(""));


        mainPanel.add(new JLabel("Top 10 Most Misspelled Words:"));
            int count = 0;
            for (String word : bos.getUniqueMisspelledWords()) {
                
                if (count >= 10) {
                        break;
                    }
                
                mainPanel.add(new JLabel((count + 1) + ". " + word + "\n"));
                count++;
            }
            
        add(mainPanel);

        setVisible(true);
    }
}
