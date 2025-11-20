import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class VigenereCipher {

    public static void main(String[] args) {
        //System.out.println(encode("Hello, my name is Adrian", "kEYword"));
        //System.out.println(decode("Rijhc, db xeka wj Dnvgwb", "kEYword"));

        String testCase = "Wv chu dusp waf txoisi eidl zboelrve? Ba ml bxjensx px yexsw zohk. Lnmtuw yigk zboelrve wliilr zemilmcbnz. Iym rxtsoe moi lampwyavamhn, tuh mhx hgm bxjsfel oselhd.";
        String keyword = "";
        try {
            File nameFile = new File("words.txt");
            Scanner reader = new Scanner(nameFile);
            while (reader.hasNextLine()) { 
                String word = reader.nextLine(); 
                String sentence = decode(testCase, word);
                if (sentence.contains("the") && sentence.contains("you")) {
                    keyword = word;
                    break;
                }
            }
            reader.close(); 

        } catch (FileNotFoundException e) {
            System.out.println("Error: File not found.");
            e.printStackTrace(); 
        }
        System.out.println(decode("Wv chu dusp waf txoisi eidl zboelrve? Ba ml bxjensx px yexsw zohk. Lnmtuw yigk zboelrve wliilr zemilmcbnz. Iym rxtsoe moi lampwyavamhn, tuh mhx hgm bxjsfel oselhd.", keyword));
        System.out.println(keyword);

    }

    public static String encode(String plainText, String keyword){
        String ans = "";
        String newString = "";
        int a = 0;
        for (int i = 0; i < plainText.length(); i++) {
            if (Character.isLetter(plainText.charAt(i))) {
                newString += keyword.charAt(a%keyword.length());
                a++;
            } else {
                newString += plainText.charAt(i);
            }
        }
        for (int i = 0; i < plainText.length(); i++) {
            char orgLetter = plainText.charAt(i);
            char encodedLetter = newString.charAt(i);
            char newLetter = ' ';
            if (Character.isLetter(plainText.charAt(i))) {
                int org = 0;
                int encoded = 0;
                if (Character.isUpperCase(orgLetter)){
                    org = orgLetter - 65;
                } else {
                    org = orgLetter - 97;
                }
                if (Character.isUpperCase(encodedLetter)) {
                    encoded = encodedLetter-65;
                } else {
                    encoded = encodedLetter-97;
                }
                if (Character.isUpperCase(orgLetter)){
                    newLetter = (char) (((org+encoded)%26)+65);
                } else {
                    newLetter = (char) (((org+encoded)%26)+97);    
                }
                ans += newLetter;
            } else {
                ans += orgLetter;
            }
        }

        return ans;
    }

    public static String decode(String cipherText, String keyword){
        String ans = "";
        String newString = "";
        int a = 0;
        for (int i = 0; i < cipherText.length(); i++) {
            if (Character.isLetter(cipherText.charAt(i))) {
                newString += keyword.charAt(a%keyword.length());
                a++;
            } else {
                newString += cipherText.charAt(i);
            }
        }
        for (int i = 0; i < cipherText.length(); i++) {
            char orgLetter = cipherText.charAt(i);
            char encodedLetter = newString.charAt(i);
            int org = 0;
            int encoded = 0;
            if (Character.isLetter(cipherText.charAt(i))) {
                if (Character.isUpperCase(orgLetter)){
                    org = orgLetter - 65;
                } else {
                    org = orgLetter - 97;
                }
                if (Character.isUpperCase(encodedLetter)){
                    encoded = encodedLetter-65;
                } else {
                    encoded = encodedLetter-97;
                }
                char newLetter = ' ';
                if (org-encoded >= 0) {
                    if (Character.isUpperCase(orgLetter)) {
                        newLetter = (char) (((org-encoded)%26)+65);
                    } else if (Character.isLowerCase(orgLetter)) {
                        newLetter = (char) (((org-encoded)%26)+97);    
                    }
                } else {
                    if (Character.isUpperCase(orgLetter)) {
                        newLetter = (char) (((org-encoded)%26)+91);  
                    } else if (Character.isLowerCase(orgLetter)) {
                        newLetter = (char) (((org-encoded)%26)+123);    
                    }
                }
                ans += newLetter;
            } else {
                ans += orgLetter;
            }
        }

        return ans;
    }
}