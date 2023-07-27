package exception_handling;
import java.util.Scanner;

class NotEvenNumberException extends Exception{
	public NotEvenNumberException(String s) {
		super(s);
	}
}
public class NotAnEvenNumber {
	public static void check(double num) throws NotEvenNumberException {
		if(num%2==0) {
			System.out.println("It is an even number");
		}
		else {
			throw new NotEvenNumberException("The number is odd or non-numeric");
		}
	}
	public static void main(String[] args)throws NotEvenNumberException {
		Scanner scanner = new Scanner(System.in);
		System.out.println("Enter an even number: ");
		double num = scanner.nextDouble();
		check(num);
	}

}
