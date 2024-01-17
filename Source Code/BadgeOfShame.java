/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tapfinal2;

import java.io.File;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;
import java.util.Scanner;

public class BadgeOfShame {

    private Set<String> uniqueMisspelledWords;

    public BadgeOfShame() {
        this.uniqueMisspelledWords = new HashSet<>();
    }

    public void misspelledWordsList(String filePath) throws IOException {
        this.uniqueMisspelledWords.clear();

        try (Scanner scanner = new Scanner(new File(filePath))) {
            while (scanner.hasNext()) {
                uniqueMisspelledWords.add(scanner.nextLine());
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public void displayBadge() {
        System.out.println("Top 10 Most Misspelled Words:");

        // Display the top 10 misspelled words (or less if the set is smaller)
        int count = 0;
        for (String word : uniqueMisspelledWords) {
            if (count >= 10) {
                break;
            }
            System.out.println((count + 1) + ". " + word);
            count++;
        }
    }
    
    public Set<String> getUniqueMisspelledWords () {
        return uniqueMisspelledWords;
    }
}

