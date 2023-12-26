/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package type.a.thon;

import java.awt.*;
import java.awt.BorderLayout;
import java.io.IOException;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JTextArea;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

public class UI extends JFrame{
    
    private JLabel promptLabel;
    private JLabel countdownLabel;
    private JTextArea textArea;
    private JTextField answerField;
    private long startTime;
    private int wordsTyped;
    private int counter = 30;
    
    public UI ()
    {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(500,300);
        setTitle("Type-A-Thon");
        
        promptLabel = new JLabel();
        textArea = new JTextArea();
        textArea.setEditable(false);
        
        JPanel panel = new JPanel();
        panel.setBackground(Color.DARK_GRAY);
        panel.setLayout(new BorderLayout());
        panel.add(promptLabel, BorderLayout.NORTH);
        panel.add(new JScrollPane(textArea), BorderLayout.CENTER);
        
        JLayeredPane layeredPane = new JLayeredPane();
        layeredPane.add(textArea,JLayeredPane.DEFAULT_LAYER);
        
        JLabel overlayLabel = new JLabel ("Overlay text");
        overlayLabel.setForeground(Color.WHITE);
        overlayLabel.setFont(new Font("Monospaced", Font.PLAIN,15));
        
        // Adjust the position of the overlay label as needed
        overlayLabel.setBounds(10, 10, 200, 20);

        // Add the overlay label to the layered pane
        layeredPane.add(overlayLabel, JLayeredPane.PALETTE_LAYER);

        // Add the layered pane to the panel
        panel.add(layeredPane, BorderLayout.CENTER);
        
        getContentPane().add(panel);
        
        
        countdownLabel = new JLabel ("Time:" + counter + " seconds");
        countdownLabel.setBackground(Color.DARK_GRAY);
        
        textArea = new JTextArea(5,5);
        textArea.setBackground(Colors.d);
        
        try {
                new RandomWords();
                String words = RandomWords.displayWords();
                textArea.setText(words);
                textArea.setForeground(Colors.l);
                
                textArea.setFont(new Font("Monospaced",Font.PLAIN,15));
            } catch (IOException e)
            {
                System.out.println(e.getMessage());
            }
        
        
//        answerField = new JTextField(50);
//        answerField.setBackground(Color.DARK_GRAY);
        
        setLayout(new BorderLayout());
        panel.add(countdownLabel,BorderLayout.NORTH);
        add(new JScrollPane(textArea), BorderLayout.CENTER);
//        add(answerField, BorderLayout.SOUTH);
        
        setTitle("Type-A-Thon");
        setSize(500,300);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
    }
    
    public static void main (String[] args)
    {
        new UI();
    }
}
