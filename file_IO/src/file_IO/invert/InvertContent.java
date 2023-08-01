package file_IO.invert;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

public class InvertContent {
	public static void main(String[] args) {
		String input = "C:\\Users\\Rashmi Shukla\\eclipse-workspace\\NT Training\\file_IO\\src\\file_IO\\invert\\file.txt";
		String output = "C:\\Users\\Rashmi Shukla\\eclipse-workspace\\NT Training\\file_IO\\src\\file_IO\\invert\\invertedFile.txt";
		ArrayList<String> arrayList = new ArrayList<String>();
		try {
			BufferedReader bReader = new BufferedReader(new FileReader(input));
			String string;
			while((string = bReader.readLine())!=null) {
				arrayList.add(string);
			}
			Collections.reverse(arrayList);
			BufferedWriter bWriter = new BufferedWriter(new FileWriter(output));
			for(String s: arrayList) {
				bWriter.write(s);
				bWriter.newLine();
			}
			bWriter.close();
			bReader.close();
			System.out.println("File Content Inverted and Copied!");
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
}
