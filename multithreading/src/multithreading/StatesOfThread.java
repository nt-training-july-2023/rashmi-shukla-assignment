package multithreading;
public class StatesOfThread extends Thread
{
	public static void main(String args[])
	{
		StatesOfThread st = new StatesOfThread();
		Thread t = new Thread(st);
		System.out.println("Thread just got created, State : "+t.getState());
		t.start();
		System.out.println("Starting the thread, State : "+t.getState());
		
		try
		{
			Thread.sleep(2000);
		}
		catch(InterruptedException e)
		{
			System.out.println(e);
		}
		System.out.println("State in sleep, State : "+t.getState());
		
		try
		{
			t.join();
		}
		catch(InterruptedException e)
		{
			System.out.println(e);
		}
		System.out.println("State after completion, State: "+t.getState());
	}
	@Override
	public void run()
	{
		System.out.println("Thread in run method");
		try {
			Thread.sleep(20000);
		}
		catch(InterruptedException e)
		{
			System.out.println(e);
		}
	}
}