public class Mutlthread {
    public static void main(String[] args) throws InterruptedException {
        int n = 8; // Number of threads
        for (int i = 0; i < n; i++) { //creating 8 new branches
            MultithreadingDemo object = new MultithreadingDemo();
            object.start(); //branches off a brand new thread, and executes the run method
            object.join();
        }
    }
}
