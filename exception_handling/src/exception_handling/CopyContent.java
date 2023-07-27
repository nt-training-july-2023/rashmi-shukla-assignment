package exception_handling;

import java.io.*;
import java.util.Scanner;  
import java.util.*;  

public class CopyContent {  
  
 public static void copyData(File src, File dest) throws Exception  
  {  
      
     FileInputStream inputStream = new FileInputStream(src);  
     FileOutputStream outputStream = new FileOutputStream(dest);  
       
     try {  
         int i;  
         
         while ((i = inputStream.read()) != -1) {  
             outputStream.write(i);  
         }  
         System.out.println("File Copied"); 
     } catch(Exception e) {  
         System.out.println("Error Found: "+e.getMessage());  
     }  
      
     finally {  
         if (inputStream != null) {  
             inputStream.close();  
         }    
         if (outputStream != null) {  
             outputStream.close();  
         }  
     }  
      
  	}  
  
	 public static void main(String[] args) throws Exception  
	 {  
	  
	 
	     File s = new File("C:\\Users\\Rashmi Shukla\\eclipse-workspace\\NT Training\\exception_handling\\src\\exception_handling\\source.txt");  
  
	     File d = new File("C:\\Users\\Rashmi Shukla\\eclipse-workspace\\NT Training\\exception_handling\\src\\exception_handling\\destination.txt");  
	      

	     copyData(s, d);  
	 }  
}  
