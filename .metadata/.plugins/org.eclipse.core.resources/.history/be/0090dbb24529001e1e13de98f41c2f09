package basics_of_java.arrays;

import java.util.Arrays;
import java.util.Scanner;

public class RotateArray {
	public static void reverse(int[] arr, int start, int end) {
		while(start<end) {
			int temp =arr[start];
			arr[start] = arr[end];
			arr[end] = temp;
			start++;
			end--;
		}
	}
	
	public static void main(String args[]) {
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter the length of array: ");
		int n = sc.nextInt();
		int arr[] = new int[n];
		for (int i =0; i<n; i++) {
			System.out.print("Enter the "+i+" element:");
			arr[i] = sc.nextInt();
		}
		
		//rotate by 2 position
		int k=2;
		
		//reverse the whole array
		reverse(arr, 0, n-1);
		
		//rotate the array from 0-k
		reverse(arr,0,k-1);
		
		//reverse the remaining arr k-n-1
		reverse(arr,k, n-1);
		
		
		
		System.out.println("The array after rotating by 2 positions: "+Arrays.toString(arr));
}
}

