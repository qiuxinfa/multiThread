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
//    private static final int total = 10;

    public synchronized void PrintA(){
        while (flag % 3 == 0){
            System.out.println(Thread.currentThread().getName()+" PrintA ");
            flag++;
            notifyAll();
        }
        try {
            wait();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public synchronized void PrintB(){
        while (flag % 3 == 1){
            System.out.println(Thread.currentThread().getName()+" PrintB ");
            flag++;
            notifyAll();
        }
        try {
            wait();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public synchronized void PrintC(){
        while (flag % 3 == 2){
            System.out.println(Thread.currentThread().getName()+" PrintC ");
            flag++;
            notifyAll();
        }
        try {
            wait();
        } catch (InterruptedException e) {
            e.printStackTrace();
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
        for (int i=0;i<10;i++){
            myResource.PrintA();
        }
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
        for (int i=0;i<10;i++){
            myResource.PrintB();
        }
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
        for (int i=0;i<10;i++){
            myResource.PrintC();
        }
    }
}

//    线程A PrintA
//    线程B PrintB
//    线程C PrintC

//    线程A PrintA
//    线程B PrintB
//    线程C PrintC

//    线程A PrintA
//    线程B PrintB
//    线程C PrintC

//    线程A PrintA
//    线程B PrintB
//    线程C PrintC

//    线程A PrintA
//    线程B PrintB
//    线程C PrintC

//    线程A PrintA
//    线程B PrintB
//    线程C PrintC

//    线程A PrintA
//    线程B PrintB
//    线程C PrintC

//    线程A PrintA
//    线程B PrintB
//    线程C PrintC

//    线程A PrintA
//    线程B PrintB