
/**********************************************

    Workshop 1
    
    Course:<JAC444> - Semester
    
    Last Name:<Ko>
    
    First Name:<Youngmin>
    
    ID:<019155159>
    
    Section:<SAB>
    
    This assignment represents my own work in accordance with Seneca Academic Policy.
    
	Youngmin Ko
    
    Date:<June 08 2018>

**********************************************/

// import java.io.IOException;

public class SavingsAccount extends Account
{

	protected double OVERDRAFT_LIMIT = 0;

	public SavingsAccount(int id, double balance)
	{
		super(id, balance);
	}

	// is it like inheritance?
	@Override
	public void withdraw(double amount)
	{

		double tempBalance = getBalance();
		if (tempBalance - amount >= OVERDRAFT_LIMIT)
		{
			super.withdraw(amount);
		}
	}

	@Override
	public String toString()
	{
		return "SavingsAccount{" + "Balance=" + getBalance() + '}';
	}
}
