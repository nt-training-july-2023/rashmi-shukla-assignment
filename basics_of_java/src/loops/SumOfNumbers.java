package loops;

import java.util.Scanner;

public class SumOfNumbers {
	public static void main(String args[]) {
		Scanner sc = new Scanner(System.in);
		System.out.print("Enter the value of N: ");
		int N = sc.nextInt();
		int i = 0, res =0;
		while(i<=N) {
			res+=i;
			i++;
		}
		System.out.println("The sum of first "+N+" numbers= "+res );
		
		
	}
}
