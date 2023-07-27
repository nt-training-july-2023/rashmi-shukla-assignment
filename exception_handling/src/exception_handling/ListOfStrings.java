package exception_handling;


import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ListOfStrings {
	public static void main(String args[]) {
		try {
			List<String> list = new ArrayList<String>();
			list.add("ice");
			list.add("snow");
			list.add("cold");
			
			Scanner scanner =  new Scanner(System.in);
			System.out.print("Enter the index of element: ");
			int index = scanner.nextInt();
			
			System.out.println("The element at index "+index+" = "+list.get(index));
			
		}catch (IndexOutOfBoundsException e) {
			System.out.println(e);
		}catch (NullPointerException e) {
			System.out.println(e);
		}
		
	}
}
