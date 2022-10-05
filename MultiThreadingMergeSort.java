import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

public class MultiThreadingMergeSort extends Thread {		//class to implement MultiThreaded MergeSort

    //function to handle threads and sort array
    private static <E> void multithreadedMergeSort(E[] array, int fromIndex, int toIndex, Comparator<? super E> comparator,
                                                   int noOfThreadsAvailable, FileWriter writer)
    {
        if (toIndex - fromIndex > 0) {
            if (noOfThreadsAvailable <= 1) {		//if no of available threads <= 1 then merge sorting the array
                mergeSort(array, fromIndex, toIndex, comparator, writer);
            }
            else {
                int middle = toIndex / 2;			//getting middle element to sort
                Thread firstHalfOfArray = new Thread() {			//thread for first half of array
                    public void run() {
                        try {				//printing starting of thread
                            writer.write("\n" + getName() + " started: ");
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        //calling multithreadedMergeSort() recursively
                        multithreadedMergeSort(array, fromIndex, middle, comparator, noOfThreadsAvailable - 1, writer);
                    }
                };
                Thread secondHalfOfArray = new Thread() {		//thread for second half of array
                    public void run() {
                        try {			//printing starting of thread
                            writer.write("\n" + getName() + " started: ");
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        //calling multithreadedMergeSort() recursively
                        multithreadedMergeSort(array, middle + 1, toIndex, comparator, noOfThreadsAvailable - 1, writer);
                    }
                };

                //starting threads
                firstHalfOfArray.start();
                secondHalfOfArray.start();

                try {		//joining threads
                    firstHalfOfArray.join();
                    if (!firstHalfOfArray.isAlive()) {			//if thread is not alive then it means it is finished
                        writer.write('\n' + firstHalfOfArray.getName() + " finished:");
                    }
                    secondHalfOfArray.join();
                    if (!secondHalfOfArray.isAlive()) {			//if thread is not alive then it means it is finished
                        writer.write('\n' + secondHalfOfArray.getName() + " finished:");
                    }

                } catch (InterruptedException ie) {
                    ie.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                //merging array after sorting
                merge(array, fromIndex, middle, toIndex, comparator, writer);
            }
        }
    }

    //function to implement merge sort
    private static <E> void mergeSort(E[] array, int fromIndex, int toIndex, Comparator<? super E> comparator, FileWriter writer) {
        if (fromIndex == toIndex) {		//if one element is left then it is already sorted
            return;
        }
        if (toIndex - fromIndex > 0) {			//if more than one element
            int middleElement = (fromIndex + toIndex) / 2;		//getting middle one
            //recursively calling mergesort()
            mergeSort(array, fromIndex, middleElement, comparator, writer);
            mergeSort(array, middleElement + 1, toIndex, comparator, writer);
            //merging array after sorting
            merge(array, fromIndex, middleElement, toIndex, comparator, writer);
        }
    }

    //function than calls multithreadedMergeSort
    public static <E> void sort(E[] array, Comparator<? super E> comparator, int noOfThreads, FileWriter writer) {
        multithreadedMergeSort(array, 0, array.length - 1, comparator, noOfThreads, writer);
    }

    //function to merge data after sorting
    @SuppressWarnings("unchecked")
    private static <E> void merge(E[] array, int fromIndex, int middleElement, int toIndex, Comparator<? super E> comparator, FileWriter writer) {
        int num = toIndex - fromIndex + 1;
        Object[] obj = new Object[num];
        int index1 = fromIndex;
        int index2 = middleElement + 1;
        int j = 0;

        //checking elements of both arrays, comparing them and storing the smaller one
        while (index1 <= middleElement && index2 <= toIndex) {
            if (comparator.compare(array[index1], array[index2]) < 0) {
                obj[j] = array[index1];
                index1++;
            }
            else {
                obj[j] = array[index2];
                index2++;
            }
            j++;
        }

        //checking element from first half
        while (index1 <= middleElement) {
            obj[j] = array[index1];
            index1++;
            j++;
        }

        //checking element from second half
        while (index2 <= toIndex) {
            obj[j] = array[index2];
            index2++;
            j++;
        }

        //updating original array
        for (j = 0; j < num; j++) {
            array[fromIndex + j] = (E) obj[j];
        }

        //writing sorted elements to output file
        for (int i = fromIndex; i <= toIndex; i++) {
            try {
                writer.write(array[i] + ", ");
            }
            catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}