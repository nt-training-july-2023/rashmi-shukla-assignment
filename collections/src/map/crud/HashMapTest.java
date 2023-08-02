package map.crud;

import java.util.*;
public class HashMapTest {
	public static void readHashMap(HashMap<Integer, String> hashMap) {
		for(int key: hashMap.keySet()) {
			System.out.println("Key: "+key+", value: "+hashMap.get(key));
		}
	}
	public static void main(String[] args) {
		///create
		HashMap<Integer, String> hashMap = new HashMap<Integer, String>();
		hashMap.put(1, "one");
		hashMap.put(2, "two");
		hashMap.put(3, "three");
		hashMap.put(4, "four");
		hashMap.put(5, "six");
		
		//read
		readHashMap(hashMap);
		System.out.println();
		
		//update
		System.out.println("Updating value of key 5 to five: ");
		hashMap.put(5, "five");
		readHashMap(hashMap);
		System.out.println();
		
		//delete
		System.out.println("Deleting the element at index 5");
		hashMap.remove(5);
		readHashMap(hashMap);
		
		
	}
}
