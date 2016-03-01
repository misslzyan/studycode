package cn.focus.es.thread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class SignalTest {
	
	private Lock lock = new ReentrantLock();
	
	private Condition con1 = lock.newCondition();
	private Condition con2 = lock.newCondition();
	
	public class ThreadA implements Runnable {

		@Override
		public void run() {
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e1) {
				e1.printStackTrace();
			}
			while(true){
				try {
					lock.lock();
					//获取锁
					System.out.println("a");
					con1.signal();
					con2.await();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}finally{
					lock.unlock();
				}
			}

			
		}
		
	}
	
	
	public class ThreadB implements Runnable {

		@Override
		public void run() {
			while(true){
				try {
					//获取锁
					lock.lock();
					con1.await();
					System.out.println("b");
					con2.signal();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}finally{
					lock.unlock();
				}
			}

			
		}
		
	}
	
	public static void main(String[] args) {
		SignalTest a = new SignalTest();
//		Thread t1 = new Thread(a.new ThreadA());
//		Thread t2 = new Thread(a.new ThreadB());
//		t1.start();
//		t2.start();
		ExecutorService es = Executors.newFixedThreadPool(2);
		es.execute(a.new ThreadA());
		es.execute(a.new ThreadB());
	}
}
