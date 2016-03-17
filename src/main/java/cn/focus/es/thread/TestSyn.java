package cn.focus.es.thread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.lang.Object;

/**
 * 
 * @author weidongduan
 *测试synchronized会不会发生死锁
 */
public class TestSyn {
	
	public synchronized void test1(){
		System.out.println("test1");
		test2();
	}
	
	public synchronized void test2(){
		System.out.println("test2");
	}
	public static void main(String[] args) {
		final TestSyn t = new TestSyn();
		
		t.test1();
		ExecutorService es = Executors.newFixedThreadPool(10);
		for(int i = 0;i<10;i++){
			es.execute(new Runnable(){

				@Override
				public void run() {
					while(true){
						t.test1();
					}
				}
				
				
			});
		}
	}
}
