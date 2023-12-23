/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package type.a.thon;


public class checkText {
    char answer;
    String text;
    
    checkText (char answer, String text)
    {
        this.answer = answer;
        this.text = text;
    }
    
    public static boolean check (char answer, String text, int i)
    {
        if (answer != text.charAt(i))
            return false;
        
        return true;
    }
}
