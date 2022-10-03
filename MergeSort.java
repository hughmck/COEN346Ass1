import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class MergeSort {
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
        printArray(threadArray);

        mergeSort(threadArray);

        System.out.println("\nSorted Array:");
        printArray(threadArray);
    }


    public static void mergeSort(int[] inputArray) {
        int inputLength = inputArray.length;

        if (inputLength < 2) {
            return; //if the length of the array is less than 2, return it as there is no more merging required
        }

        int middle = inputLength / 2; //finding the middle index in order to sort the two arrays on either side before the merge
        int[] leftHalf = new int[middle];
        int[] rightHalf = new int[inputLength - middle];

        for (int i = 0; i < middle; i++) { //adding all the numbers which are less than the middle value to the left array
            leftHalf[i] = inputArray[i];
        }
        for (int i = middle; i < inputLength; i++) { //adding all the numbers which are greater than the middle value to the right array
            rightHalf[i - middle] = inputArray[i];
        }

        mergeSort(leftHalf); //sorting the left half into ascending order
        mergeSort(rightHalf); //sorting right half into ascending order

        merge(inputArray, leftHalf, rightHalf); //adding the array together ro create a new sorted array
    }

    private static void merge (int[] inputArray, int[] leftHalf, int[] rightHalf) {
        int leftSize = leftHalf.length;
        int rightSize = rightHalf.length;

        int i = 0;
        int j = 0;
        int k = 0;

        while (i < leftSize && j < rightSize) {
            if (leftHalf[i] <= rightHalf[j]) {
                inputArray[k] = leftHalf[i];
                i++;
            }
            else {
                inputArray[k] = rightHalf[j];
                j++;
            }
            k++;
        }

        while (i < leftSize) {
            inputArray[k] = leftHalf[i];
            i++;
            k++;
        }

        while (j < rightSize) {
            inputArray[k] = rightHalf[j];
            j++;
            k++;
        }

    }

    private static void printArray(int[] threadArray) {
        for (int i = 0; i < threadArray.length; i++) {
            System.out.println(threadArray[i]);
        }
    }
}

