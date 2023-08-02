package map;

import java.util.HashMap;

public class RetrieveKeyValue {
	public static void main(String[] args) {
		HashMap<String, String> h1 = new HashMap<String, String>(); 
		h1.put("Rashmi", "001");
		h1.put("Pranjal", "002");
		
		for(String key: h1.keySet()) {
			System.out.println("Keys of hashmap: "+key);
		}
		
		for(String value: h1.values()) {
			System.out.println("Values of hashMap: "+value);
		}
		
		
	}
}
