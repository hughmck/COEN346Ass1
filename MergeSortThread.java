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

    public static void Merge(int[] arrayOfStrings, int[] left, int[] right) { //takes the integer array as an argument, as well as left array and right array
        int leftArraySize = left.length; //initializes the length of the left and right array
        int rightArraySize = right.length;

        //need three variables, one to iterate through left array, one to go through right array, and one for final merged array
        int x = 0; //left array
        int y = 0;//right array
        int z = 0; //merged array

        while (x < leftArraySize && y < rightArraySize) { //looping through left and right array
            if (left[x] <= right[y]) { //comparing the elements in left array to the right array
                arrayOfStrings[z] = left[x]; //if left value is smaller than the right value, add it to the merged array
                x++; //keep going through left array
            }
            else {
                arrayOfStrings[z] = right[y];  //adding values that are larger into right side of array
                y++;
            }
            z++; //iterating through merged array
        }

        while (x < leftArraySize) {
            arrayOfStrings[z] = left[x];
            x++;
            z++; //adding the smallest values in left array into the array of strings
        }

        while (y < rightArraySize) {
            arrayOfStrings[z] = right[y]; //adding the larger values from the right array into array of strings
            y++;
            z++;
        } //the merge method will now have combined both arrays in an ascending order
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