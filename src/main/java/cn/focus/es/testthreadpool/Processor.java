package cn.focus.es.testthreadpool;

import java.util.concurrent.Callable;

public class Processor implements Callable<String> {

	@Override
	public String call() throws Exception {
		System.out.println("aaaaa");
		Thread.sleep(3000);
		return "b";
	}

	

}
