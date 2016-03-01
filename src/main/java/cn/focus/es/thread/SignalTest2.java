package cn.focus.es.thread;

import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import org.apache.log4j.Logger;

/**
 * 描述：实现一个队列，保证是线程安全的
 * @author weidongduan
 *
 */
public class SignalTest2 {
	
	private Logger logger = Logger.getLogger(SignalTest2.class);
	
	private static  int MAX = 10;
	
	/**
	 * 存数据
	 */
	private int[] queue  = new int[MAX];
	
	private Lock lock = new ReentrantLock();
	
	private Condition emptyCon = lock.newCondition();
	
	private Condition fullCon = lock.newCondition();
	
	private int count;
	
	/**
	 * 生产
	 */
	private void put(){
		try {
			lock.lock();
			Thread.sleep(1000);
			if(count>=MAX){
				fullCon.await();
			}
			Random r = new Random();
			int num = r.nextInt(10);
			queue[count++]=num;
			emptyCon.signal();
		} catch (Exception e) {
			logger.error(e.getMessage(),e);
		}finally{
			lock.unlock();
		}
	}
	
	/**
	 * 
	 * 消费
	 */
	private int get(){
		try {
			lock.lock();
			Thread.sleep(1000);
			if(count<1){
				//等待
					emptyCon.await();
			}
			int num = queue[--count];
			fullCon.signal();
			return num;
		} catch (InterruptedException e) {
			logger.error(e.getMessage(),e);
			return 0;
		}finally{
			lock.unlock();
		}
	}
	
	public static void main(String[] args) {
		final SignalTest2 s = new SignalTest2();
		ExecutorService es1 = Executors.newFixedThreadPool(5);
		for(int i = 0;i<5;i++){
			
			es1.execute(new Runnable(){
				
				@Override
				public void run() {
					while(true){
						s.put();
					}
				}
				
			});
		}
		
		ExecutorService es2 = Executors.newFixedThreadPool(10);
		for(int i = 0;i<2;i++){
			es2.execute(new Runnable(){
				@Override
				public void run() {
					while(true){
						int num = s.get();
						System.out.println(num);
					}
				}
				
			});
		}
		
	}
}
