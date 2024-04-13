import java.util.Arrays;

public class SortTest
{
    public static void selectionSort(int[] elements)
    {
        int count = 0;
        
        for (int j = 0; j < elements.length - 1; j++)
        {
            int minIndex = j;
            for (int k = j + 1; k < elements.length; k++)
            {
                if (elements[k] < elements[minIndex])
                {
                    minIndex = k;
                }
            }
            int temp = elements[j];
            elements[j] = elements[minIndex];
            elements[minIndex] = temp;
            count++;
        }
        
        System.out.println("Number of iterations: " + count);
    }

    public static void main(String[] args)
    {
        int[] arr1 = {1, 2, 3, 5, 4};
        System.out.println(Arrays.toString(arr1));
        selectionSort(arr1);
        System.out.println(Arrays.toString(arr1));
    }
}

