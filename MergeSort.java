import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class MergeSort {
    public static void main(String[] args)
            throws IOException
    {
        Scanner scanner = new Scanner(new File("input.txt"));
        int[] threadArray = new int [8];
        int i = 0;
        while(scanner.hasNextInt()){
            threadArray[i++] = scanner.nextInt();
        }

        System.out.println("Before:");
        printArray(threadArray);

        mergeSort(threadArray);

        System.out.println("\nAfter:");
        printArray(threadArray);
    }


    private static void mergeSort(int[] inputArray) {
        int inputLength = inputArray.length;

        if (inputLength < 2) {
            return;
        }

        int middle = inputLength / 2;
        int[] leftHalf = new int[middle];
        int[] rightHalf = new int[inputLength - middle];

        for (int i = 0; i < middle; i++) {
            leftHalf[i] = inputArray[i];
        }
        for (int i = middle; i < inputLength; i++) {
            rightHalf[i - middle] = inputArray[i];
        }

        mergeSort(leftHalf);
        mergeSort(rightHalf);

        merge(inputArray, leftHalf, rightHalf);
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

