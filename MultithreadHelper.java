
public class MultithreadHelper extends Thread{

    @Override
    public void run(){

        MergeSort getArray = new MergeSort();

        int[] multiThreadArray = getArray.getNumArray(); //trying to take the array from the MergeSort file into here, so we can use a loop and initilize each thread

        for(int i =1; i<= 5; i++){
            System.out.println("thread test");
            try {
                Thread.sleep(1000);}
            catch (InterruptedException e) {
            }
        }
    }
}
