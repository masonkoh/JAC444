
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

import java.util.Scanner;
import java.io.IOException;

public class TestProgram_ATM
{
	private static final Scanner in = new Scanner(System.in); // CLOSE IT LATER

	public static void main(String[] args)
	{
		Account[] accounts = new Account[10];
		for (int i = 1; i < 11; i++)
		{
			accounts[i - 1] = new Account(i, 100.0);
		}

		System.out.print("Enter an id (1 - 10): ");
		int id = in.nextInt();

		if (id < 1 || id > 10)
		{
			id = incorrectId(id);
		}

		// default value for user balance is $100
		accounts[id - 1].setBalance(100);

		while (true)
		{
			menuDisplay();
			System.out.print("Enter a choice: ");
			int choice = in.nextInt();

			if (choice == 4)
			{
				System.out.printf("%nEnter an id (1 - 10): ");
				id = in.nextInt();

				if (id < 1 || id > 10)
				{
					id = incorrectId(id);
				}
			}
			performChoice(id, choice, accounts);
		}
	}

	public static int incorrectId(int id)
	{
		// Scanner in = new Scanner(System.in);
		while (id < 1 || id > 10)
		{
			System.out.print("Please enter a valid id: ");
			id = in.nextInt();
			System.out.println();
		}
		return id;
	}

	public static void performChoice(int id, int choice, Account[] accounts)
	{
		// Scanner in = new Scanner(System.in);
		switch (choice)
		{
		case 1:
			System.out.printf("The balance is $%.1f%n", accounts[id - 1].getBalance());
			break;
		case 2:
			System.out.print("Enter the amount to withdraw: ");
			accounts[id - 1].withdraw(in.nextDouble());
			break;
		case 3:
			System.out.print("Enter the amount to deposit: ");
			accounts[id - 1].deposit(in.nextDouble());
			break;
		default:
			break;
		}
	}

	public static void menuDisplay()
	{
		System.out.printf("%nMain menu%n");
		System.out.println("1: check balance");
		System.out.println("2: withdraw");
		System.out.println("3: deposit");
		System.out.println("4: exit");
	}
}