package com.demo.lock;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @ClassName ReentranLockTest
 * @Description TODO
 * @Author qiuxinfa
 * @Date 2020/11/2 22:57
 **/
public class ReentrantLockTest {
    public static void main(String[] args) {
        Depot depot = new Depot(5);
        Producer producer = new Producer(depot);
        Consumer consumer = new Consumer(depot);

        Thread p1 = new Thread(producer,"生产者1");

        Thread c1 = new Thread(consumer,"消费者1");

        p1.start();
        c1.start();
    }
}

// 仓库类
class Depot{
    private int index = -1;  // 下标
    private int[] data;  // 容量
    private Lock lock = new ReentrantLock();  // 锁
    private Condition fullCondition = lock.newCondition();
    private Condition emptyCondition = lock.newCondition();

    public Depot(int capacity){
        if (capacity < 1){
            throw new IllegalArgumentException("容量必须大于0");
        }
        this.data = new int[capacity];
    }

    // 生产
    public void produce(){
        // 加锁
        lock.lock();
        try {
            // 超过仓库的最大容量时，停止生产
            while (index >= data.length-1){
                System.out.println("仓库已满： "+Thread.currentThread().getName()+" 停止生产");
                fullCondition.await();
            }
            int val = (int)(Math.random() * 100);
            data[++index] = val;
            System.out.println(Thread.currentThread().getName()+" 生产 "+val+" 完毕！");
            // 通知消费者消费
            emptyCondition.signalAll();
        } catch (InterruptedException e){
            e.printStackTrace();
        } finally {
            // 释放锁
            lock.unlock();
        }

    }

    // 消费
    public void consume(){
        lock.lock();
        try {
            // 没有商品时，停止消费，等待生产
            while (index < 0){
                System.out.println("仓库已空： "+Thread.currentThread().getName()+" 停止消费");
                emptyCondition.await();
            }
            System.out.println(Thread.currentThread().getName()+" 消费 "+data[index--]+" 完毕！");
            // 通知通知生产者生产
            fullCondition.signalAll();
        }catch (InterruptedException e){
            e.printStackTrace();
        }finally {
            lock.unlock();
        }
    }


}


// 生产者
class Producer implements Runnable{
    private Depot depot;

    public Producer (Depot depot){
        this.depot = depot;
    }

    @Override
    public void run() {
        for (int i=0; i<100;i++){
            depot.produce();
        }
    }
}

// 消费者
class Consumer implements Runnable{

    private Depot depot;

    public Consumer (Depot depot){
        this.depot = depot;
    }

    @Override
    public void run() {
        for (int i=0; i<100;i++){
            depot.consume();
        }
    }
}