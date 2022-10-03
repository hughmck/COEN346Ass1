public class MultithreadingDemo extends Thread {
    public int threadID;

    public void MultiThreadIDNumber (int threadID){
        threadID = threadID;
    }

    final int NUMBER_OF_CORES = 8; //my computer has 8 cores, and can handle 8 kernel threads as long as they're ran concurrently

    public void run()
    {
        for(int i = 1; i <= NUMBER_OF_CORES; i++){ //creating 8 threads
            System.out.println(i + " from thread number " + threadID);
        }
        try {
            Thread.sleep(1000);
        }
        catch (Exception e) {
            // Throwing an exception
            System.out.println("Exception is caught");
        }
    }
}
