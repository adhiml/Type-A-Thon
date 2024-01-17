/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tapfinal2;


// files import
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.Scanner;


public class RandomWordsGenerator {
    
    public List <String> words;  // established a String List called words
    private static final String fileName = "C:\\Users\\user\\Documents\\NetBeansProjects\\TAPFinal2\\randomWords.txt";
    public RandomWordsGenerator () throws IOException {
        
        this.words = new ArrayList <> (); // ArrayList is like a box to hold the list of words (line 30)
                                    // initialize the list in the constructor
        try (Scanner g = new Scanner (new File (fileName))) {
            
            while (g.hasNext())
            {
                words.add(g.nextLine());
            }
            
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        
        Collections.shuffle(words);  // to generate random words from the list
    }
    
    public String displayRandomWords () {
        
        String texts = " ";
        Random i = new Random();
                    
                if (words != null) {     
                    texts = words.get(i.nextInt(words.size())); // get = keyword for List 
                }
                else
                    texts = "No more words are available";
        
        return texts;
    }
    
    public static void main (String [] args) throws IOException {
        
        
        try {
            
//            String DATA_DIRECTORY = "C:" + File.separator + "Users" + File.separator + "saada" + File.separator + "OneDrive" + File.separator + "Desktop" + File.separator + "Players";
//            String userDirectory = DATA_DIRECTORY + File.separator + username + File.separator + username + "_IncorrectWords.txt";
//            
//            new IncorrectWordsRandomGenerator(userDirectory);
            
                new RandomWordsGenerator();
            
            System.out.println("Words succesfully loaded");
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        
    }
}




