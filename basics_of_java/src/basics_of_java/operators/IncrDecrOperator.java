package basics_of_java.operators;

public class IncrDecrOperator {
	public static void main(String args[]) {
		int i = 4; 
		
		System.out.println("i = "+ i);
		
		//increment 
		System.out.println("i after post-increment: "+ ++i); 
		
		System.out.println("i after pre-increment: "+ i++);
		
		System.out.println("i = "+ i);
		
		//decrement 
		System.out.println("i after post-decrement: "+ --i); 
		
		System.out.println("i after pre-increment: "+ i--);
		
		System.out.println("i = "+ i);
	}
}
