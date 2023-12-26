/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package type.a.thon;


import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.Scanner;


public class RandomWords {
    
    private final List <String> words;
    
    private final String fileName = "C:\\Users\\saada\\OneDrive\\Desktop\\Java\\FoP Tutorial\\Type-A-Thon\\randomWords.txt";
    
    public RandomWords () throws IOException {
        this.words = new ArrayList <> ();
        
        try ( Scanner g = new Scanner (new File(fileName)))
        {
            while (g.hasNext())
            {
                words.add(g.nextLine());
            }
        }
        
        Collections.shuffle(words);
    }
    
    public String pickRandomWords ()
    {
        if (!words.isEmpty())
        {
            Random j = new Random ();
            return words.get(j.nextInt(words.size()));
        }
        else
        {
            return null;
        }
    }
    
    public static String displayWords ()
    {
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
        
        return texts;
    }
    
    public static void main(String[] args) {
    try {new RandomWords();
        System.out.println(displayWords());
    } catch (IOException error) {
        System.out.println(error.getMessage());
    }
    }
}