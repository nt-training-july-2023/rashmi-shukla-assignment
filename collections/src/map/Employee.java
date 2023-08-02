package map;

import java.util.HashMap;
import java.util.Map.Entry;
import java.util.Scanner;

public class Employee {
	static HashMap<Integer, String> map = new HashMap<Integer, String>();
	static int i =1;
	public static void addElement(String name){
		map.put(i, name);
		i+=1;
	}
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		System.out.print("Enter the number of employees: ");
		int n = sc.nextInt();
		for(int i=0; i<n;i++) {
			System.out.print("Enter the name of the employee: ");
			String name = sc.next();
			addElement(name);
		}
		
		for(Entry<Integer, String> entry: map.entrySet()) {
			System.out.println("Emp-id= "+entry.getKey()+", Emp-name= "+entry.getValue());
		}
		
		
	}
}
