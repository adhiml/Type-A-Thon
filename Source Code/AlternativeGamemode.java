/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tapfinal2;

import java.awt.Color;
import java.awt.HeadlessException;
import javax.swing.JButton;
import javax.swing.JFrame;
import java.io.IOException;

public class AlternativeGamemode {

    public static void main(String[] args) {
        try {
            showGameModeSelection();
        } catch (Exception e) {
            // Handle exceptions appropriately
            System.out.println("Cannot show the list of alternative game");
        }
    }
public static void showGameModeSelection() {
    try{
        JFrame frame = new JFrame("Select Game Mode");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 150);
        frame.setLayout(null);

        JButton timedButton = new JButton("Timed");
        timedButton.setBounds(50, 30, 80, 30);
        timedButton.setBackground(new Color(0xE5D598));

        JButton wordButton = new JButton("Word");
        wordButton.setBounds(140, 30, 80, 30);
        wordButton.setBackground(new Color(0xE5D598));

        JButton quotesButton = new JButton("Quotes");
        quotesButton.setBounds(230, 30, 80, 30);
        quotesButton.setBackground(new Color(0xE5D598));


        timedButton.addActionListener(e -> startTimedMode(frame));
        wordButton.addActionListener(e -> startWordMode(frame));
        quotesButton.addActionListener(e -> startQuotesMode(frame));

        frame.add(timedButton);
        frame.add(wordButton);
        frame.add(quotesButton);
        frame.getContentPane().setBackground(new Color(0x79616F));


        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }catch(HeadlessException e){
        System.out.println("Error");
    }
}

    public static void startTimedMode(JFrame frame) {
    try {
        new TimedMode();
        // Additional setup or configurations for TimedMode if needed
        frame.dispose();
    } catch (IOException e) {
            System.out.println("Time mode problem");
    }
}

    public static void startWordMode(JFrame frame) {
       try {
        new Words();
        // Additional setup or configurations for TimedMode if needed
        frame.dispose();
    } catch (IOException e) {
            System.out.println("Words mode problem");
    }
}

    public static void startQuotesMode(JFrame frame) {
        try {
        new Quotes();
        // Additional setup or configurations for TimedMode if needed
        frame.dispose();
    } catch (IOException e) {
            System.out.println("Quotes mode problem");
    }
}
}
