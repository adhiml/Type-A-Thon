/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package tapfinal2;

// I HOPE THIS WORKS BRO CAUSE WTAF JBFGUIWDFNKFN


import java.io.IOException;

// aesthetic / GUI import
import javax.swing.*;           // buttons, windows, labels


public class TAPFinal2 extends JFrame{
    
    
    public static void main(String[] args) throws IOException {
        
        SwingUtilities.invokeLater( () -> {
            new Profile ();
        } );
    }
}