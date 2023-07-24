package oops.inheritance;

class Animal{
	void sleep() {
		System.out.println("sleeping");
	}
	void walk() {
		System.out.println("walking");
	}
	void eat() {
		System.out.println("eating");
	}
}
class Feline extends Animal {
	void eat() {
		System.out.println("Hunt for prey, then eat..");
	}
}
class Lion extends Feline {
	void roar() {
		System.out.println("roars");
	}
}
public class MutliLevel {
	public static void main(String args[]) {
		Lion l= new Lion();
		l.roar();
		l.eat();
		l.walk();
		l.sleep();
	}

}
