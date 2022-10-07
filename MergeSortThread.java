public class MergeSortThread extends  Thread {
    private int[] array;
    public MergeSortThread(int[] array) {
        this.array = array;
    }

    public void run() {
        System.out.println("Thread " + this.getId() + " is starting " + arrayToString(array)); //outputing the thread ID and its current segment of the array its assigned to
        int inputLength = array.length;
        if (inputLength > 1) { //checking if the segment has more than one element, if its a single element, no more splitting needs to be done
            int middle = inputLength / 2; //finding the middle index in order to sort the two arrays on either side before the merge
            int[] leftHalf = new int[middle];
            int[] rightHalf = new int[inputLength - middle];

            for (int i = 0; i < middle; i++) { //adding all the numbers which are less than the middle value to the left array
                leftHalf[i] = array[i];
            }
            for (int i = middle; i < inputLength; i++) { //adding all the numbers which are greater than the middle value to the right array
                rightHalf[i - middle] = array[i];
            }
            MergeSortThread leftHalfThread = new MergeSortThread(leftHalf); //assigning thread to lefthalf
            MergeSortThread rightHalfThread = new MergeSortThread(rightHalf); //assigning thread to righthalf
            leftHalfThread.start(); //begin execution of left thread
            rightHalfThread.start(); //begin execution of right thread
            try
            {
                leftHalfThread.join(); //trying to merge once both threads are done. Can't merge if one thread is still executing
                rightHalfThread.join();
                Merge(array, leftHalf, rightHalf); //Merging the two array segments

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("Thread " + this.getId() + " is finished " + arrayToString(array)); //ouputs that the thread is done and what the worked on segment of the array looks like

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

    public String arrayToString(int[] array)
    {
        String output = "";
        for (int i = 0; i < array.length ; i++)
        {
            output += Integer.toString(array[i]) + ", ";
        }
        return output;
    }
}