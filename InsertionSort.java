import java.util.Arrays;

public class SortTest
{
    public static void insertionSort(int[] elements)
    {
        int count = 0;
        
        for (int j = 1; j < elements.length; j++)
        {
            int temp = elements[j];
            int possibleIndex = j;
            while (possibleIndex > 0 && temp < elements[possibleIndex - 1])
            {
                elements[possibleIndex] = elements[possibleIndex - 1];
                possibleIndex--;
            }
            elements[possibleIndex] = temp;
            
            count++;
        }
        
        System.out.println("Number of iterations: " + count);
    }

    public static void main(String[] args)
    {
        int[] arr1 = {1, 2, 3, 5, 4};
        System.out.println(Arrays.toString(arr1));
        insertionSort(arr1);
        System.out.println(Arrays.toString(arr1));
    }
}

