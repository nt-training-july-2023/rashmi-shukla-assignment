
package file_IO.copy;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class CopyContent {
	public static void main(String[] args) {
		
		String input = "C:\\Users\\Rashmi Shukla\\eclipse-workspace\\NT Training\\file_IO\\src\\file_IO\\copy\\original.txt";
		String output = "C:\\Users\\Rashmi Shukla\\eclipse-workspace\\NT Training\\file_IO\\src\\file_IO\\copy\\copied.txt";
		
		try {
			FileInputStream iReader = new FileInputStream(input);
			FileOutputStream oWriter = new FileOutputStream(output);
			
			int i ;
			while((i = iReader.read()) !=-1) {
				oWriter.write(i);
			}
			iReader.close();
			oWriter.close();
			System.out.println("File succesfully copied");
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
