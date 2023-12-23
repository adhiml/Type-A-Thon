/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package type.a.thon;

/**
 *
 * @author saada
 */
public class WPM {
    
    
    static String text;
    static int counter = 0;
    static int y = 0;
    WPM (String text)
    {
        this.text = text;
    }
    
    public static int overallCounter (String text)
    {
        
        while (counter < text.length())
            counter++;
        
        return counter;
    }
    
    public static int correctCounter (char answer, String text, int i)
    {
        if ((answer == text.charAt(i)))
            counter++;
        
        return counter;
    }
    
    public static void overallMark ()
    {
        
    }
}

