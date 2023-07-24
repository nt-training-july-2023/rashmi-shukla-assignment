package oops.statics;

class Mobile{
	String brand;
	int price;
	static String name; //common to all the objects
	
	public void print() {
		System.out.println(brand+": "+price+": "+name);
	}
	
}
public class StaticVariable {
	public static void main(String args[]) {
		Mobile mob = new Mobile();
		mob.brand="Samsung";
		mob.price=70000;
		Mobile.name= "SmartPhone";
		
		
		Mobile mob2 = new Mobile();
		mob2.brand="Apple";
		mob2.price=150000;
		Mobile.name= "Phone";
		
		mob.print();
		mob2.print();
		
		
	}
}
