package exception_handling;

import java.util.Scanner;

public class ArrayOfIntegers {
//	public static void checkException(int[]arr, int i) throws NullPointerException,IndexOutOfBoundsException {
//		if(arr.length==0) {
//			throw new NullPointerException("\nElement cannot be accessed from null list");
//		}
//		else if(i<0 || i>arr.length) {
//			throw new IndexOutOfBoundsException("\n Array index out of bound");
//		}
//	}
	public static void main(String[] args){
		int arr[] = {1,2,3,4,5};
//		int arr[] = {};
		Scanner scanner =  new Scanner(System.in);
		try {
		System.out.print("Enter an index to be accesed: ");
		int i = scanner.nextInt();
		
		if(arr.length==0) {
		throw new NullPointerException("Element cannot be accessed from null list");
		}
		else if(i<0 || i>arr.length) {
			throw new IndexOutOfBoundsException("Array index out of bound");
		}
		
		System.out.println("The element at index "+i+" = "+arr[i]);
//		checkException(arr, i);
		}catch (NullPointerException e) {
			System.out.println("error: "+e);
		}catch (IndexOutOfBoundsException e) {
			System.out.println("error: "+e);
		}
		
		

	}
}
