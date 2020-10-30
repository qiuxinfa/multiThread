package com.demo;

public class JoinTest extends Thread{

    public static void main(String[] args) throws InterruptedException {
        JoinTest joinTest = new JoinTest();
        System.out.println("主线程开始.....");
        joinTest.start();
        // 表示 子线程 joinTest 执行完毕后，主线程才会继续执行
        joinTest.join();
        System.out.println("主线程结束.....");
    }

    @Override
    public void run() {
        for (int i=0;i<20;i++){
            System.out.println("子线程："+i);
        }
    }

}
