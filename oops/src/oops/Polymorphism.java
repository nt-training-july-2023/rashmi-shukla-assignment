package oops;

class Calculator{
	public int add(int a, int b) {
		return a+b;
	}
	public float add(float a, float b) { //overload
		return a+b;
	}
	public int subtract(int a, int b) {
		return a-b;
	}
	public float subtract(float a, float b) {
		return a-b;
	}
}
class ScientificCalculator extends Calculator {
	public float subtract(float a, float b) {
		System.out.print("Float Subtraction: "); //override
		return a-b;
	}
	public double exponent(double a, double b) {
		return Math.pow(a, b);
	}
}
public class Polymorphism {
	public static void main(String args[]) {
		Calculator cal = new Calculator();
		ScientificCalculator scal = new ScientificCalculator();
		System.out.println(scal.add(1, 5));
		System.out.println(cal.subtract(4.5f, 3.f));
		System.out.println(scal.subtract(4.5f, 3.f));
		System.out.println(scal.exponent(2, 3));
	}
}

