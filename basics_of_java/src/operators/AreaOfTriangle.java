package operators;

import java.util.Scanner;

public class AreaOfTriangle {
	public static void main(String args[]) {
		Scanner sc = new Scanner(System.in);
		System.out.print("Enter the base of triangle: ");
		double b = sc.nextDouble();
		System.out.print("Enter the height of triangle: ");
		double h = sc.nextDouble();
		
		double area = 0.5*b*h;
		
		System.out.println("The area of triangle with base "+b+" and height "+h+" is: "+area);
	}
}
