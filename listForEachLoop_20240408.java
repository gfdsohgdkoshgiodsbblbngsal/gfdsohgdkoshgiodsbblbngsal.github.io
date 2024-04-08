import java.util.*; // import all classes in this package.

public class Test1
{
    public static void main(String[] args)
    {
        ArrayList<Integer> myList = new ArrayList<Integer>();
        myList.add(50);
        myList.add(30);
        myList.add(20);
        int total = 0;
        for (Integer value : myList)
        {
            total += value;
        }
        System.out.println("Sum of all elements: " + total);

        // Write a for-each loop that computes the product
        // of all the elements in myList and print out the product.
        total = 1;
        for (Integer value : myList)
        {
            total *= value;
        }
        System.out.println("Product of all elements: " + total);
    }
}

