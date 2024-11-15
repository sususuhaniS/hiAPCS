/**
 *	Utilities for handling HTML
 *
 *	@author	
 *	@since	
 */
public class HTMLUtilities
{
	
	// NONE = not nested in a block, COMMENT = inside a comment block
	// PREFORMAT = inside a pre-format block
	private enum TokenState { NONE, COMMENT, PREFORMAT };
	// the current tokenizer state
	private TokenState state; 
	
	public HTMLUtilities()
	{
		state = TokenState.NONE;
	}
	
	/**
	 *	Break the HTML string into tokens. The array returned is
	 *	exactly the size of the number of tokens in the HTML string.
	 *	Example:	HTML string = "Goodnight moon goodnight stars"
	 *				returns { "Goodnight", "moon", "goodnight", "stars" }
	 *	@param str			the HTML string
	 *	@return				the String array of tokens
	 */
	public String[] tokenizeHTMLString(String str)
    {
        String[] result = new String[10000];
        int charCount = 0;
        int tokenIndex = 0;

        while (charCount < str.length())
        {
			//if there is no comment or preformat blocks
            if (state == TokenState.NONE)
            {
				//checks for comments
                if (charCount + 4 <= str.length() && str.substring
					(charCount, charCount + 4).equals("<!--"))
                {
                    state = TokenState.COMMENT;
                    int endComment = str.indexOf("-->", charCount);
                    if (endComment != -1)
                    {
                        charCount = endComment + 3;
                        state = TokenState.NONE;
                    }
                    else
                    {
                        charCount = str.length();//terminates loop
                    }
                }
                
                //checks if in pre-format blcok
                else if (charCount + 5 <= str.length() && str.substring
					(charCount, charCount + 5).equals("<pre>"))
                {
                    result[tokenIndex] = "<pre>";
                    tokenIndex++;
                    state = TokenState.PREFORMAT;
                    charCount += 5;
                }
                //checks for html
                else if (str.charAt(charCount) == '<')
                {
                    int endIdx = str.indexOf(">", charCount);
                    if (endIdx != -1)
                    {
                        result[tokenIndex] = str.substring(charCount, endIdx + 1);
                        tokenIndex++;
                        charCount = endIdx + 1;
                    }
                    else
                    {
                        charCount = str.length(); 
                    }
                }
                //checks for words
                else if (isLetterOrHyphen(str.charAt(charCount)))
                {
                    String token = "";
                    while (charCount < str.length() && isLetterOrHyphen
						(str.charAt(charCount)))
                    {
                        token += str.charAt(charCount);
                        charCount++;
                    }
                    result[tokenIndex] = token;
                    tokenIndex++;
                }
                //checks for numbers
                else if (isNumberStart(str.charAt(charCount)))
                {
                    String token = "";
                    if (str.charAt(charCount) == '-')
                    {
                        token += '-';
                        charCount++;
                    }
                    while (charCount < str.length() && isNumber(str.charAt
						(charCount)))
                    {
                        token += str.charAt(charCount);
                        charCount++;
                    }
                    if (charCount < str.length() && str.charAt(charCount) == '.')
                    {
                        token += '.';
                        charCount++;
                        while (charCount < str.length() && isNumber(str.charAt(charCount)))
                        {
                            token += str.charAt(charCount);
                            charCount++;
                        }
                    }
                    if (charCount < str.length() && (str.charAt(charCount) == 'e' 
						|| str.charAt(charCount) == 'E'))
                    {
                        token += 'e';
                        charCount++;
                        if (charCount < str.length() && (str.charAt(charCount) 
							== '-' || str.charAt(charCount) == '+'))
                        {
                            token += str.charAt(charCount);
                            charCount++;
                        }
                        while (charCount < str.length() && 
							isNumber(str.charAt(charCount)))
                        {
                            token += str.charAt(charCount);
                            charCount++;
                        }
                    }
                    result[tokenIndex] = token;
                    tokenIndex++;
                }
                else if (isPunctuation(str.charAt(charCount)))
                {
                    result[tokenIndex] = String.valueOf(str.charAt(charCount));
                    tokenIndex++;
                    charCount++;
                }
                else
                {
                    charCount++;
                }
            }
            //if the state is preformat, save the entire string in token
            else if (state == TokenState.PREFORMAT)
            {
                    result[tokenIndex] = str.substring(charCount);
                    tokenIndex++;
                    charCount = str.length(); 
            }
            //if in comment block, skips the text in comments
            else if (state == TokenState.COMMENT)
            {
                int endComment = str.indexOf("-->", charCount);
                if (endComment != -1)
                {
                    charCount = endComment + 3;
                    state = TokenState.NONE;
                }
                else
                {
                    charCount = str.length(); 
                }
            }
        }
		
		//creates a new array to store the valid entries.
        String[] outputArray = new String[tokenIndex];
        for (int i = 0; i < tokenIndex; i++)
        {
            outputArray[i] = result[i];
        }

        return outputArray;
    }

	/**
	 * checks if the character is a letter or hyphen
	 * @return true if the character is punctuation
	 * @param charIn a specific character in the string
	 */
	private boolean isLetterOrHyphen(char charIn) 
	{
        return (charIn >= 'A' && charIn <= 'Z') || (charIn >= 'a' && charIn <= 'z') || charIn == '-';
    }
	
	/**
	 * checks to see if a character is a number or the positive or negative
	 * or has e in it
	 * @return true if the character is punctuation
	 * @param charIn a specific character in the string
	 */
	private boolean isNumberStart(char charIn) 
	{
        return (charIn >= '0' && charIn <= '9') || charIn == '.' || 
			charIn == '-' || charIn == '+' || charIn == 'e' || charIn == 'E';
    }
	
	/**
	 * checks to see if a character is a number
	 * @return true if the character is punctuation
	 * @param charIn a specific character in the string
	 */
    private boolean isNumber(char charIn)
    {
        return (charIn >= '0' && charIn <= '9');
    }

	/**
	 * checks if the specidfic characters is a punctuation
	 * @return true if the character is punctuation
	 * @param charIn a specific character in the string
	 */
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
