package exception_handling;

import java.util.Scanner;

class InvalidDimensionException extends Exception{
	public InvalidDimensionException(String s){
		super(s);	
	}
}


public class AreaOfRectangle {
	public static void main(String args[]) {
		try {
			Scanner sc = new Scanner(System.in);
			System.out.print("Enter the length of rectangle: ");
			double length = sc.nextDouble();
			System.out.print("Enter the width of rectangle: ");
			double width = sc.nextDouble();

			if(length<=0) {
				throw new InvalidDimensionException("Length cannot be less than 1");
			}
			
			if(width<=0) {
				throw new InvalidDimensionException("Width cannot be less than 1");
			}
			
			double area = length*width;
			
			System.out.println("The area of rectangle with length "+length+" and width "+width+" = "+area);	
		} catch (InvalidDimensionException ex) {   
    
            // printing the message from InvalidAgeException object  
            System.out.println("Error: " + ex);  
        }
	}
}
