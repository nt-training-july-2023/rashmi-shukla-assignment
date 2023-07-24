package oops.innerClass;

//can also be used with interfaces and abstract class
class A{
	
	void print() {
		System.out.println("In class A");
	}
	void print1() {
	
	}
}
public class Anonymous {
	public static void main(String args[]) {
		A obj1 = new A() {
			void print1(){
				System.out.println("Anonymous class");
			}
		};
		obj1.print();
		obj1.print1();
		
		A obj2 = new A() {
			void print1() {
				System.out.println("Overriden for obj2");
			}
		};
		obj2.print();
		obj2.print1();
		
		
		
		
	}
}
