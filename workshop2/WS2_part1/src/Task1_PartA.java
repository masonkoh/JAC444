import java.util.Scanner;

public class Task1_PartA
{

    // static String[] words = { "need", "caffeine", "drink", "java", "seneca",
    // "compile", "android", "apple", "visual",
    // "studio", "eclipse", "code", "hello", "world", "thinkpad", "macbook" };
    static String[] words = { "hello", "macbook", "java" };
    static boolean isCorrect;

    public static void main(String[] args)
    {

	Scanner input = new Scanner(System.in);
	String playStats = "y";
	while (playStats.equals("y"))
	{
	    String word = getWord();
	    String hiddenWord = getHiddenWord(word);
	    int missCount = 0;
	    while (!word.equals(hiddenWord))
	    {

		System.out.print("(Guess) Enter a letter in word " + hiddenWord + " > ");
		char ch = input.next().charAt(0);

		if (!isAlreadyInWord(hiddenWord, ch))
		{

		    hiddenWord = getGuess(word, hiddenWord, ch);

		    if (!isCorrect)
		    {
			System.out.println(ch + " is not in the word.");
			missCount++;
		    }

		} else
		{
		    System.out.println(ch + " is already in word.");
		}

	    }
	    System.out.println("The word is program. You missed " + missCount + "times");
	    System.out.println("Do you want to guess another word? Enter y or n >");
	    playStats = input.next();
	}
	input.close();

    }

    public static String getWord()
    {
	return words[(int) (Math.random() * words.length)];
    }

    public static String getHiddenWord(String word)
    {

	String hidden = "";
	for (int i = 0; i < word.length(); i++)
	{
	    hidden += "*";
	}
	return hidden;
    }

    static public String getGuess(String word, String hiddenWord, char ch)
    {

	isCorrect = false;
	StringBuilder s = new StringBuilder(hiddenWord);
	for (int i = 0; i < word.length(); i++)
	{

	    if (ch == word.charAt(i) && s.charAt(i) == '*')
	    {
		isCorrect = true;
		s = s.deleteCharAt(i);
		s = s.insert(i, ch);
	    }
	}
	return s.toString();
    }

    public static boolean isAlreadyInWord(String hiddenWord, char ch)
    {

	for (int i = 0; i < hiddenWord.length(); i++)
	{

	    if (ch == hiddenWord.charAt(i))
	    {
		return true;
	    }
	}
	return false;
    }
}