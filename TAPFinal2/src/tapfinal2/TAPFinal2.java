/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package tapfinal2;

// I HOPE THIS WORKS BRO CAUSE WTAF JBFGUIWDFNKFN

// files import
import java.io.IOException;

// aesthetic / GUI import
import javax.swing.*;           // buttons, windows, labels


public class TAPFinal2 {
    public static void main(String[] args) throws IOException {
    
        SwingUtilities.invokeLater( () -> {
            try {
                new Game ();
            } catch (IOException e) {
                System.out.println(e.getMessage());
            }
        } );
        
    }
}
