package com.demo;

/**
 * @ClassName PrintAbc
 * @Description TODO
 * @Author qiuxinfa
 * @Date 2020/10/31 22:11
 **/
public class PrintAbc {
    public static void main(String[] args) {
        MyResource myResource = new MyResource();
        ThreadA threadA = new ThreadA(myResource);
        ThreadB threadB = new ThreadB(myResource);
        ThreadC threadC = new ThreadC(myResource);

        threadA.start();
        threadB.start();
        threadC.start();
    }
}

// 共享资源
class MyResource{
    private volatile int flag = 0;
    private static final int total = 10;

    public synchronized void printABC(int val){
        for(int i=0;i<total;i++){
            while (flag % 3 != val){
                try {
                    wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            System.out.println(Thread.currentThread().getName()+" Print"+(val == 0 ? "A" : val == 1 ? "B" : "C"));
            flag++;
            notifyAll();
        }
    }

}

class ThreadA extends Thread{

    private MyResource myResource;

    public ThreadA(MyResource myResource){
        super("线程A");
        this.myResource = myResource;
    }

    @Override
    public void run() {
        myResource.printABC(0);
    }
}

class ThreadB extends Thread{

    private MyResource myResource;

    public ThreadB(MyResource myResource){
        super("线程B");
        this.myResource = myResource;
    }

    @Override
    public void run() {
        myResource.printABC(1);
    }
}

class ThreadC extends Thread{

    private MyResource myResource;

    public ThreadC(MyResource myResource){
        super("线程C");
        this.myResource = myResource;
    }

    @Override
    public void run() {
        myResource.printABC(2);
    }
}
