package com.demo;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * @ClassName CallableTest
 * @Description TODO
 * @Author qiuxinfa
 * @Date 2020/11/1 10:34
 **/
public class CallableTest {
    public static void main(String[] args) throws Exception{
        // 创建线程池
        ExecutorService pool = Executors.newFixedThreadPool(11);
       // 可以执行Runnable对象或者Callable对象代表的线程
        Future<Integer> f1 = pool.submit(new MyCallable(1));
        Future<Integer> f2 = pool.submit(new MyCallable(11));
        Future<Integer> f3 = pool.submit(new MyCallable(21));
        Future<Integer> f4 = pool.submit(new MyCallable(31));
        Future<Integer> f5 = pool.submit(new MyCallable(41));
        Future<Integer> f6 = pool.submit(new MyCallable(51));
        Future<Integer> f7 = pool.submit(new MyCallable(61));
        Future<Integer> f8 = pool.submit(new MyCallable(71));
        Future<Integer> f9 = pool.submit(new MyCallable(81));
        Future<Integer> fa = pool.submit(new MyCallable(91));

        int sum = 0;
        // V get()
        sum += f1.get();
        sum += f2.get();
        sum += f3.get();
        sum += f4.get();
        sum += f5.get();
        sum += f6.get();
        sum += f7.get();
        sum += f8.get();
        sum += f9.get();
        sum += fa.get();

        System.out.println("结果： "+sum);
        // 结束
        pool.shutdown();
    }
}

class MyCallable implements Callable<Integer>{
    private int startNum = 1;

    public MyCallable(int num){
        this.startNum = num;
    }
    @Override
    public Integer call() {
        int sum = 0;
        for (int x = 0; x < 10; x++) {
            sum += (startNum + x);
        }
        System.out.println(Thread.currentThread().getName()+" sum = "+sum);
        return sum;
    }
}