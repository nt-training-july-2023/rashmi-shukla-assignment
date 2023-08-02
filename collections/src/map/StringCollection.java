package map;

import java.util.LinkedHashSet;
import java.util.Scanner;

public class StringCollection {
	public static void main(String[] args) {
		LinkedHashSet<String> linkedHashSet = new LinkedHashSet<String>();
		Scanner scanner = new Scanner(System.in);
		for(int i =0; i<20;i++) {
			System.out.print("Enter an element: ");
			String ele = scanner.next();
			linkedHashSet.add(ele);
		}
		
		System.out.println(linkedHashSet);
		
	}
}
