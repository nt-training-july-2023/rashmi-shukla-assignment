package exception_handling;

import java.lang.invoke.StringConcatFactory;
import java.util.Scanner;

class InvalidPasswordException extends Exception{
	public InvalidPasswordException(String s) {
		super(s);
	}
}

public class Password {
	public static void isValid(String s) throws InvalidPasswordException {
	    String num = ".*[0-9].*";
	    String lower = ".*[a-z].*";
	    String upper = ".*[A-Z].*";
	    
	    if(s.length()<8 ) {
			throw new InvalidPasswordException("The length of password should be atleast 8");
		}
	    
	    if(!(s.matches(num) && (s.matches(lower) || s.matches(upper)))) {
	    	throw new InvalidPasswordException("Invalid password!");
	    }
	}
	public static void main(String[] args) {
		try {
			Scanner scanner = new Scanner(System.in);
			System.out.println("Enter a password having atleast 8 digits, containing both numbers & digits: ");
			String pass = scanner.next();
			
			isValid(pass);
			
			
			System.out.println("New password set Successfully!");
			
		}catch(InvalidPasswordException e) {
			System.out.println("Error: "+e);
		}
	}
}
