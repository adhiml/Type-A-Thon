/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tapfinal2;

import javax.swing.*;
import java.awt.*;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class Leaderboard extends JFrame {

    private JTextArea leaderboardTextArea;

    public Leaderboard() {
        // Set up the frame
        super("Leaderboard");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 300);

        // Create components
        leaderboardTextArea = new JTextArea();
        JScrollPane scrollPane = new JScrollPane(leaderboardTextArea);

        // Add components to the frame
        getContentPane().setLayout(new BorderLayout());
        getContentPane().add(scrollPane, BorderLayout.CENTER);

        // Load leaderboard data and update the text area
        loadLeaderboardData();

        // Set visibility
        setLocationRelativeTo(null); // Center the frame on the screen
        setVisible(true);
    }

    private void loadLeaderboardData() {
        Map<String, List<Double>> userScores = new HashMap<>();

        try (Scanner sc = new Scanner(new FileInputStream("score.txt"))) {
            while (sc.hasNextLine()) {
                String[] line = sc.nextLine().split(",");
                if (line.length == 3) {
                    String username = line[0];
                    double wpm = Double.parseDouble(line[1]);
                    double accuracy = Double.parseDouble(line[2]);

                    if (!userScores.containsKey(username)) {
                        userScores.put(username, new ArrayList<>());
                    }

                    List<Double> scores = userScores.get(username);
                    scores.add(wpm);
                    scores.add(accuracy);

                    while (scores.size() > 20) {
                        scores.remove(0);
                    }
                }
            }
        } catch (IOException e) {
            JOptionPane.showMessageDialog(this, "Problem with score file", "Error", JOptionPane.ERROR_MESSAGE);
        }

        StringBuilder leaderboardString = new StringBuilder("Leaderboard (Last 10 games):");
        leaderboardString.append(String.format("%-15s %-15s %-10s%n", "\nUsername:", "\tAverage WPM:", "    Average Accuracy Scores:"));
        for (Map.Entry<String, List<Double>> entry : userScores.entrySet()) {
            String username = entry.getKey();
            List<Double> scores = entry.getValue();

            double totalWpm = 0;
            double totalAccuracy = 0;
            for (int i = 0; i < scores.size(); i += 2) {
                totalWpm += scores.get(i);
                totalAccuracy += scores.get(i + 1);
            }

            double averageWpm = totalWpm / (scores.size() / 2);
            double averageAccuracy = totalAccuracy / (scores.size() / 2);

            leaderboardString.append(String.format("%-15s \t%-30.2f %-15.2f%n", username, averageWpm, averageAccuracy));
        }

        leaderboardTextArea.setText(leaderboardString.toString());
    }
}