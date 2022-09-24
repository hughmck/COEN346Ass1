public class MultithreadHelper extends Thread{

    @Override
    public void run(){
        for(int i =1; i<= 5; i++){
            System.out.println("thread test");
            try {
                Thread.sleep(1000);}
            catch (InterruptedException e) {
            }
        }
    }
}
