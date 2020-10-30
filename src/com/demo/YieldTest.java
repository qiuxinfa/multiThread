package com.demo;

// 线程让步测试
public class YieldTest {
    public static void main(String[] args) {
        MyYield a = new MyYield("线程A");
        MyYield b = new MyYield("线程B");
        b.setPriority(a.getPriority()+2);
        a.start();
        b.start();
    }

    static class MyYield extends Thread {
        private static Object obj = new Object();
        public MyYield(String name){
            super(name);
        }

        @Override
        public void run() {
            synchronized (obj){
                for (int i = 0;i<50;i++){
                    System.out.println(Thread.currentThread().getName()+" ---> "+i);
                    if (i % 5 == 0){
                        Thread.yield();
                    }
                }
            }
        }
    }

}
