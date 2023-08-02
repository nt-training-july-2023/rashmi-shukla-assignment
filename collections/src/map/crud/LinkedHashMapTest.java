package map.crud;

import java.util.LinkedHashMap;

public class LinkedHashMapTest {
	public static void main(String[] args) {
		LinkedHashMap<Integer, String> lHashMap = new LinkedHashMap<Integer, String>();
		lHashMap.put(1, "One");
		lHashMap.put(2, "two");
		lHashMap.put(3, "three");
		
		//read
		System.out.println(lHashMap);
		System.out.println(lHashMap.get(1));
		
		//update
		lHashMap.put(3, "four");
		System.out.println(lHashMap);
		
		//delete
		lHashMap.remove(3);
		System.err.println(lHashMap);
	}
}
