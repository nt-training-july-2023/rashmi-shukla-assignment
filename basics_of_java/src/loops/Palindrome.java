package loops;

import java.util.Scanner;

public class Palindrome {
	public static void main(String args[]) {
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter a number: ");
		int n = sc.nextInt();
		
		int i = n, rev = 0;
		
		while(i>0) {
			int rem = i%10;
			rev = rev*10+rem;
			i = i/10;
		}
		
		if(rev==n) System.out.println("The number is a Palindrome");
		else System.out.println("The number is not a Palindrome");
}
}
