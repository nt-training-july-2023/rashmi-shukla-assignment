package map;

import java.util.HashMap;
import java.util.Scanner;

public class Threshold {
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		System.out.print("Enter the threshold size: ");
		int threshold = scanner.nextInt();
		
		HashMap<Integer, String> h1 = new HashMap<Integer, String>();
		h1.put(1, "A");
		h1.put(2, "B");
		h1.put(3, "C");
		System.out.println("h1: "+h1);
		if(h1.size() >= threshold) {
			h1.clear();
			System.out.println("Cleared h1: "+h1);
		}
		
		HashMap<Integer, String> h2 = new HashMap<Integer, String>();
		h2.put(1, "a");
		h2.put(2, "b");
		System.out.println("h2: "+h2);
		if(h2.size() >= threshold) {
			h2.clear();
			System.out.println(h2);
		}
		
		
	}
}
