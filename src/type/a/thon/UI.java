/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package type.a.thon;

import java.awt.*;
import java.awt.BorderLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
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
        setSize(400,200);
        setTitle("Type-A-Thon");
        
        promptLabel = new JLabel();
        textArea = new JTextArea();
        textArea.setEditable(false);
        
        JPanel panel = new JPanel();
        panel.setBackground(Color.DARK_GRAY);
        panel.setLayout(new BorderLayout());
        panel.add(promptLabel, BorderLayout.NORTH);
        panel.add(new JScrollPane(textArea), BorderLayout.CENTER);
        
        getContentPane().add(panel);
        
        
        countdownLabel = new JLabel ("Time:" + counter + " seconds");
        countdownLabel.setBackground(Color.DARK_GRAY);
        
        textArea = new JTextArea(4,6);
        textArea.setBackground(Color.DARK_GRAY);
        
        answerField = new JTextField(50);
        answerField.setBackground(Color.DARK_GRAY);
        
        setLayout(new BorderLayout());
        panel.add(countdownLabel,BorderLayout.NORTH);
//        add(new JScrollPane(textArea), BorderLayout.CENTER);
//        add(answerField, BorderLayout.SOUTH);
        
        setTitle("Type-A-Thon");
        setSize(500,300);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
    }
}
