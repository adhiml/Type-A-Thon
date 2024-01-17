/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tapfinal2;


import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;

public class saveScore {
        String username;
        double wpm;
        double accuracy;
    
public saveScore(String username) {
        this.username = username;
        
    }

public void saveScore() {
    this.wpm = Game.getWpm();
    this.accuracy = Game.getAccuracy();
    saveScoreToFile(username,wpm, accuracy);
    
}

    public void saveScoreToFile(String u, double wpm, double accuracy) {
        try {
            PrintWriter out = new PrintWriter(new FileOutputStream("score.txt", true));
            out.println(u + "," + wpm + "," + accuracy);
            out.println();
            out.close();
        } catch (IOException e) {
            System.out.println("Problem with saving score");
        }
    }
}