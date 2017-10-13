package com.ty;

import com.ty.utils.Student;

import java.net.ServerSocket;
import java.util.Objects;
import java.util.concurrent.CountDownLatch;

/**
 * Created by thinkpad on 2017/10/12.
 */

/*
如何让两个线程依次执行？
那如何让 两个线程按照指定方式有序交叉运行呢？
四个线程 A B C D，其中 D 要等到 A B C 全执行完毕后才执行，而且 A B C 是同步运行的
三个运动员各自准备，等到三个人都准备好后，再一起跑
子线程完成某件任务后，把得到的结果回传给主线程
 */
public class TreadTest {

    /*  1.多线程运行start()方法之后 不是看start方法的先后顺序去运行线程的， 线程start之后开始等待cpu的资源，
          哪一个线程先获取到cpu的资源 是不一定的 ，但是可以 设置线程的优先级
        2. 多线程操作同一个对象时， 为了不引起冲突， 在线程中可以对要操作的对象加锁，也就是synchronized 同步关键字
 */





    public static void main(String[] args) throws InterruptedException {
        System.out.println("parent main thread start");
    //   demo1();
        //demo2();
      //  demo3();

        System.out.println("parent main thread stop");
    }

    public static void demo1(){
        Thread A=new Thread(new Runnable() {
            @Override
            public void run() {
                printNumber("A");
            }
        });

        Thread B=new Thread(new Runnable() {
            @Override
            public void run() {
                printNumber("B");
            }
        });
        A.start();
        B.start();

    }

    public static void demo2(){
        Thread A=new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("A开始执行了");
               // printNumber("A");
                System.out.println("A 1");
                System.out.println("A 2");
                System.out.println("A 3");
            }
        });

        Thread B=new Thread(new Runnable() {
            @Override
            public void run() {

                try {
                    //Thread.sleep(5000);
                    System.out.println("B开始等待A");

                    A.join();
                    printNumber("B");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        B.start();
        A.start();

    }

    public static void demo3(){
        Object lock =new Object();
        Thread A=new Thread(new Runnable() {
            @Override
            public void run() {

                synchronized (lock){
                    System.out.println("A 1"+" "+System.currentTimeMillis());
                   /* try {
                        lock.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }*/
                    System.out.println("A 2"+" "+System.currentTimeMillis());
                    System.out.println("A 3"+" "+System.currentTimeMillis());
                }
            }
        });

        Thread B=new Thread(new Runnable() {
            @Override
            public void run() {
                synchronized (lock){
                    System.out.println("B 1"+" "+System.currentTimeMillis());
                    System.out.println("B 2"+" "+System.currentTimeMillis());
                    System.out.println("B 3"+" "+System.currentTimeMillis());
                    lock.notify();
                }
            }
        });
        // A  B先后 start 顺序 会影响结果

        A.start();
        B.start();
    }



    public static void printNumber(String threadName){
        int i=0;
        while(i++ <3){
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(threadName+"print "+i);
        }
    }


}
