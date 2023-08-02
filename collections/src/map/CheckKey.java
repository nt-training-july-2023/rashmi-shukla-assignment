package map;

import java.util.HashMap;
import java.util.Scanner;

public class CheckKey {
	public static void main(String[] args) {
		HashMap<Integer, String> hashMap = new HashMap<Integer, String>();
		hashMap.put(1, "Rashmi");
		hashMap.put(2, "Shukla");
		
		Scanner scanner = new Scanner(System.in);
		System.out.println("Enter a key to be checked: ");
		int key = scanner.nextInt();
		
		if (hashMap.containsKey(key)) {
			System.out.println("Key exists, value:"+hashMap.get(key));
		}
		else {
			System.out.println("The key doesnot exits !");
		}
	}
}
