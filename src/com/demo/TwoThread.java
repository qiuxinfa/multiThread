package com.demo;

/**
 * @ClassName TwoThread
 * @Description 写两个线程，一个线程打印1~ 52，另一个线程打印A~Z，打印顺序是12A34B…5152Z
 * @Author qiuxinfa
 * @Date 2020/11/1 10:07
 **/
public class TwoThread {
    public static void main(String[] args) {
        MyCommon common = new MyCommon();
        NumberThread numberThread = new NumberThread(common);
        CharThread charThread = new CharThread(common);
        numberThread.start();
        charThread.start();
    }
}

// 共享资源
class MyCommon{
    private volatile boolean flag = false;

    public synchronized void printNUmber(boolean val){
        for(int i=1;i<=52;){
            while (flag != val){
                try {
                    wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            System.out.println(Thread.currentThread().getName()+" print "+(i++)+" "+(i++));
            flag = !flag;
            notifyAll();
        }
    }

    public synchronized void printChar(boolean val){
        for(int i=0;i<26;i++){
            while (flag != val){
                try {
                    wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            System.out.println(Thread.currentThread().getName()+" print "+((char)(65+i)));
            flag = !flag;
            notifyAll();
        }
    }

}

// 打印数字的线程
class NumberThread extends Thread {
    private MyCommon myCommon;
    public NumberThread(MyCommon myCommon){
        super("数字线程 ");
        this.myCommon = myCommon;
    }

    @Override
    public void run() {
        myCommon.printNUmber(false);
    }
}

// 打印字母的线程
class CharThread extends Thread {
    private MyCommon myCommon;
    public CharThread(MyCommon myCommon){
        super("数字线程 ");
        this.myCommon = myCommon;
    }

    @Override
    public void run() {
        myCommon.printChar(true);
    }
}