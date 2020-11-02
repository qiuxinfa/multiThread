package com.demo;

/**
 * @ClassName CalculateSum
 * @Description TODO
 * @Author qiuxinfa
 * @Date 2020/11/1 11:02
 **/
public class CalculateSum {
    public static void main(String[] args) {
        Thread[] threadList = new Thread[10];
        for (int i=0;i<10;i++){
            threadList[i] = new MySum(10*i+1);
            threadList[i].start();
        }
        for (int i=0;i<10;i++){
            try {
                threadList[i].join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        System.out.println("最终结果："+MySum.sum);
    }
}

class MySum extends Thread{
    private int startNum = 1;
    public static int sum = 0;

    public MySum(int startNum){
        this.startNum = startNum;
    }


    @Override
    public void run() {
        for (int i=0;i<10;i++) {
            sum += (startNum + i);
        }
    }
}