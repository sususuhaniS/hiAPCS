/**
 *	The game of Pig.
 *	(Description here)
 *
 *	@author	
 *	@since	
 */
public class PigGame {
	
	private int totalScore;
	private boolean roll;
	private boolean userTurn;
	private int compTot;
	private Dice dRun;
	
	public PigGame()
	{
		totalScore = 0;
		roll = true;
		userTurn = true;
		compTot = 0;
		dRun = new Dice();
		
	}
	
	public static void main(String[] args)
	{
		PigGame pg = new PigGame();
		pg.runIt();
	}
	
	public void runIt()
	{
		printIntroduction();
		playGame();
	}
	
	public void playGame()
	{
		while(totalScore <= 100 && compTot <= 100)
		{
			if(userTurn)
			{
				userGame();
			}
			else 
			{
				compGame();
			}
		}
		
	}
	
	/**	Print the introduction to the game */
	public void printIntroduction() {
		System.out.println("\n");
		System.out.println("______ _         _____");
		System.out.println("| ___ (_)       |  __ \\");
		System.out.println("| |_/ /_  __ _  | |  \\/ __ _ _ __ ___   ___");
		System.out.println("|  __/| |/ _` | | | __ / _` | '_ ` _ \\ / _ \\");
		System.out.println("| |   | | (_| | | |_\\ \\ (_| | | | | | |  __/");
		System.out.println("\\_|   |_|\\__, |  \\____/\\__,_|_| |_| |_|\\___|");
		System.out.println("          __/ |");
		System.out.println("         |___/");
		System.out.println("\nThe Pig Game is human vs computer. Each takes a"
							+ " turn rolling a die and the first to score");
		System.out.println("100 points wins. A player can either ROLL or "
							+ "HOLD. A turn works this way:");
		System.out.println("\n\tROLL:\t2 through 6: add points to turn total, "
							+ "player's turn continues");
		System.out.println("\t\t1: player loses turn");
		System.out.println("\tHOLD:\tturn total is added to player's score, "
							+ "turn goes to other player");
		System.out.println("\n");
	}
	
	public void userGame()
	{
		int compTurnScore = 0;
		int rollNum = 0;
		String input = "";
		do
		{
			input = Prompt.getString("**** USER Turn ***\n\nYour turn score:"
							+ totalScore + "\nYour total score:" + totalScore + 
							"\n\n (r)oll1 or (h)old");
			System.out.println(input);
			if(input.equals("r"))
			{
				rollNum = dRun.roll();
				dRun.printDice();
				if(rollNum != 1)
				{
					totalScore += rollNum;
					compTurnScore += rollNum;
				}
				else
				{
					userTurn = false;
				}
			}
			else
			{
				userTurn = false;
			}
		}while(userTurn);
		
	}
	
	public void compGame()
	{
		int rollNum = 0;
		int compTurnScore = 0;
		String input = "";
		do
		{
			input = Prompt.getString("**** COMPUTER'S Turn ***\n" + "Computers" +
				" turn score:" + compTurnScore + "\nComputer's total score: "
				+ " Press enter for computer's turn");
			rollNum = dRun.roll();
			rollNum = dRun.roll();
			dRun.printDice();
			if(rollNum != 1)
			{
				compTot += rollNum;
				compTurnScore += rollNum;
				
			}
			else
			{
				userTurn = true;
			}
		}while(rollNum != 1);
	}
}
