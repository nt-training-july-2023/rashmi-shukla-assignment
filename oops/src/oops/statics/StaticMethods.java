package oops.statics;

class Laptop{
	String brand;
	int price;
	static String name; 
	
	public void print() {
		System.out.println(brand+": "+price+": "+name);
	}
	public static void print1() {
		System.out.print("In static method of "+name); //can only use static variables
	}
}

public class StaticMethods {
	public static void main(String args[]) {
		Laptop l1 = new Laptop();
		l1.brand="Lenovo";
		l1.price=70000;
		Laptop.name= "Laptop";
		
		Laptop l2 = new Laptop();
		l2.brand="Dell";
		l2.price=150000;
		Laptop.name= "Laptop";
		
		l1.print();
		l2.print();
		
		Laptop.print1();
	}
}
