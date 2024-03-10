import java.io.*;
import java.nio.file.*;
import java.util.*;

public class SpellChecker
{
    // This dictionary has 10,000 English words read in from a dictionary file in
    // the constructor
    private static String[] dictionary = new String[10000];

    /* 1. Write a print10() method that prints out the first
     * 10 words of the dictionary array. Do not print out the whole array!
     */
    public static void print10()
    {
            for (int i = 0; i < 10; i++)
            {
                System.out.println(dictionary[i]);
            }
    }

    /* 2. Write a spellcheck() method that takes a word as a
     * parameter and returns true if it is in the dictionary array.
     * Return false if it is not found.
     */
    public static boolean spellcheck(String word) {
        for (String entry : dictionary) {
            if (entry != null && entry.equals(word)) {
                return true;
            }
        }
        return false;
    }

    // Do not change "throws IOException" which is needed for reading in the input
    // file
    public static void main(String[] args) throws IOException
    {
        SpellChecker checker = new SpellChecker();
        // Uncomment to test Part 1
        checker.print10();

        // Uncomment to test Part 2
        String word = "catz";


        if (checker.spellcheck(word) == true)
        {
            System.out.println(word + " is spelled correctly!");
        }
        else
        {
            System.out.println(word + " is misspelled!");
        }

        word = "cat";
        System.out.println(word + " is spelled correctly? " + checker.spellcheck(word));
        

        // 3. optional and not autograded
        // checker.printStartsWith("b");
    }

    // The constructor reads in the dictionary from a file
    public SpellChecker() throws IOException
    {
        // Let's use java.nio method readAllLines and convert to an array!
        List<String> lines = Files.readAllLines(Paths.get("dictionary.txt"));
        dictionary = lines.toArray(dictionary);

        /* The old java.io.* Scan/File method of reading in files, replaced by java.nio above
        // create File object
        File dictionaryFile = new File("dictionary.txt");

        //Create Scanner object to read File
        Scanner scan = new Scanner(dictionaryFile);

        // Reading each line of the file
        // and saving it in the array
        int i = 0;
        while(scan.hasNextLine())
        {
            String line = scan.nextLine();
            dictionary[i] = line;
            i++;
        }
        scan.close();
        */
    }
}

