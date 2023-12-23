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
    
    private List <String> words;
    
    public RandomWords () throws IOException {
        this.words = new ArrayList <> ();
        
        try ( Scanner g = new Scanner (new File("random-word.txt")))
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
}
