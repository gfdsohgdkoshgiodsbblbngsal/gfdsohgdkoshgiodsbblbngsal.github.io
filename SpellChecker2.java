import java.io.*;
import java.nio.file.*;
import java.util.*;

public class SpellChecker
{
    // This dictionary includes 10,000 English words read in from the dictionary
    // file
    private static String[] dictionary = new String[10000];

    /* Write a spellcheck() method using an enhanced for each loop
     * that takes a word as a parameter and returns true if it is
     * in the dictionary array. Return false if it is not found.
     */
    public static boolean spellcheck(String word) {
        for (String d : dictionary) {
            if (d.equals(word)) {
                return true;
            }
        }
        
        return false;
    }
    
    /* Write a checkText() method that takes a String[] parameter which is a sentence
     * of text in a String array and then calls your spellcheck method above
     * to check if each word in that text is spelled correctly.
     * Use an enhanced for each loop.
     * It should count and print out the misspelled words, and return the count.
     */
    public int checkText(String[] texts){
        int misspelled = 0;
        for (String text: texts) {
            if (!spellcheck(text)) {
                misspelled += 1;
                System.out.println(text);
            }
        }
        
        return misspelled;
    }

    // Do not change "throws IOException" which is needed for reading in the input
    // file
    public static void main(String[] args) throws IOException
    {
        SpellChecker checker = new SpellChecker();
        // Uncomment to test your method
        String word = "catz";
        System.out.println(word + " is spelled correctly? " + checker.spellcheck(word));
        System.out.println(word + " is spelled correctly? " + checker.spellcheck("cat"));

        // Testing checkText method
        String text = "Catz are cool aminals!";
        // replace punctuation symbols with empty string
        text = text.replaceAll("\\p{Punct}", "");
        // convert to lowercase
        text = text.toLowerCase();
        // split the text into a String array
        String[] words = text.split(" ");
        // Call your checkText method
        int numErrors = checker.checkText(words);
        System.out.println("There were " + numErrors + " spelling errors in " + text);
        
    }

    // The constructor reads in the dictionary from a file
    public SpellChecker() throws IOException
    {
        // Let's use java.nio method readAllLines and convert to an array!
        List<String> lines = Files.readAllLines(Paths.get("dictionary.txt"));
        dictionary = lines.toArray(dictionary);

        /* The old java.io.* Scan/File method of reading in files, replaced by java.nio above // create File object
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

