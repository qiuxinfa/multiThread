package com.demo.hello;

/**
 * @ClassName MyThread
 * @Description TODO
 * @Author qiuxinfa
 * @Date 2020/10/31 10:24
 **/
public class ThreadTest{
    public static void main(String[] args) {
        // 三个线程，各卖10张票

        // 创建线程
        MyThread t1 = new MyThread();
        MyThread t2 = new MyThread();
        MyThread t3 = new MyThread();
        // 启动线程
        t1.start();
        t2.start();
        t3.start();
    }
}


class MyThread extends Thread {
    private int ticket = 10;

    @Override
    public void run() {
        for (int i=0;i<20;i++){
            if (ticket > 0){
                System.out.println(Thread.currentThread().getName()+" ----> "+ticket--);
            }

        }
    }
}