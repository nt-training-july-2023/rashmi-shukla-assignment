package basics_of_java.operators;

public class Bitwise {
	public static void main(String args[]) {
		
		//Unary bitwise complement 
		int a = 3; 
		System.out.println(~a);
		
		
		//left shift
		int b1 = 10; //0000 0000 0000 1010 -> 0000 0000 0010 1000 = 32+8 = 40
		System.out.println(b1<<2);
		int b2 = -10; //1111 1111 1111 0110 -shift> 11 1111 1111 0110 00(leftmost 1 -ve)
		System.out.println(b2<<2); // 2s compl(except sign bit)
		
		
		//signed right shift (preserves sign bit)
		int c = 4; //0000 0000 0000 0100 -shift> 0000 0000 0000 0001
		System.out.println(c>>2);									
		int d = -4; //1111 1111 1111 1100 -shift> 11 1111 1111 1111 11 (-ve no)                                      
		System.out.println(d>>2);
		
		
		//unsigned right shift
		int e = 8; //0000 0000 0000 1000 -shift> 0000 0000 0000 0010
		System.out.println(e>>>2);																	
		int f = -8; //1111 1111 1111 1000 -shift> 00 1111 1111 1111 10
		System.out.println(f>>>2);
		
		
	}
}
