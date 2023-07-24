package oops;

import java.util.*;  

public class PropertyDemo {  
	public static void main(String[] args)throws Exception{  
		
		Properties p=new Properties();  
		
		p.setProperty("name", "Rashmi Shukla");
		p.setProperty("email","rashmi.shukla@nucluesteq.com");  
		System.out.println(p);
  
	}  
}  

