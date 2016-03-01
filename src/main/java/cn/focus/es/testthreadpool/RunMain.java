package cn.focus.es.testthreadpool;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class RunMain {
	
	public static void main(String[] args) {
		ExecutorService es = Executors.newFixedThreadPool(2);
		while(true){
			Future<String> fu = es.submit(new Processor());
			try {
				fu.get();
			} catch (InterruptedException e) {
				e.printStackTrace();
			} catch (ExecutionException e) {
				e.printStackTrace();
			}
		}
	}
}
