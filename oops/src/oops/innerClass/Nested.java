package oops.innerClass;

class Outer{
	void run() {
		System.out.println("Outer Class Method");
	}
	class Inner{
		void run() {
			System.out.println("Inner Class Method");
		}
	}
}
public class Nested {
	public static void main(String args[]) {
		Outer obj = new Outer();
		obj.run();
		Outer.Inner obj1 = obj.new Inner();
		obj1.run();
	}
}
