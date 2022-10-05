import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Random;
import java.util.Scanner;

public class driver {

    public static void main(String[] args) throws IOException {
        //reading data from given text file
        Scanner scanner = new Scanner(new File("input.txt"));
        Integer[] threadArray = new Integer[8];
        int i = 0;
        while (scanner.hasNextInt()) {			//storing data in threadArray
            threadArray[i++] = scanner.nextInt();
        }
        //getting number of available threads
        int noOfThreads = (Runtime.getRuntime().availableProcessors()) * 2;
        Comparator<Integer> comparator = new Comparator<Integer>() {			//setting comparator object for sorting
            public int compare(Integer d1, Integer d2) {
                return d1.compareTo(d2);
            }
        };

        FileWriter myWriter = new FileWriter("Output.txt");		//opening file to write output
        MultiThreadingMergeSort.sort(threadArray, comparator, noOfThreads, myWriter);		//calling sort() to sort threadArray using multithreading
        myWriter.close();
    }
}