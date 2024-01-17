/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tapfinal2;

import java.io.Serializable;
import java.util.List;

 public class UserData implements Serializable {

    private double accuracy;
    private double wpm;
    private List<String> mostWrongWords;
    private static final long serialVersionUID = 1L;
    

    public double getAccuracy() {
        return accuracy;
    }

    public void setAccuracy(double accuracy) {
        this.accuracy = accuracy;
    }

    public double getWpm() {
        return wpm;
    }

    public void setWpm(double wpm) {
        this.wpm = wpm;
    }

    public List<String> getMostWrongWords() {
        return mostWrongWords;
    }

    public void setMostWrongWords(List<String> mostWrongWords) {
        this.mostWrongWords = mostWrongWords;
    }
}

class TypingSession {
    public static void updateUserData(String username, UserData user, double newWPM, double accuracy, List<String> mostWrongWords) {

        user.setAccuracy(accuracy);
        user.setWpm(newWPM);
        
        try {
            FileManager.saveIncorrectWords(username, mostWrongWords);
            FileManager.saveUserData(username, user);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            // Handle the exception appropriately (e.g., show an error message)
        }
    }
}

