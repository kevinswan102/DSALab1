
public class StringUtility {

	public static String[] GetTokensFromString(String userInput, String delimeter)
	{
		String token[] = userInput.split(delimeter);
		
		return token;
	}
}
