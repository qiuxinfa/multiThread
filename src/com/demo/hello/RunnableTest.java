package com.demo.hello;

/**
 * @ClassName RunnableTest
 * @Description TODO
 * @Author qiuxinfa
 * @Date 2020/10/31 10:31
 **/
public class RunnableTest {
    public static void main(String[] args) {

        // 三个线程，共用一共Runnable，实现资源的共享，一共卖10张票

        MyRunnable myRunnable = new MyRunnable();

        // 创建线程
        Thread t1 = new Thread(myRunnable);
        Thread t2 = new Thread(myRunnable);
        Thread t3 = new Thread(myRunnable);
        // 启动线程
        t1.start();
        t2.start();
        t3.start();
    }
}

class MyRunnable implements Runnable{
    private volatile int ticket = 10;

    @Override
    public void run() {
        synchronized(this){
            for (int i=0;i<20;i++){
                if (ticket > 0){
                    System.out.println(Thread.currentThread().getName()+" ----> "+ticket--);
                }
            }
        }
    }

}