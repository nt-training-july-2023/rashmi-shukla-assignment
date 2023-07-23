package loops;

import java.util.Scanner;

public class Pyramid {
	public static void main(String args[]) {
//		1
//		2 2
//		3 3 3
//		4 4 4 4 
//		5 5 5 5 5 
		
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter the value of n: ");
		int n = sc.nextInt();
		
		for(int i =1; i<=n;i++) {
			for (int j=0; j<i;j++ ) {
				System.out.print(i);
			}
			System.out.println();
		}
}
}
