package oops.statics;

class EarPhones{
	String brand;
	int price;
	static String name; 
	
	//static block
	static{
		name = "EarPhone";
		System.out.println("In static block");
	}
	//constructor 
	public EarPhones() {
		brand = "XYZ";
		price = 2000;
		System.out.println("In constructor");
	}
	public void print() {
		System.out.println(brand+": "+price+": "+name);
	}
	
}
public class StaticBlock {
	public static void main(String args[]) {
		EarPhones e1 = new EarPhones();
		System.out.println(e1.brand);
		System.out.println(e1.price);
		e1.print();
		System.out.println(EarPhones.name);
		
	}
}
