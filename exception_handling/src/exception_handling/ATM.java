package exception_handling;

import java.util.Scanner;

class InvalidInputException extends Exception{
	public InvalidInputException(String s){
		super(s);	
	}
}

public class ATM {
	
	//validate the user input
	static void validate (double bal, double amt) throws InvalidInputException{    
	       if(bal < 0){  
	        // throw an object of user defined exception  
	        throw new InvalidInputException("Negative balance is invalid");   
	       }
	       else if(amt<0){
	    	   throw new InvalidInputException("Negative withdrawal amount is invalid");  
	       }
	       else if(amt>bal) {
	    	   throw new InvalidInputException("Amount more than bank balance cannot be withdrawn"); 
	       }
	}
	public static void main(String args[]) {
		try {
			Scanner sc = new Scanner(System.in);
			System.out.print("Enter your account balance: ");
			double balance = sc.nextDouble();
			System.out.print("Enter the amount you want to withdraw: ");
			double amt = sc.nextDouble();
			validate(balance, amt);
			
			double updatedBalance = balance - amt;
			
			System.out.println("Withdrawl of Rs."+amt+" sucessful. Remaining balance: "+updatedBalance);	
		} catch (InvalidInputException ex) {   
    
            // printing the message from InvalidAgeException object  
            System.out.println("Error: " + ex);  
        }
	}

}
