/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package type.a.thon;


import java.util.Scanner;

public class TypeAThon {

    public static void main(String[] args) {
        Scanner g = new Scanner (System.in);
        String answer;
        
        String text = "It was VERY easy!";
        System.out.println(GFG.ANSI_PURPLE + text + GFG.ANSI_RESET);

        System.out.print("Please type the answers back:");
        answer = g.nextLine();
        
        for (int i = 0; i < text.length(); i++)
        {
            
            if(!checkText.check(answer.charAt(i), text, i))
                System.out.print(GFG.ANSI_RED + answer.charAt(i) + GFG.ANSI_RESET);
            else
                System.out.print(GFG.ANSI_GREEN + answer.charAt(i) + GFG.ANSI_RESET);   
            
        }
        
        System.out.println("");
        
        
        
    }
    
}
