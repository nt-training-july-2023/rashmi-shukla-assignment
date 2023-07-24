package oops.innerClass;

class OuterClass{
	void outer() {
		System.out.println("Inside outer class");
		class InnerClass{
			void inner() {
				System.out.println("Inside inner class");
			}
		}
		
		InnerClass innerClass = new InnerClass();
		innerClass.inner();
	}
}
public class MethodLocal {
	public static void main(String args[]) {
		OuterClass outerClass = new OuterClass();
		outerClass.outer();
		}
}
