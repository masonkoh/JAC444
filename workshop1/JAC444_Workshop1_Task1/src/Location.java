
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

public class Location
{

    public int row;
    public int column;
    public double maxValue;

    public Location(int row, int column, double maxValue)
    {
	this.row = row;
	this.column = column;
	this.maxValue = maxValue;
    }

    public static Location locateLargest(double[][] a)
    {
	/*
	 * The class contains public data fields row, column, and maxValue that store
	 * the maximal value and its indices in a two-dimensional array with row and
	 * column as int types and maxValue as a double type.
	 */
	int row = 0;
	int column = 0;
	double maxValue = a[row][column];

	for (int i = 0; i < a.length; i++)
	{
	    for (int j = 0; j < a[i].length; j++)
	    {
		if (maxValue < a[i][j])
		{
		    maxValue = a[i][j];
		    row = i;
		    column = j;
		}
	    }
	}

	return new Location(row, column, maxValue);
    }

    public static void main(String[] args)
    {

	Scanner input = new Scanner(System.in);
	System.out.print("Enter the number of rows and columns in the array: ");
	int row = input.nextInt();
	int column = input.nextInt();

	if (row < 1 || column < 1)
	{
	    row = incorrectRow(row);
	    column = incorrectColumn(column);
	}

	double[][] m = new double[row][column];

	System.out.println("Enter the array:");
	for (int i = 0; i < m.length; i++)
	{
	    for (int j = 0; j < m[i].length; j++)
	    {
		m[i][j] = input.nextDouble();
	    }
	}

	Location location = Location.locateLargest(m);

	System.out.println("The location of the largest element is " + location.maxValue + " at (" + location.row + ", "
		+ location.column + ")");
	input.close();
    }

    public static int incorrectRow(int row)
    {
	Scanner input = new Scanner(System.in);
	while (row < 1)
	{
	    System.out.print("Please enter a valid row: ");
	    row = input.nextInt();
	    System.out.println();
	}
	input.close();
	return row;
    }

    public static int incorrectColumn(int column)
    {
	Scanner input = new Scanner(System.in);
	while (column < 1)
	{
	    System.out.print("Please enter a valid column: ");
	    column = input.nextInt();
	    System.out.println();
	}
	input.close();
	return column;
    }

}