import java.io.File;
import java.io.IOException;
import java.util.Scanner;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveAction;


public class MergeSortFork {
    public static void main(String[] args)
            throws IOException, InterruptedException {
            Scanner scanner = new Scanner(new File("input.txt"));
            int[] array = new int [8]; //taking input text and creating an integer array from it
            int i = 0;
            while(scanner.hasNextInt()){
                array[i++] = scanner.nextInt();
            }

            System.out.println("Given Array from input:");
            MergeSort.PrintArray(array);

            mergeSortFork(array);

            System.out.println("\nSorted Array:");
            MergeSort.PrintArray(array);
        }

        public static void mergeSortFork(int[] array)
        {
            SortTask mainTask = new SortTask(array);
            ForkJoinPool pool = new ForkJoinPool();
            pool.invoke(mainTask);
        }

    public static void Merge(int[] inputArray, int[] leftHalf, int[] rightHalf) {
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

        private static class SortTask extends RecursiveAction
        {
            private int[] array;

            public String arrayToString(int[] array)
            {
                String output = "";
                for (int i = 0; i < array.length ; i++)
                {
                    output += Integer.toString(array[i]) + ", ";
                }
                return output;
            }
            public SortTask(int[] array){
                this.array = array;
            }
            @Override
            protected void compute() {
                int thread = 1;
                System.out.println("Thread " + thread + ":" + arrayToString(array));
                thread = thread + 1;
                int inputLength = array.length;

                if (inputLength < 2) {
                    return; //if the length of the array is less than 2, return it as there is no more merging required
                }

                int middle = inputLength / 2; //finding the middle index in order to sort the two arrays on either side before the merge
                int[] leftHalf = new int[middle];
                int[] rightHalf = new int[inputLength - middle];

                for (int i = 0; i < middle; i++) { //adding all the numbers which are less than the middle value to the left array
                    leftHalf[i] = array[i];
                }
                for (int i = middle; i < inputLength; i++) { //adding all the numbers which are greater than the middle value to the right array
                    rightHalf[i - middle] = array[i];
                }

                SortTask leftHalfTask = new SortTask(leftHalf); //sorting the left half into ascending order
                SortTask rightHalfTask = new SortTask(rightHalf); //sorting right half into ascending order

                invokeAll(leftHalfTask, rightHalfTask);

                Merge(leftHalf, rightHalf, array); //adding the array together to create a new sorted array
            }
        }
    }



