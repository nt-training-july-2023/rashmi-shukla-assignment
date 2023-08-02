package map;

import java.util.HashMap;
import java.util.Scanner;

public class RemoveKeyValue {
	public static void main(String[] args) {
		HashMap<String, String> h1 = new HashMap<String, String>(); 
		h1.put("Rashmi", "001");
		h1.put("Shukla", "002");
		
		Scanner scanner = new Scanner(System.in);
		System.out.println("Enter a key: ");
		String name = scanner.next();
		
		System.out.println("Enter a value: ");
		String val = scanner.next();
		
		System.out.println(h1);
		
		if(h1.get(name).equals(val)) {
			System.out.println("The value is mapped to the key");
			h1.remove(name);
			System.out.println(h1);
		}
		
		else {
			System.out.println("The value is not mapped to the key");
		}
		
		
		
		
	}
}
