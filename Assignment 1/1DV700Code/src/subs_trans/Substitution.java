package subs_trans;

import java.util.Scanner;
/**
 * For more details please have a look at pdf attachment
 *
 *
 */
/**
 * The type Substitution.
 */
public class Substitution {

    private static Substitution _occurrence;

    /**
     * Instance substitution.
     *
     * @return the substitution
     */
    public static Substitution instance() {
        if (_occurrence == null) {
            _occurrence = new Substitution();
        }
        return _occurrence;
    }

    /**
     * Encryption string.
     *
     * @param plaintext the plaintext
     * @return the string
     */
    public String encryption(String plaintext){
        System.out.println("*********************************\n" +
                "| Please enter a encryption key |\n" +
                "| Between 0-255\n" +
                "*********************************");
        Scanner scanner=new Scanner(System.in);
        System.out.print("-> ");
        int key=scanner.nextInt();
        if(key<0 || key >255){
            System.err.println("key must be within the 0-255 range\n" +
                    "failed to encrypt the data");
            System.exit(-1);
        }

        StringBuilder builder = new StringBuilder();

        for (int i = 0; i < plaintext.length(); i++) {
            char letter = plaintext.charAt(i);
                if (!Character.isLetter(letter)){
                int tempValue;
                int subsNumber = key;
                char encryptedChar;
                    // ASCII Value
                    tempValue=((int) letter +subsNumber);
                encryptedChar=(char) (tempValue);
                builder.append(encryptedChar);


            }
                 /*
                 * https://ascii.cl/
                 * Default: Convert to ASCII, add 1, convert back to char
                 * */
            else {
                int tempValue;
                int subsNumber;
                char encryptedChar;
                    // ASCII Value
                    if ((int) letter >= 65 && (int) letter <= 90)
                    subsNumber = 64;
                else
                    subsNumber = 96;

                tempValue = ((int) letter - subsNumber + key) % 26;
                if(tempValue == 0)
                    tempValue = 26;
                encryptedChar = (char) (tempValue + subsNumber);
                builder.append(encryptedChar);
            }
        }
        return builder.toString();
    }

    /**
     * Decryption string.
     *
     * @param secretMsg the secret msg
     * @param key       the key
     * @return the string
     */
    public String decryption(String secretMsg, int key){
        StringBuilder builder = new StringBuilder();

        for (int i = 0; i < secretMsg.length(); i++) {
            char letter = secretMsg.charAt(i);

            if (!Character.isLetter(letter)){
                    int tempValue;
                    int subsNumber = key;
                    char decryptedChar;
                    // ASCII Value
                tempValue=((int) letter -subsNumber);
                    decryptedChar=(char) (tempValue);
                    builder.append(decryptedChar);

            }

            else {
                int tempValue;
                int subsNumber;
                if((int) letter >= 65 && (int) letter <= 90){//for the charter
                    subsNumber = 64;
                }
                else{
                    subsNumber = 96;
                }

                tempValue = ((int) letter - subsNumber - key % 26 + 26) % 26;
                if(subsNumber == 0)
                    subsNumber = 26;
                char decryptedChar = (char)(tempValue + subsNumber);
                builder.append(decryptedChar);
            }
        }

        return builder.toString();
    }

}
