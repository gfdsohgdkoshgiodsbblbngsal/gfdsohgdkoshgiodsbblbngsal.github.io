import java.io.*;
import java.nio.file.*;
import java.util.*;

public class SpellChecker
{
    private ArrayList<String> dictionary;

    /* Constructor populates the dictionary ArrayList from the file dictionary.txt*/
    public SpellChecker() throws IOException
    {
        List<String> lines = Files.readAllLines(Paths.get("dictionary.txt"));
        dictionary = new ArrayList<String>(lines);
    }

    /**
     * Write a linearSearch(word) method that finds a word
     * in the ArrayList dictionary. It should also keep
     * a count of the number of words checked.
     *
     * @param String word to be found in elements.
     * @return a count of how many words checked before returning.
     */
    public int linearSearch(String word)
    {
        for (int i = 0; i < dictionary.size(); i++) {
            if (word.equals(dictionary.get(i))) {
                return i+1;
            }
        }
        
        return -1;
    }

    /**
     * Write a binarySearch(word) method that finds the word
     * in the ArrayList dictionary. It should also keep
     * a count of the number of words checked.
     *
     * @param String word to be found in elements.
     * @return a count of how many words checked before returning.
     */
    public int binarySearch(String word)
    {
        int left = 0;
        int right = dictionary.size() - 1;
        
        int i = 0;
        while (left <= right) {
            int middle = (left + right) / 2;
            i++;
            
            int comparison = word.compareTo(dictionary.get(middle));
            if (comparison < 0) {
                right = middle - 1;
            } else if (comparison > 0) {
                left = middle + 1;
            } else {
                return i;
            }
        }
        
        return -1;
    }

    public static void main(String[] args) throws IOException
    {
        SpellChecker checker = new SpellChecker();
        String word = "catz";
        int i = checker.linearSearch(word);
        System.out.println("Linear search steps for " + word + " = " + i);
        int count = checker.binarySearch(word);
        System.out.println("Binary search steps for " + word + " = " + count);
    }
}

