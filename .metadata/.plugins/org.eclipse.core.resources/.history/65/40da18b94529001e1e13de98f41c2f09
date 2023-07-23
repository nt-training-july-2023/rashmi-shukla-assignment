package basics_of_java.loops;

import java.util.Scanner;

public class ArmstrongNumber {
	public static void main(String args[]) {
		
		//sum of cube of each digit equals to number itself
		
		Scanner sc = new Scanner(System.in);
		System.out.print("Enter a number to be checked: ");
		int n = sc.nextInt();
		int i = n,res = 0;
		while(i>0) {
			int rem = i%10;
			System.out.println(rem);
			res+=Math.pow(rem, 3);
			i = i/10;
		}
		if(res==n)System.out.println(n+" is an Armstrong number");
		else System.out.println(n+" is not an Armstrong number");
	}
}
