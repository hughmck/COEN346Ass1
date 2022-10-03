import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

class MergeSort {

    private static final int NUMBER_OF_CORES = 8; //my computer has 8 cores, and can handle 8 kernel threads as long as they're ran concurrently

    void mergingArrays(int integerArray[], int leftArray, int middle, int rightArray)
    {
        int n1 = middle - leftArray + 1;
        int n2 = rightArray - middle;

        int L[] = new int[n1];
        int R[] = new int[n2];

        for (int i = 0; i < n1; ++i)
            L[i] = integerArray[leftArray + i]; //adding all numbers less than n1 to the left array
        for (int j = 0; j < n2; ++j) //adding al numbers less than n2 to the right array
            R[j] = integerArray[middle + 1 + j];

        int i = 0, j = 0;

        int k = leftArray;
        while (i < n1 && j < n2) {
            if (L[i] <= R[j]) {
                integerArray[k] = L[i];
                i++;
            }
            else {
                integerArray[k] = R[j];
                j++;
            }
            k++;
        }

        while (i < n1) {
            integerArray[k] = L[i];
            i++;
            k++;
        }

        while (j < n2) {
            integerArray[k] = R[j];
            j++;
            k++;
        }
    }

    void sort(int arr[], int l, int r)
    {
        if (l < r) {
            // Find the middle point
            int m =l+ (r-l)/2;

            // Sort first and second halves
            sort(arr, l, m);
            sort(arr, m + 1, r);

            // Merge the sorted halves
            mergingArrays(arr, l, m, r);
        }
    }

    public static void threadedSort(Integer[] array){
        long time = System.currentTimeMillis();
        final int length = array.length;
        boolean exact = length%NUMBER_OF_CORES == 0;
        int maxNumberOfThreads;
        if (exact) maxNumberOfThreads = length / NUMBER_OF_CORES; //if the number of threads can all be done concurrently, do it, if not
        else maxNumberOfThreads = length / (NUMBER_OF_CORES - 1);
        maxNumberOfThreads = Math.max(maxNumberOfThreads, NUMBER_OF_CORES); //
        // To keep track of threads
        final ArrayList<MergeSort> threads = new ArrayList<>();

        for(int i=0; i < length; i+=maxNumberOfThreads){
            int begin = i;
            int remain = (length)-i;
            int end = remain < maxNumberOfThreads? i+(remain-1): i+(maxNumberOfThreads-1);
            final MergeSort t = new MergeSort();
            // Add the thread references to join them later
            threads.add(t);
        }
        for(MergeSort t: threads){
            try{
                t.join();
            } catch(InterruptedException ignored){}
        }

        for(int i=0; i < length; i+=maxNumberOfThreads){
            int mid = i == 0? 0 : i-1;
            int remain = (length)-i;
            int end = remain < maxNumberOfThreads? i+(remain-1): i+(maxNumberOfThreads-1);
            // System.out.println("Begin: "+0 + " Mid: "+ mid+ " End: "+ end + " MAXLIM = " + maxlim);
            merge(array, 0, mid, end);
        }
        time = System.currentTimeMillis() - time;
        System.out.println("Time spent for custom multi-threaded recursive merge_sort(): "+ time+ "ms");
    }


    // Typical recursive merge sort

    /* A utility function to print array of size n */
    static void printArray(int arr[])
    {
        int n = arr.length;
        for (int i = 0; i < n; ++i)
            System.out.print(arr[i] + " ");
        System.out.println();
    }

    // Driver code
    public static void main(String args[])
            throws IOException
    {

        Scanner scanner = new Scanner(new File("input.txt"));
        int[] threadArray = new int [8];
        int i = 0;
        while(scanner.hasNextInt()){
            threadArray[i++] = scanner.nextInt();
        }
        
        System.out.println("Given Array");
        printArray(threadArray);

        MergeSort ob = new MergeSort();
        ob.sort(threadArray, 0, threadArray.length - 1);

        System.out.println("\nSorted array");
        printArray(threadArray);



    }
}

