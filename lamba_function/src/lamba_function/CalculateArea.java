package lamba_function;

import java.util.Scanner;

interface Shape{
	void area();
}
public class CalculateArea {
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		Shape square = ()->{
			System.out.println("Enter the side of sqaure: ");
			double s = scanner.nextDouble();
			double res = s*s;
			System.out.println("Area of square = "+res);
		};
		square.area();
		
		Shape circle = ()->{
			System.out.println("Enter the radius of circle: ");
			double r = scanner.nextDouble();
			double res = 2*3.14*r;
			System.out.println("Area of square = "+res);
		};
		circle.area();
		
		Shape  cube = ()->{
			System.out.println("Enter the side of cube: ");
			double a = scanner.nextDouble();
			double res = 6*a;
			System.out.println("Area of cube = "+res);
		};
		cube.area();
				
		Shape sphere = ()->{
			System.out.println("Enter the radius of sphere: ");
			double r = scanner.nextDouble();
			double res = 4*3.14*r*r;
			System.out.println("Area of sphere = "+res);
		};
		sphere.area();
		
		Shape cylinder = ()->{
			System.out.println("Enter the radius of cylinder: ");
			double r = scanner.nextDouble();
			System.out.println("Enter the height of cylinder: ");
			double h = scanner.nextDouble();
			double res = 2*3.14*r*h + 2*3.14*r*r;
			System.out.println("Area of cylinder = "+res);
		};
		cylinder.area();
		
		
	}
}
