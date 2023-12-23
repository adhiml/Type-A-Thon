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
    static int counter1 = 0, counter2 = 0;
    static int y = 0;
    
    WPM (String text)
    {
        this.text = text;
    }
    
    public static int overallCounter (String text)
    {
        
        while (counter2 < text.length())
            counter2++;
        
        return counter2;
    }
    
    public static int correctCounter (char answer, String text, int i)
    {
        if ((answer == text.charAt(i)))
            counter1++;
        
        return counter1;
    }
    
    public static void overallMark ()
    {
        System.out.println(counter1 + "/" + counter2);
    }
}

