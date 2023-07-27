package exception_handling;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class ReadFile2 {
	public static void main(String[] args)  {
		
		Scanner scanner = new Scanner(System.in);
		System.out.print("Enter the file name: ");
		String file = scanner.nextLine();
//		String file = "C:\\Users\\Rashmi Shukla\\eclipse-workspace\\NT Training\\exception_handling\\src\\exception_handling\\input.txt";
		
		FileReader fReader = null;
		try {
			fReader = new FileReader(file);
			BufferedReader bReader = new BufferedReader(fReader);
			String s;
			
			while((s = bReader.readLine())!=null) {
				
				System.out.println(s);
			}
		}catch(FileNotFoundException e) {
			System.out.println("Error: File not found "+e);
		}
		catch (Exception e) {
			System.out.println("Error: "+e);
		}finally {
			try {
				fReader.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			scanner.close();
		}
		
			
		}
}
