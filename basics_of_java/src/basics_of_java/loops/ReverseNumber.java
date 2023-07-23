package basics_of_java.loops;

import java.util.Scanner;

public class ReverseNumber {
	public static void main(String args[]) {
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter a number to be reversed: ");
		int n = sc.nextInt();
		
		int i=n,rev = 0;
		
		while(i>0) {
			int rem = i%10;
			rev = rev*10+rem;
			i = i/10;
		}
		
		System.out.println("The reverse of "+n+" is "+rev);
	}
}
