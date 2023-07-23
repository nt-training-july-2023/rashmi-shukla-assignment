package strings;

import java.util.Scanner;

public class StringOperations {
	public static void main(String args[]) {
		Scanner sc = new Scanner(System.in);
		System.out.print("Enter a String: ");
		String s = sc.next();
		
		//string length
		System.out.println("The length of String "+s+" = "+s.length());
		
		//string concatenation
		System.out.print("Enter another string: ");
		String s2 = sc.next();
		System.out.println("After concatenation of "+s+" and "+s2+" = "+s.concat(s2));
		
		//get char at given position
		System.out.print("Enter a position to extract char: ");
		int i = sc.nextInt();
		System.out.println("The char at index "+i+" = "+s.charAt(i));
		
		
		//Check if a String starts with a given character or String
		System.out.println("Enter a char or string: ");
		String start = sc.next();
		System.out.println("The String "+s+" starts with "+start+" = "+s.startsWith(start));
		
		//Find index of a given character or string from a String.
		System.out.println("Enter a char or string: ");
		String str = sc.next();
		System.out.println("The String "+str+" lies at index: "+s.indexOf(str));
		
		//Replace a character from a String
		System.out.println("Replace character a from b in string: "+s+" = "+s.replace("a", "b"));
		
		}
}
