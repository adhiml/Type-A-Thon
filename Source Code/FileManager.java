/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tapfinal2;

import java.io.*;
import java.util.List;

public class FileManager {
                                               
    public static final String DATA_DIRECTORY = "C:" + File.separator + "Users" + File.separator + "user" + File.separator + "Documents" + File.separator + "NetBeansProjects" + File.separator + "TAPFinal2"+ File.separator + "Players";
    //C:\Users\alyar\Documents\NetBeansProjects\TAPFinal2\Players               
    public static void createUserDirectory(String username) {
    File userDirectory = new File(DATA_DIRECTORY + File.separator + username);
    if (!userDirectory.exists()) {
        if (!userDirectory.mkdir()) {
            // Handle directory creation failure
            System.err.println("Failed to create user directory: " + userDirectory.getAbsolutePath());
        }
    }
}

    public static void saveUserData(String username, UserData data) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(DATA_DIRECTORY + File.separator + username + File.separator + username + "_data.txt"))) {
            oos.writeObject(data);
        } catch (IOException e) {
            System.out.println(e.getMessage());
            // Handle file save error
        }
    }
    
    public static void saveIncorrectWords(String username, List<String> incorrectWords) {
    try (FileWriter fileWriter = new FileWriter(DATA_DIRECTORY + File.separator + username + File.separator + username + "_IncorrectWords.txt", true);
         BufferedWriter writer = new BufferedWriter(fileWriter)) {
        
            // Write each incorrect word on a new line
            for (String word : incorrectWords) {
                writer.write(word);
                writer.newLine();
            }

        } catch (IOException e) {
            System.out.println(e.getMessage());
            // Handle file save error
        }
    }

    
    public UserData loadUserData(String username) {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(DATA_DIRECTORY + File.separator + username + File.separator + username + "_data.txt"))) {
            return (UserData) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            System.out.println(e.getMessage()); // Print the error for debugging
            return null;
        }
    }

}
