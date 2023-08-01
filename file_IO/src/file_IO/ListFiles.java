package file_IO;

import java.io.File;

public class ListFiles {
	public static void main(String[] args) {
		File dirPath = new File("C:\\Program Files");
		String arr[] = dirPath.list();
		for(String i: arr) {
			System.out.println(i);
		}
	}
}
