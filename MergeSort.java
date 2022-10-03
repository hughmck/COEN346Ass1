import java.io.File;
import java.io.IOException;
import java.util.Scanner;

class MergeSort extends Thread {
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

    /* A utility function to print array of size n */
    static void printArray(int arr[])
    {
        int n = arr.length;
        for (int i = 0; i < n; ++i)
            System.out.print(arr[i] + " ");
        System.out.println();
    }

    static void run(){
        for(int i =1; i<= 5; i++){
            System.out.println("thread test");
            try {
                Thread.sleep(1000);}
            catch (InterruptedException e) {
            }
        }
    }

    // Driver code
    public static void main(String args[])
            throws IOException
    {

        Scanner scanner = new Scanner(new File("input.txt"));
        int [] threadArray = new int [8];
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

        MultithreadHelper multithreadHelper = new MultithreadHelper();
        multithreadHelper.start();

        run();
    }
}

