package multithreading;

import java.util.Arrays;
import java.util.Scanner;


class Reverse implements Runnable{
	
	int a[];
	public Reverse(int[] arr) {
		a = arr;
	}

	@Override
	public void run() {
		int rev[] = new int[a.length];
		for(int i = 0; i<a.length; i++) {
			rev[i] = a[a.length - i-1];
		}
		System.out.println("Reversed array of numbers = "+Arrays.toString(rev)); 
		
	}
	
}

class Sum implements Runnable{
	int arr[];
	
	public Sum(int a[]) {
		arr = a;
	}

	@Override
	public void run() {
		int sum = 0;
		for(int i:arr) {
			sum+=i;
		}
		System.out.println("Sum of numbers = "+sum);
		
	}
}

class Fibonacci implements Runnable{
	int n;
	public Fibonacci(int num) {
		n = num;
	}

	@Override
	public void run() {
		int fib[] = new int[n];
		fib[0] =0 ;
		fib[1]=1;
		int i = 2;
		while(i<n) {
			fib[i]= fib[i-2]+fib[i-1];
			i++;
		}
		System.out.println("Fibonacci series: "+Arrays.toString(fib));	
	}
}
public class InputNumber {
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		System.out.print("Enter a number: ");
		int num = scanner.nextInt();
		scanner.close();
		int arr[] = new int[num-1];
		for(int i = 0; i<num-1; i++) {
			arr[i]= i+1 ;
		}
		System.out.println(Arrays.toString(arr));
		
		Reverse r1 = new Reverse(arr);
		Sum r2 = new Sum(arr);
		Fibonacci r3 = new Fibonacci(num);
		
		Thread t1 = new Thread(r1);
		Thread t2 = new Thread(r2);
		Thread t3 = new Thread(r3);
		
		t1.setPriority(1);
		t1.start();
		
		t2.setPriority(3);
		t2.start();
		
		
		t3.setPriority(2);
		t3.start();
		
		
	}
}
