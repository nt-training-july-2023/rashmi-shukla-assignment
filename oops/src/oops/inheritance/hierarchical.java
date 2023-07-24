package oops.inheritance;

//Animal class already in same package

class Tiger extends Animal{
	void roar() {
		System.out.println("roarrr..");
	}
}
class Cat extends Animal{
	void meow() {
		System.out.println("meowww..");
	}
	
}
public class hierarchical {
	public static void main(String args[]) {
		Tiger t = new Tiger();
		t.roar();
		t.walk();
		
		Cat c = new Cat();
		c.meow();
		c.sleep();
	}
}
