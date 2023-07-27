package exception_handling;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class ReadFile {
	public static void main(String args[]) {
		try {
			//read file 
			File file = new File("C:\\Users\\Rashmi Shukla\\eclipse-workspace\\NT Training\\exception_handling\\src\\exception_handling\\input.txt");
			
			BufferedReader bReader = new BufferedReader(new FileReader(file));
			
			String s;
			
			while((s= bReader.readLine()) != null) {
				System.out.println(s);
			}
		}catch (FileNotFoundException ex){
			System.out.println(ex+": File not Found");
		}
		catch (IOException ex){
			System.out.println(ex+": in file I/O");
		}
	}
}
