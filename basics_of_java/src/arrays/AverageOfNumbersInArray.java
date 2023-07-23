package arrays;

import java.util.Scanner;

public class AverageOfNumbersInArray {
	public static void main(String args[]) {
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter the length of array: ");
		int n = sc.nextInt();
		int arr[] = new int[n];
		for (int i =0; i<n; i++) {
			System.out.print("Enter the "+i+" element:");
			arr[i] = sc.nextInt();
		}
		
		int sum =0;
		for(int i : arr) {
			sum +=i;
		}
		int avg  = sum/arr.length;
		
		System.out.println("The average of elements of array: "+avg);
}
}
