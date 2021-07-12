package Futures;

public class App {

    public static void main(String[] args) throws InterruptedException {


//        MyThread myThread = new MyThread();
//        myThread.start();

/*

        // 2. implement Runnable
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("Hello : "+ Thread.currentThread().getName());
            }
        });

        thread.start();

*/
/*

        // 3. Use lambda expression
        Thread thread = new Thread(() -> {

            try {
                Thread.sleep(1000L);
            } catch (InterruptedException e) { // 누가 깨우면
                e.printStackTrace();
            }
            System.out.println("Thread : "+ Thread.currentThread().getName());
        });

*/
/*
        Thread newThread = new Thread(() -> {
           while (true) {
               System.out.println("Thread : "+ Thread.currentThread().getName());
               try {
                   Thread.sleep(2000L);
               } catch (InterruptedException e) {
                   System.out.println("Exit!");
                   return;
               }
           }
        });

        newThread.start();
        // print out main thread
        System.out.println(Thread.currentThread().getName());

        // cause an interruption to a thread to stop
        Thread.sleep(6000L);
        newThread.interrupt();

*/

        Thread newThread = new Thread(() -> {
            System.out.println("Thread : "+ Thread.currentThread().getName());
            try {
                Thread.sleep(2000L);
                System.out.println("sleeping");
            } catch (InterruptedException e) {
                System.out.println("Exit!");
                return;
            }
        });

        // join a thread work to a main thread so that the last command is, by no means, executed after newThread is done.
        newThread.start(); // 1
        newThread.join(); // 2
        System.out.println(newThread+" is finished"); // 3

        // Problem is that as the number of threads increases, it becomes to manage them altogether.
    }

    // 1. create a sub class inherent to Thread class
    static class MyThread extends Thread {

        @Override
        public void run() {
            System.out.println("Hello : "+Thread.currentThread().getName());
        }
    }
}
