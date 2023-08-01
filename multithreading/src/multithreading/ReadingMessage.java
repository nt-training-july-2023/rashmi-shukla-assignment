package multithreading;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

class Message implements Runnable{
	@Override
	synchronized public void run() {
		
		String file = "C:\\Users\\Rashmi Shukla\\eclipse-workspace\\NT Training\\multithreading\\src\\multithreading\\message.txt";
		BufferedReader bReader;
		try {
			bReader = new BufferedReader(new FileReader(file));
			String s;
			while((s = bReader.readLine())!=null){
				System.out.println(s+" ~reading: "+Thread.currentThread().getName());
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}

public class ReadingMessage {
	public static void main(String args[]) {
		Message m1 = new Message();
		Thread t1 = new Thread(m1);
		t1.setName("Thread 1");
		
		Thread t2= new Thread(m1);
		t2.setName("Thread 2");
		
		Thread t3= new Thread(m1);
		t3.setName("Thread 3");
		
		Thread t4= new Thread(m1);
		t4.setName("Thread 4");
		
		t1.start();
		t2.start();
		t3.start();
		t4.start();
	}
}
