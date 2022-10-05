import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class MergeSort extends Thread {

    private static class SortThreads extends Thread{
        SortThreads(int[] inputArray, int[] leftHalf, int[] rightHalf){
            super(()->{
                MergeSort.Merge(inputArray, leftHalf, rightHalf);
            });
            this.start();
        }
    }

    public static void threadedSort (int[] threadArray){
        long time = System.currentTimeMillis();

        final int length = threadArray.length;

        boolean exact = length%8 == 0;

        int maxlim = exact? length/8: length/(8-1);

        maxlim = Math.max(maxlim, 8);

        final ArrayList<SortThreads> threads = new ArrayList<>();

        // spawn threads and assign their working index ranges
        // ex: for 16 element list, t1 = 0-3, t2 = 4-7, t3 = 8-11, t4 = 12-15
        for(int i=0; i < length; i+=maxlim){
            int beg = i;
            int remain = (length)-i;
            int end;
            if (remain < maxlim) end = i + (remain - 1);
            else end = i + (maxlim - 1);
            // final SortThreads t = new SortThreads(threadArray, beg, end);
          //  threads.add(t);
        }
        for(Thread t: threads){
            try{
                t.join();
            } catch(InterruptedException ignored){}
        }

        for(int i=0; i < length; i+=maxlim){
            int mid = i == 0? 0 : i-1;
            int remain = (length)-i;
            int end = remain < maxlim? i+(remain-1): i+(maxlim-1);
          //  MergeSort.Merge(threadArray, mid, end);
        }
        time = System.currentTimeMillis() - time;
        System.out.println("Time spent for custom multi-threaded recursive merge_sort(): "+ time+ "ms");
    }


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

        MergeSort(threadArray);

        System.out.println("\nSorted Array:");
        PrintArray(threadArray);

        for (int x = 0; x < 8; x++) { //running the MultithreadingDemoclass 8 times
            MultithreadingDemo object = new MultithreadingDemo(); //calling a new MultiThreadingDemo object
            object.start(); //branches off a brand new thread, and executes the run method
            object.join();
        }
    }



    public static void MergeSort(int[] inputArray) {
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

        MergeSort(leftHalf); //sorting the left half into ascending order
        MergeSort(rightHalf); //sorting right half into ascending order

        Merge(inputArray, leftHalf, rightHalf); //adding the array together ro create a new sorted array
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

    public static void PrintArray(int[] threadArray) {
        for (int i = 0; i < threadArray.length; i++) {
            System.out.println(threadArray[i]);
        }
    }
}

