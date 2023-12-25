/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package type.a.thon;


import java.io.IOException;
import java.util.Scanner;

public class TypeAThon {

    public static void main(String[] args) {
        Scanner g = new Scanner (System.in);
        String answer = "", texts = "";
        
                try {
            
            RandomWords j = new RandomWords();
            
            StringBuilder d = new StringBuilder ();
            
            if (j != null)
            {
                    for (int r = 0; r < 30; r++)
                    {
                        d.append(j.pickRandomWords ()).append(" ");
                        
                        // add a line break after every 7 words
                        if ((r + 1) % 6 == 0)
                        {
                            d.append("\n");
                        }
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
        
        
        System.out.print("\nPlease type the answers back:");
        answer = g.nextLine();
        
        boolean nextLine = false;
        
        for (int i = 0; i < texts.length(); i++)
        {
            if (answer == "\n")
                nextLine = true;
            
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
        

        
        Stopwatch timer = new Stopwatch ();
        timer.start();
    }
    
}
