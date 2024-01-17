/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tapfinal2;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

/**
 *
 * @author saada
 */
public class IncorrectWordsRandomGenerator {
    
    public List <String> words;
    
    public IncorrectWordsRandomGenerator(String filePath) throws IOException {
    this.words = new ArrayList<>();
    loadIncorrectWordsFromFile(filePath);
}
    
    public void loadIncorrectWordsFromFile(String filePath) throws IOException {
    
        this.words.clear();

        try (Scanner g = new Scanner (new File (filePath))) {
            
            while (g.hasNext())
            {
                words.add(g.nextLine());
            }
            
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        
        Collections.shuffle(words);  // to generate random words from the list
    }
    
    public String displayRandomIncorrectWords() {
        
        String texts = " ";
        Random i = new Random();
                    
                if (words != null) {     
                    texts = words.get(i.nextInt(words.size())); // get = keyword for List 
                }
                else
                    texts = "No more words are available";
        
        return texts;
    }
}

