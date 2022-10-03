public class MultithreadingDemo extends Thread {
    private int threadID;

    public void MultiThreadIDNumber (int threadID){
        this.threadID = threadID;
    }

    @Override
    public void run()
    {
        for(int i = 1; i <= 8; i++){
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


}
