package homework_01;

import java.util.Scanner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MyThread extends Thread{
    int number;

    public MyThread(int number) {
        this.number = number;
    }

    public synchronized void run (){
        Scanner sc = new Scanner(System.in);
        int n0 = 1;
        int n1 = 1;
        int n2;
        System.out.print("Потік Thread :");
        System.out.print(n0+" "+n1+" ");
        for(int i = 3; i <= number; i++){
            n2=n0+n1;
            System.out.print(n2+" ");
            n0=n1;
            n1=n2;
        }
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("How number you want?:");
        int number = sc.nextInt();
        MyThread myThread = new MyThread(number);
        ExecutorService executorService1 = Executors.newFixedThreadPool(4);
        ExecutorService executorService2 = Executors.newSingleThreadExecutor();
        ExecutorService executorService3 = Executors.newScheduledThreadPool(4);
        Thread thread = new Thread(new RunnableThread(number));

        myThread.run();
        thread.run();
        System.out.println();
        System.out.println();
        executorService1.execute(thread);
        executorService1.execute(thread);
        executorService1.execute(thread);
        executorService1.execute(thread);
        executorService1.execute(thread);
        System.out.println();
        executorService2.execute(thread);
        executorService2.execute(thread);
        executorService2.execute(thread);
        executorService2.execute(thread);
        executorService2.execute(thread);
        System.out.println();
        executorService3.execute(thread);
        executorService3.execute(thread);
        executorService3.execute(thread);
        executorService3.execute(thread);
        executorService3.execute(thread);
        executorService1.shutdown();
        executorService2.shutdown();
        executorService3.shutdown();
        /*
        myThread.run();
        thread.run();

         */
    }
    static class RunnableThread implements Runnable{
        int number;

        public RunnableThread(int number) {
            this.number = number;
        }
        @Override
        public void run() {
            int[] mas = new int[number+1];
            mas[1] = 1;
            mas[2] = 1;
            int n2;

            System.out.println();
            System.out.print("Потік Runnable:");
            for(int i = 3; i < number; i++){
                mas[i] = mas[i-1] + mas[i-2];
            }
            for (int i = mas.length-2;i>=0;i--){
                System.out.print(mas[i]+" ");
            }
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
