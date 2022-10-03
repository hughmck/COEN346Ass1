import java.io.File;
import java.io.IOException;
import java.util.Scanner;


public class Driver {
    public static void main(String[] args)
            throws IOException
    {
        Scanner scanner = new Scanner(new File("input.txt"));
        int[] threadArray = new int [8]; //taking input text and creating an integer array from it
        int i = 0;
        while(scanner.hasNextInt()){
            threadArray[i++] = scanner.nextInt();
        }

        System.out.println("Given Array from input:");
        PrintArray printArray = new PrintArray(threadArray); //want to create a new print array object and then call printarray from mergesorrt
        MergeSort.printArray(threadArray);

        MergeSort mergeSort = new MergeSort(); //want to create a new print array object and then call printarray from mergesorrt
        MergeSort.mergeSort(threadArray);

        System.out.println("\nSorted Array:");
        printArray(threadArray);
    }
}
