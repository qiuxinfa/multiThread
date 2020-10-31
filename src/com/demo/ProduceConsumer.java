package com.demo;

/**
 * @ClassName ProduceConsumer
 * @Description TODO
 * @Author qiuxinfa
 * @Date 2020/10/31 19:44
 **/
public class ProduceConsumer {
    public static void main(String[] args) {
        Depot depot = new Depot(10);
        Producer producer = new Producer(depot);
        Consumer consumer = new Consumer(depot);

        Thread p1 = new Thread(producer,"生产者1");
        Thread p2 = new Thread(producer,"生产者2");
        Thread p3 = new Thread(producer,"生产者3");

        Thread c1 = new Thread(consumer,"消费者1");
        Thread c2 = new Thread(consumer,"消费者2");

        p1.start();
        p2.start();
        p3.start();
        c1.start();
        c2.start();
    }
}

class Depot {
    private int[] data;
    private int size;      // 实际容量

    public Depot (int capacity){
        if (capacity < 1){
            throw new IllegalArgumentException("容量不能小于0");
        }
        this.data = new int[capacity];
        this.size = -1;
    }

    // 生产商品
    public synchronized void produce(){
        try {
            // 超过仓库的最大容量时，停止生产
            while (size >= data.length-1){
                System.out.println("仓库已满： "+Thread.currentThread().getName()+" 停止生产");
                wait();
            }
            int val = (int)(Math.random() * 100);
            data[++size] = val;
            System.out.println(Thread.currentThread().getName()+" 生产 "+val+" 完毕！");
            // 通知消费者消费
            notifyAll();
        }catch (InterruptedException e){
            e.printStackTrace();
        }
    }

    // 消费商品
    public synchronized void consume(){
        try {
            // 没有商品时，停止消费，等待生产
            while (size < 0){
                System.out.println("仓库已空： "+Thread.currentThread().getName()+" 停止消费");
                wait();
            }
            System.out.println(Thread.currentThread().getName()+" 消费 "+data[size--]+" 完毕！");
            // 通知通知生产者生产
            notifyAll();
        }catch (InterruptedException e){
            e.printStackTrace();
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