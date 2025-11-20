import java.util.*;


public class CaesarCipher {
    public static void main(String[] args) {
        for (int i = 0; i < 26; i++) {
            System.out.print("Key: " + i + " ");
            System.out.println(decode("Migyncgym cn cm nby jyijfy hi ihy wuh cguachy uhsnbcha iz qbi xi nby nbcham hi ihy wuh cguachy.", i));
        }
    }
    
    public static String encode(String plainText, int key) {
        String ans = "";
        for (int i = 0; i < plainText.length(); i++) { //Loop through text
        char letter = plainText.charAt(i); //take the character at i
        if (Character.isLetter(letter)) { //check if character is a letter
            char newLetter = letter;
            newLetter = (char) (letter + key); //find the new letter after shifting
            if (Character.isUpperCase(letter) && newLetter > 90) { //check if letter is uppercase and whether it needs to wrap around
                int index = newLetter - 65; //subtract the displacement so it starts at 0 and goes to 26
                newLetter = (char) (index%26+65); //get the remainder and add back the original displacement
            } else if (Character.isLowerCase(letter) && newLetter > 122) { //same logic as uppercase
                int index = newLetter - 97;
                newLetter = (char) (index%26+97);
            }
            ans = ans + newLetter;
        } else {
            ans = ans + letter;
        }
    }
        return ans;
    }
    
    public static String decode(String cipherText, int key) {
        String ans = "";
        for (int i = 0; i < cipherText.length(); i++) { //loop through text
        char letter = cipherText.charAt(i);
        if (Character.isLetter(letter)) { //check if letter
            char newLetter = letter;
            newLetter = (char) (letter - key); //get the new letter
            if (Character.isUpperCase(letter) && newLetter < 65) { //check if uppercase and needs to be wrapped
                int index = 65 - newLetter; //find the amount it needs to be shifted by from z
                newLetter = (char) (91-(index%26)); //subtract the amount by the position of z
            } else if (Character.isLowerCase(letter) && newLetter < 97) { //same logic as for uppercase
                int index = 97 - newLetter;
                newLetter = (char) (123-(index%26));
            }
            ans = ans + newLetter;
        } else {
            ans = ans + letter;
        }
    }
        return ans;
    }
}