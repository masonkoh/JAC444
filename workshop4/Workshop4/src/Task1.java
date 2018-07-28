//JAC444 2018 SUMMER, WORKSHOP 4
//Student name: Youngmin Ko
//Student #: 019155159

// MK747: local file exist. No need to download from url link


import java.io.*;
import java.io.Serializable; // the key element of this lab
import java.util.*;
import java.util.regex.*;

public class Task1 implements Serializable
{

    private static String fileName;
    private static boolean isMale = false;
    private static boolean isFemale = false;
    private static int year;

    public static int getYear()
    {
	return year;
    }

    public static void setYear(int pYear)
    {
	year = pYear;
    }

    public static void setFile(int year)
    {
	try
	{
	    switch (year)
	    {
	    case 2001:
		fileName = "src/babynamesranking2001.txt";
		setYear(2001);
		break;
	    case 2002:
		fileName = "src/babynamesranking2002.txt";
		setYear(2002);
		break;
	    case 2003:
		fileName = "src/babynamesranking2003.txt";
		setYear(2003);
		break;
	    case 2004:
		fileName = "src/babynamesranking2004.txt";
		setYear(2004);
		break;
	    case 2005:
		fileName = "src/babynamesranking2005.txt";
		setYear(2005);
		break;
	    case 2006:
		fileName = "src/babynamesranking2006.txt";
		setYear(2006);
		break;
	    case 2007:
		fileName = "src/babynamesranking2007.txt";
		setYear(2007);
		break;
	    case 2008:
		fileName = "src/babynamesranking2008.txt";
		setYear(2008);
		break;
	    case 2009:
		fileName = "src/babynamesranking2009.txt";
		setYear(2009);
		break;
	    case 2010:
		fileName = "src/babynamesranking2010.txt";
		setYear(2010);
		break;
	    default:
		System.out.println("Invalid year. Please enter a year between 2001-2010.");
		break;
	    }
	} catch (Exception e)
	{
	    e.printStackTrace();
	}
    }

    public static void getGender(char g)
    {
	switch (g)
	{
	case 'M':
	    isMale = true;
	    break;
	case 'F':
	    isFemale = true;
	    break;
	default:
	    System.out.println("Invalid input. Please enter either 'M' or 'F'.");
	    break;
	}
    }

    public static void findName(String name)
    {
	// open file for reading
	try (BufferedReader br = new BufferedReader(new FileReader(fileName)))
	{
	    String line;
	    Boolean matchFound = false;
	    while ((line = br.readLine()) != null && matchFound == false)
	    {
		// split line into variables
		String[] l = line.split("\\s+");

		String rank = l[0];
		String boyName = l[1];
		String girlName = l[3];

		// girls name
		if (isFemale)
		{
		    if (girlName.toUpperCase().equals(name.toUpperCase()))
		    {
			System.out.println("Girl name " + girlName + " is ranked #" + rank + " in year " + getYear());
			matchFound = true;
		    }
		} else if (isMale)
		{
		    if (boyName.toUpperCase().equals(name.toUpperCase()))
		    {
			System.out.println("Boy name " + boyName + " is ranked #" + rank + " in year " + getYear());
			matchFound = true;
		    }
		}
	    }
	    if (matchFound == false)
	    {
		System.out.println("No match was found for this query.");
	    }

	} catch (FileNotFoundException e)
	{
	    e.printStackTrace();
	} catch (IOException e)
	{
	    e.printStackTrace();
	} catch (Exception e)
	{
	    e.printStackTrace();
	}

    }

    /* ---- MAIN PROGRAM --- */
    public static void main(String[] args)
    {
	Scanner scan = new Scanner(System.in);
	int usrInput;
	char gender;
	char keepRunning;
	String nameInput;
	Boolean correctYear = false;
	Boolean validGenderInput = false;
	Boolean validName = false;
	Boolean end = false;
	Boolean validEnd = false;
	Pattern letters = Pattern.compile("\\p{Alpha}+"); // regex to check name input

	while (end == false)
	{

	    // ask for year
	    try
	    {
		correctYear = false;

		do
		{
		    System.out.print("Enter the year: ");
		    if (scan.hasNextInt())
		    {
			usrInput = scan.nextInt();

			if (usrInput >= 2001 && usrInput <= 2010)
			{
			    setFile(usrInput);
			    correctYear = true;
			    break;
			}
		    } else
		    {
			scan.next();
		    }
		    System.out.println("Please enter a number between 2001-2010.");
		    usrInput = 0;
		} while (correctYear == false);

	    } catch (InputMismatchException e)
	    {
		System.out.println("Please enter a number between 2001-2010.");
	    } catch (Exception e)
	    {
		e.printStackTrace();
	    }

	    // ask for gender
	    try
	    {
		validGenderInput = false;
		do
		{
		    System.out.print("Enter the gender: ");
		    gender = scan.next().charAt(0);
		    gender = Character.toUpperCase(gender); // set input to uppercase
		    if (Character.compare(gender, 'F') == 0 || Character.compare(gender, 'M') == 0)
		    {
			getGender(gender);
			scan.nextLine();
			validGenderInput = true;
			break;
		    }
		    System.out.println("Please enter M or F.");
		} while (validGenderInput == false);
	    } catch (Exception e)
	    {
		e.printStackTrace();
	    }

	    // ask for name
	    try
	    {
		validName = false;
		do
		{
		    System.out.print("Enter the name: ");
		    nameInput = scan.nextLine();

		    // check that name input has only letters
		    if (letters.matcher(nameInput).matches())
		    {
			validName = true;
			findName(nameInput);
			break;
		    }

		    System.out.println("Please enter a valid name with only letters.");
		} while (validName == false);
	    } catch (Exception e)
	    {
		e.printStackTrace();
	    }

	    try
	    {
		do
		{
		    System.out.print("Enter another inquiry? ");
		    keepRunning = scan.next().charAt(0);
		    keepRunning = Character.toUpperCase(keepRunning); // set input to uppercase
		    if (Character.compare(keepRunning, 'Y') == 0)
		    {
			validEnd = true;
			break;
		    }
		    if (Character.compare(keepRunning, 'N') == 0)
		    {
			validEnd = true;
			end = true;
			break;
		    }

		    System.out.println("Please enter either Y or N.");
		} while (validEnd == false);
	    } catch (Exception e)
	    {
		e.printStackTrace();
	    }

	}
	System.out.println("END OF PROGRAM. GOOD LUCK WITH YOUR NEW FAMILY!");
    }

}