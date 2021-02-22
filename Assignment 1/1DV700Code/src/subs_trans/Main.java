package subs_trans;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

public class Main {
    /**
     * For more details please have a look at pdf attachment
     *
     *
     */

    public static void main(String[] args) throws IOException {

        try {
            ReadAndWriteFile.instance().openFileDialog();

            System.out.println("Function of your choice!");
            System.out.println("| 1.Encryption |  \n| 2.Decryption |");
            Scanner input = new Scanner(System.in);
            int userInput = input.nextInt();

            if (userInput == 1) {
                double key;
                String textData;

                Scanner scanner = ReadAndWriteFile.instance().readFile();
                String message = transScannerToStr(scanner);

                System.out.println("Method of your choice!");
                System.out.println("| 1.Substitution  |  \n| 2.transposition |");

                input = new Scanner(System.in);
                userInput = input.nextInt();

                System.out.println("Choose the location");
                ReadAndWriteFile.instance().openFileDialog();

                switch (userInput) {
                    case 1:
                        textData = Substitution.instance().encryption(message);
                        ReadAndWriteFile.instance().saveData(textData);
                        break;
                    case 2:
                        System.out.println("Type Transposition key");
                        input = new Scanner(System.in);
                        key = input.nextInt();

                        textData = Transposition.instance().encryption(message, (int) key);
                        ReadAndWriteFile.instance().saveData(textData);
                        break;
                }
            }
            else
            {
                Scanner scanner = ReadAndWriteFile.instance().readFile();
                String message = transScannerToStr(scanner);
                System.out.println("Method of your choice!");
                System.out.println("| 1.Substitution  |  \n| 2.transposition |");

                input = new Scanner(System.in);
                userInput = input.nextInt();
                int key;
                String text;

                System.out.println("Choose the location");
                ReadAndWriteFile.instance().openFileDialog();

                switch (userInput) {

                    case 1:
                        System.out.println("Type Substitution key");
                        input = new Scanner(System.in);
                        key = input.nextInt();

                        text = Substitution.instance().decryption(message, key);
                        ReadAndWriteFile.instance().saveData(text);
                        break;

                    case 2:
                        System.out.println("Type Transposition key");
                        input = new Scanner(System.in);
                        key = input.nextInt();

                        text = Transposition.instance().decryption(message, key);
                        ReadAndWriteFile.instance().saveData(text);
                        break;
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    private static String transScannerToStr(Scanner fileTxt)
    {
        StringBuilder builder = new StringBuilder();

        while(fileTxt.hasNextLine())
        {
            builder.append(fileTxt.nextLine() + "\n");
        }

        return builder.toString();
    }
}
