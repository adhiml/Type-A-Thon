/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package type.a.thon;


import java.io.IOException;
import java.util.Scanner;
import javax.swing.SwingUtilities;

public class TypeAThon {

    public static void main(String[] args) {
        Scanner g = new Scanner (System.in);
        String answer = "", texts = "";
        
        try {
            
            RandomWords j = new RandomWords();
            StringBuilder d = new StringBuilder ();
            
            if (j != null)
            {
                    for (int r = 0; r < 10; r++)
                    {
                        d.append(j.pickRandomWords ()).append(" ");
                    }
                
                texts = d.toString().trim();
            }
            else {
                System.out.println("No words available.");
            }
        } catch (IOException error)
        {
            System.out.println("Error reading the file." + error.getMessage());  // .getMessage() gives you the error
        }
        
        System.out.println(texts);
        WPM.overallCounter(texts);
        
        
        System.out.print("\nPlease type the answers:");
        answer = g.nextLine();
        
        boolean nextLine = false;
        
        for (int i = 0; i < texts.length(); i++)
        {
            if(!checkText.check(answer.charAt(i), texts, i) && !nextLine)
            {
                System.out.print(Colors.ANSI_RED + answer.charAt(i) + Colors.ANSI_RESET);
            }
            else
            {
                System.out.print(Colors.ANSI_GREEN + answer.charAt(i) + Colors.ANSI_RESET); 
            }
            
            WPM.correctCounter(answer.charAt(i), texts, i);
        }

        System.out.println("");
        WPM.overallMark();
        System.out.printf("Accuracy:%.2f", WPM.accuracy());
        System.out.print("% \n");
        
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                UI ui = new UI();
                ui.setVisible(true);
            }
        
    });
}
}

