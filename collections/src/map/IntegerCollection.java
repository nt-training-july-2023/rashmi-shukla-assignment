package map;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class IntegerCollection {
	public static void main(String[] args) {
		ArrayList<Integer> arr = new ArrayList<Integer>();
		for(int i =1; i<=100; i+=10) {
			arr.add(i);
		}
		Collections.reverse(arr);
		System.out.println(arr);
		
		for(int i  = 0; i<arr.size();i++) {
			if(arr.get(i)>50) {
				arr.set(i, arr.get(i)+5);	
			}
		}
		System.out.println(arr);
		
		//display if ele>60
		for(int i : arr) {
			System.out.println("Elements greater than 60: ");
			if(i>60) System.out.print(i+" ");
		}
	}
}
