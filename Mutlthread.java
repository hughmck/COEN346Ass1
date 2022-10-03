public class Mutlthread {
    public static void main(String[] args) throws InterruptedException {
        final int NUMBER_OF_CORES = 8; //my computer has 8 cores, and can handle 8 kernel threads as long as they're ran concurrently
        for (int i = 0; i < NUMBER_OF_CORES; i++) { //creating 8 new branches
            MultithreadingDemo object = new MultithreadingDemo();
            object.start(); //branches off a brand new thread, and executes the run method
            object.join();

        }
    }
}
