import java.io.File;
import java.io.IOException;
import java.util.Scanner;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveAction;


public class MergeSortFork {
    public static void main(String[] args)
            throws IOException, InterruptedException {
//            Scanner scanner = new Scanner(new File("input.txt"));
//            int[] array = new int [8]; //taking input text and creating an integer array from it
//            int i = 0;
//            while(scanner.hasNextInt()){
//                array[i++] = scanner.nextInt();
//            }
//
//            System.out.println("Given Array from input:");
//            PrintArray(array);
//
//            mergeSortFork(array);
//
//            System.out.println("\nSorted Array:");
//            PrintArray(array);
        }

        public static void mergeSortFork(int[] array)
        {
            SortTask mainTask = new SortTask(array);
            ForkJoinPool pool = new ForkJoinPool();
            pool.invoke(mainTask);
        }

    public static void PrintArray(int[] threadArray) {
        for (int i = 0; i < threadArray.length; i++) {
            System.out.println(threadArray[i]);
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
            }
        }
    }



