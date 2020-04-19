package com.ckq.threadpool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.junit.Test;

public class TestnewFixedThreadPool {

	public static void method_01() throws InterruptedException { 
		 
	    ExecutorService executor = Executors.newFixedThreadPool(4); 
	 
	    for (int i = 0; i < 10; i++) { 
	 
	        Thread.sleep(1000); 
	        final int index = i; 
	 
	        executor.execute(() -> { 
	            try { 
	                Thread.sleep(2 * 1000); 
	            } catch (InterruptedException e) { 
	                e.printStackTrace(); 
	            } 
	            System.out.println(Thread.currentThread().getName() + "  " + index); 
	        }); 
	    } 
	    executor.shutdown(); 
	} 
	
	@Test
	public void test_1() throws InterruptedException {
		method_01();
	}
}
