package exception_handling;

import java.io.IOException;

/**
* This is a java program to find area of shapes
* @author  Rashmi Shukla
*/

public class Area {
	
	/**
	* This method is used find the area of rectangle
	* @param len This is the first parameter to areaofRectangle method
	* @param width  This is the second parameter to areaofRectangle method
	* @return double This returns the area of rectangle.
	*/
	static double areaofRectangle(double len, double width) {
		return len*width;
	}
	
	/**
	* This method is used find the area of triangle
	* @param base This is the first parameter to areaOfTriangle method
	* @param height  This is the second parameter to areaOfTriangle method
	* @return double This returns the area of triangle.
	*/
	static double areaOfTriangle(double base, double height) {
		return 0.5*base*height;
	}
	
	/**
	* This method is used find the area of Circle
	* @param radius This is the first parameter to areaOfCircle method
	* @return double This returns the area of circle.
	*/
	static double areaOfCircle(double radius) {
		return 2*3.17*radius;
	}
	
	
	/**
	   * This is the main method which makes use of area method for all shapes.
	   * @param args Unused.
	   * @exception IOException On input error.
	   * @see IOException
	*/
	public static void main(String args[]) throws IOException {
		
		System.out.println("Area of Rectangle with length = 2 and width= 1 is "+ areaofRectangle(2, 1));
		System.out.println("Area of Triangle with height = 4 and base= 2 is "+ areaOfTriangle(2, 1));
		System.out.println("Area of Circle with Radius = 2 is "+ areaOfCircle(2));
		
	}
}
