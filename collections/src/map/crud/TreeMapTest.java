package map.crud;

import java.util.TreeMap;

public class TreeMapTest {
	public static void main(String[] args) {
		TreeMap<String, Integer> treeMap = new TreeMap<String, Integer>();
		treeMap.put("One", 1);
		treeMap.put("two", 2);
		treeMap.put("three", 4);
		
		//read 
		System.out.println("Natural ordering of keys: "+treeMap);
		
		//update
		treeMap.put("three",3);
		System.out.println("After Updation: "+treeMap);
		
		//delete
		treeMap.remove("two");
		System.out.println("After deletion: "+treeMap);
		
}
}
