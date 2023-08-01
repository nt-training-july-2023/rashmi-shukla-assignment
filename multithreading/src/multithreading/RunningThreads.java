package multithreading;

class ThreadClass1 extends Thread{
	public void run() {
		System.out.println("Thread using Thread Class: "+Thread.currentThread().getName());
	}
	
}

class ThreadClass2 extends ThreadClass1{
	public void run() {
		System.out.println("Thread using Thread Class overriden: "+Thread.currentThread().getName());
	}
}

class ThreadClass3 implements Runnable{

	@Override
	public void run() {
		System.out.println("Thread using Runnable Interface: "+Thread.currentThread().getName());
		
	}
	
}

public class RunningThreads {
 public static void main(String[] args) {
	ThreadClass1 t1 = new ThreadClass1();
	t1.setName("Thread 1");
	t1.start();
	
	ThreadClass2 t2 = new ThreadClass2();
	t2.start();
	t2.setName("Thread 2");
	
	ThreadClass3 r = new ThreadClass3();
	Thread t3 = new Thread(r);
	t3.setName("Thread 3");
	t3.start();
	
	System.out.println("In main thread");
	
	
}
}
