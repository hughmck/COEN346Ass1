import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class Driver {

    public static void main(String[] args)
            throws IOException, InterruptedException {
        Scanner scanner = new Scanner(new File("input.txt"));
        int[] threadArray = new int [8]; //taking input text and creating an integer array from it
        int i = 0;
        while(scanner.hasNextInt()){
            threadArray[i++] = scanner.nextInt();
        }

        System.out.println("Given Array from input:");
        PrintArray(threadArray);

        MergeSortThread sortArray = new MergeSortThread(threadArray); //creating MergeSortThread object to pass array from input.txt
        sortArray.start(); //starting process
        sortArray.join();
        try
        {
            System.out.println("\nSorted Array:"); //printing out array
            PrintArray(threadArray);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public static void PrintArray(int[] threadArray) {
        for (int i = 0; i < threadArray.length; i++) {
            System.out.println(threadArray[i]);
        }
    }
}
