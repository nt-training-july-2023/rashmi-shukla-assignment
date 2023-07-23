package arrays;

import java.util.Arrays;
import java.util.Scanner;

public class LargestNumberInArray {
	public static void main(String args[]) {
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter the length of array: ");
		int n = sc.nextInt();
		int arr[] = new int[n];
		for (int i =0; i<n; i++) {
			System.out.print("Enter the "+i+" element:");
			arr[i] = sc.nextInt();
		}
		
		int max = Integer.MIN_VALUE;
		for(int i : arr) {
			if(i>max) max = i;
		}
		
		System.out.println("The largest element of array: "+max);
}
}
