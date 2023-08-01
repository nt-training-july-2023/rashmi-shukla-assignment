package multithreading;

class Bank{
	static int balance = 1000;
	
	synchronized void withdraw(String name, int amount) {
		if(amount<=balance) {
			balance -= amount;
			System.out.println(name+ " withdrawed: "+amount);
			System.out.println(name+"'s updated balance after withdrawl is: "+balance);
		}
		else {
			System.out.println(name+" Cannot withdraw more than available balance");
		}
	}
	
	synchronized void deposit(String name, int amount) {
		balance += amount;
		System.out.println(name+ " deposited: "+amount);
		System.out.println(name+"'s updated balance after deposit is: "+balance);
	}
}

class WithdrawThread extends Thread{
	
	Bank bank;
	String name;
	int amount;
	
	public WithdrawThread(Bank object, String name, int amount) {
		this.bank = object;
		this.name = name;
		this.amount = amount;
	}
	
	
	@Override
	public void run() {
		bank.withdraw(name, amount);
		
	}
}

class DepositThread extends Thread{
	
	Bank bank;
	String name;
	int amount;
	
	public DepositThread(Bank object, String name, int amount) {
		this.bank = object;
		this.name = name;
		this.amount = amount;
	}
	
	
	@Override
	public void run() {
		bank.deposit(name, amount);
		
	}
}
	
public class ATM {
	public static void main(String[] args) {
		Bank objBank = new Bank();
		
		WithdrawThread t1= new WithdrawThread(objBank,"Rekha", 100);
		DepositThread t2= new DepositThread(objBank,"Rashmi", 1000);
		WithdrawThread t3= new WithdrawThread(objBank,"Hema", 5000);
		WithdrawThread t4= new WithdrawThread(objBank,"Jaya", 200);
		DepositThread t5= new DepositThread(objBank,"Sushma", 100);
		
		t1.start();
		t2.start();
		t3.start();
		t4.start();
		t5.start();
		
		
		
	}
}


