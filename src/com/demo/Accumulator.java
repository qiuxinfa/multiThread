package com.demo;

/**
 * @ClassName Accumulator
 * @Description TODO
 * @Author qiuxinfa
 * @Date 2020/11/1 11:16
 **/
public class Accumulator extends Thread {
    /*可以直接让主函数所在的类继承Thread类!
     * 可以理解为，对于这道题的解答，直接放在了一个类里。
     *
     * */

    private int startNum;
    public static int sum;
    public Accumulator(int startNum) {
        this.startNum = startNum;
    }
    public static synchronized void add(int num) {		//add()被设置为线程安全的！该方法不能被多个线程同时执行！
        sum += num;
    }
    public void run() {
        int sum = 0;
        for (int i = 0; i < 10; i++) {
            sum += (startNum + i);						//找到规律...
            /* 执行效果：（这是自己不容易想到的。所以在写程序前要思考！寻找每组连加的规律！才能使代码变得简单啊）
             *  第一组：0 + （1+0）+（1+1）+（1+2）+...+(1+9) = 1 + 2 + 3 + ... + 10
             *  第二组：0 + （11+0） +（11+1）+（11+2）+...+(11+9) = 11 + 12 + 13 + ... + 20
             * ...
             * */
        }
        System.out.println(sum);
        add(sum);										//将每组的结果相加
    }
    public static void main(String[] args) {
        Thread[] threadList = new Thread[10];			//创建Thread数组！
        for (int i = 0; i < 10; i++) {					//这个for循环的作用是开启十个线程！
            threadList[i] = new Accumulator(10 * i + 1);//找到技巧...
            /* 这里将分别传入每组的初始数据。
             * */
            threadList[i].start();
        }
        for (int i = 0; i < 10; i++) {
            try {
                threadList[i].join();
                /*
                 * 谁调用join()谁就优先执行。也就是说，threadList[i]线程一直执行自己的run()方法，直至不符合条件使得该线程结束。
                 * 事实上，在此之前每个线程执行到何种地步是不需要关心的。而这里的for循环可以确保每个线程都执行完了。
                 * 那么这个for循环语句的执行顺序是：先i = 0，使得threadList[0]一直执行其run()直至完毕后，再使得i = 1...这样进行下去？？？
                 *
                 * */
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            } 						//
        }
        System.out.println("1 + 2 + 3 + ... + 100 = " + sum);
    }
}