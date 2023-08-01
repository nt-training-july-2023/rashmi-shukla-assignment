package lamba_function;

import java.util.Scanner;

interface Replace{
	void replaceVowel(String s);
}

public class replaceVowels {
	public static void main(String[] args) {
		Scanner scanner  = new Scanner(System.in);
		System.out.println("Enter a String: ");
		String str = scanner.nextLine();
		Replace objReplace  = (String s )->{
			String s1 = "";
	        s1 = s.replaceAll("[aeiou]", "#");
	        System.out.println("String "+s+" after replacing vowels from # = "+s1);
		};
		objReplace.replaceVowel(str);
	}
}
