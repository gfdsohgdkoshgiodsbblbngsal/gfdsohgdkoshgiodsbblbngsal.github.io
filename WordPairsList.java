import java.util.*;

public class WordPairsList
{
    private ArrayList<WordPair> allPairs;

    public WordPairsList(String[] words)
    {
        // WRITE YOUR CODE HERE
        // initialize allPairs to an empty ArrayList of WordPair objects
        allPairs = new ArrayList<WordPair>();
        // nested loops through the words array to add each pair to allPairs
        for (int i = 0; i < words.length-1; i++) {
            for (int j = i + 1; j < words.length; j++) {
                allPairs.add(new WordPair(words[i], words[j]));
            }
        }
    }

    public int numMatches()
    {
        int matches = 0;
        for (WordPair pair : allPairs) {
            if (pair.getFirst().equals(pair.getSecond())) {
                matches++;
            }
        }
        return matches;
    }

    public String toString()
    {
        return allPairs.toString();
    }

    public static void main(String[] args)
    {
        String[] words = {"Hi", "there", "Tyler", "Sam"};
        WordPairsList list = new WordPairsList(words);
        System.out.println(list);
        // For second part below, uncomment this test:
        System.out.println("The number of matched pairs is: " + list.numMatches());
    }
}

class WordPair
{
    private String word1;
    private String word2;

    public WordPair(String word1, String word2)
    {
        this.word1 = word1;
        this.word2 = word2;
    }

    public String getFirst()
    {
        return word1;
    }

    public String getSecond()
    {
        return word2;
    }

    public String toString()
    {
        return "(" + word1 + ", " + word2 + ")";
    }
}

 
