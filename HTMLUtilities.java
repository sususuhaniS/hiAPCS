/**
 *	Utilities for handling HTML
 *
 *	@author	
 *	@since	
 */
public class HTMLUtilities {

	/**
	 *	Break the HTML string into tokens. The array returned is
	 *	exactly the size of the number of tokens in the HTML string.
	 *	Example:	HTML string = "Goodnight moon goodnight stars"
	 *				returns { "Goodnight", "moon", "goodnight", "stars" }
	 *	@param str			the HTML string
	 *	@return				the String array of tokens
	 */
	public String[] tokenizeHTMLString(String str) {
		// make the size of the array large to start
		String[] result = new String[10000];
		for(int i = 0; i < result.length(); i++)
		{
			result[i] = "";
		}
		 
		int charCount = 0;
		int tokenIzer = 0;       
		while(charCount < str.length())
		{
			if(str.charAt(charCount) == '<')
			{
				int idexSt = str.indexOf("<");
				int endSt = str.indexOf(">", idexSt);
				result[tokenIzer] = str.substring(idexSt, endSt +1);
				charCount = endSt++;
				tokenIzer++;
				
			}
			else if(isLetter(str.charAt(charCount)))
			{
				result[tokenIzer] += str.charAt(charCount);
				charCount++;
				/*String word = str.substring(str.indexOf(" ", str.charAt(charCount)),
					str.lastIndexOf(" ", str.charAt(charCount)));
				
				charCount = str.indexOf(" ", str.charAt(charCount));
				tokenIzer++;*/
			}
			else if(isNumber(str.substring(str.indexOf(" ", str.indexOf(charCount)),
				str.lastIndexOf(" ", str.indexOf(charCount)))))
			{
				String num = str.substring(str.indexOf(" ", 
						charCount),
					str.lastIndexOf(" ", str.charAt(charCount)));
				result[tokenIzer] = num;
				charCount = str.indexOf(" ", str.charAt(charCount));
				tokenIzer++;
			}
			else if(isPunctuation(str.charAt(charCount)))
			{
				result[tokenIzer] = " " + str.charAt(charCount);
				tokenIzer++;
			}
			else if(str.charAt(charCount) == ' ')
			{
				charCount++;
				tokenIzer++;
			}
			
		}                                                                                                                                         
		
		
		/*String htmlStart = str.substring(str.indexOf("<"), str.indexOf(">"));
		String htmlFin = str.substring(str.indexOf("</"), str.indexOf(">"));
		
		String htmlBlock = str.substring(htmlStart, htmlFin);
		
		int counterHtml = 0;
		while(counterHtml <= str.length())
		{
			int idSpace = htmlBlock.indexOf(" ");
			String word = str.substring(idSpace, str.indexOf(" ", idSpace);
			boolean hasPuncuation = hasPunctuation(word);
			boolean number = isNumber(word);
			
			if(hasPunctuation);
			{
				if(word.length() == 1)
				{
					//set result at i to punctuation
				}
				else
				{
					for(int i = 0; i < word.length(); i++)
					{
						if(hasPunctuation("" + word.char(i)))
						{
							//set result at i to punctuation
							//set result at i to punctuation for the rest of the word
						}
						
					}
				}
			}
			else if(number)
			{
				//set result at i to number.
			}
		}*/
		
		// return the correctly sized array
		return result;
	}
	
	
	private boolean isLetter(char charIn)
	{
		if((charIn >= 'A' && charIn <= 'Z')||(charIn >= 'a' && charIn <= 'z'))
		{
			return true;
		}
		return false;
		
	}
	
	private boolean isNumber(String numIn)
	{
		
		boolean isNum = false;
		int test = -1; 
		
		try
		{
			test = Integer.parseInt(numIn);
			isNum = true;
		}
		
		catch (NumberFormatException e)
		{
			isNum = false;
		}
		return isNum;
	}
	
	private boolean isPunctuation(char charIn)
	{
		
		if (charIn == ',' || charIn == '.' || charIn == ';' || charIn == ':' 
			|| charIn == '(' || charIn == ')' || charIn == '?' || charIn == '!' 
			|| charIn == '=' || charIn == '&'|| charIn == '~' || charIn == '+') 
		{
			return true;
		}
		
		return false;
	}
	
	/**
	 *	Print the tokens in the array to the screen
	 *	Precondition: All elements in the array are valid String objects.
	 *				(no nulls)
	 *	@param tokens		an array of String tokens
	 */
	public void printTokens(String[] tokens) {
		if (tokens == null) return;
		for (int a = 0; a < tokens.length; a++) {
			if (a % 5 == 0) System.out.print("\n  ");
			System.out.print("[token " + a + "]: " + tokens[a] + " ");
		}
		System.out.println();
	}

}
