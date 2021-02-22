package subs_trans;

import javax.swing.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

/**
 * For more details please have a look at pdf attachment
 *
 *
 */

/**
 * The type Read and write file.
 */
public class ReadAndWriteFile {
    private static ReadAndWriteFile _occurrence;

    private File _filePath;
    private Scanner _scan;


    /**
     * Instance read and write file.
     *
     * @return the read and write file
     */
    public static ReadAndWriteFile instance()
    {
        if(_occurrence == null)
        {
            _occurrence = new ReadAndWriteFile();
        }
        return _occurrence;
    }


    /**
     * Open file dialog.
     */
    public void openFileDialog()
    {
        System.out.println("Provide your file location for en/decryption");
        JFileChooser jFileChooser = new JFileChooser();
        jFileChooser.showOpenDialog(null);
        _filePath = jFileChooser.getSelectedFile();
        if(_filePath == null)
        {
            System.err.println("Failed or Terminated " + jFileChooser.getName());
            System.exit(0);
        }
    }

    /**
     * Read file scanner.
     *
     * @return the scanner
     * @throws IOException the io exception
     */
    public Scanner readFile() throws IOException
    {
        try{
            _scan = new Scanner(_filePath);
        }catch(FileNotFoundException e){
            e.printStackTrace();
        }

        System.out.println("File path:  " + _filePath.getPath());

        return _scan;
    }

    /**
     * Save data.
     *
     * @param textData the text data
     * @throws IOException the io exception
     */
    public void saveData(String textData)throws IOException
    {
        PrintWriter printer = new PrintWriter(_filePath);
        printer.print(textData);
        printer.close();

        System.out.println("Data successfully saved in: " + _filePath.getPath());
    }
}

