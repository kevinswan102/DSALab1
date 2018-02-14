import java.util.ArrayList;
import java.util.Scanner;

//H:\workspace\Data Structure And Algorithm Lab\Lab1_BowlingLogProcessor\src\LogFiles\BowlingLog.txt
//H:\workspace\Data Structure And Algorithm Lab\Lab1_BowlingLogProcessor\src\LogFiles\AlphabetForNoOfplayers.txt
//H:\workspace\Data Structure And Algorithm Lab\Lab1_BowlingLogProcessor\src\LogFiles\NoOfPlayersIsMoreThanAllowed.txt
//H:\workspace\Data Structure And Algorithm Lab\Lab1_BowlingLogProcessor\src\LogFiles\InvalidValueForPins.txt
//H:\workspace\Data Structure And Algorithm Lab\Lab1_BowlingLogProcessor\src\LogFiles\MissingScoreOfPlayers.txt
//H:\workspace\Data Structure And Algorithm Lab\Lab1_BowlingLogProcessor\src\LogFiles\BowlingLogFullStrike.txt

public class LogReader {

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		
		FileUtility fileUtility = new FileUtility();
		Scanner readFile = fileUtility.RequestForValidFile(scanner);

		if (readFile == null)
			return;
		
		ArrayList<Player> players = new ArrayList<Player>();
			
		boolean isFirstLine = true;
		int noOfPlayers = 0;
		int lineCount = 0;
		while(readFile.hasNextLine()) {
			String line = readFile.nextLine();
			if (isFirstLine)
			{
				try
				{
					noOfPlayers = Integer.parseInt(line);
				}
				catch(Exception ex)
				{
					System.out.println("Number of players entered is incorrect");
					return;
				}
				
				if (noOfPlayers < 1 || noOfPlayers > 6)
				{
					System.out.println("Number of players should be between 1 and 6");
					return;
				}
				
				for(int i=1; i <= noOfPlayers; i++)
				{
					Player newPlayer = new Player();
					newPlayer.Name = Integer.toString(i);
					
					players.add(newPlayer);
				}
				isFirstLine = false;
			}
			else
			{
				lineCount += 1;
				String token[] = StringUtility.GetTokensFromString(line, "]");
				boolean lineError = false;
				
				//we read individual lines and process them to tempLocations
				if (token.length != noOfPlayers)
				{
					System.out.println("Not enough players " + token.length + " on line " + lineCount);
				}
				else
				{
					for(int i=0; i <token.length; i++)
					{
						if (players.get(i).ProcessScore(token[i].trim(), lineCount) == false)
						{
							lineError = true;
						}
					}
				}
				
				if (lineError == false)
				{
					for(int i=0; i <token.length; i++)
					{
						players.get(i).CommitScore();
					}
				}
			}
        }
		
	    
		//Print Players
		System.out.println("------------------------");
		System.out.println("Printing player stats");
		System.out.println("------------------------");
		for(Player x:players)
		{
			System.out.println("------------------------");
			System.out.println("Player number : " + x.Name);
			System.out.println("Score : " + x.Score);
			System.out.println("No. of Strikes : " + x.Strike);
			System.out.println("No. of Spares : " + x.Spare);
			System.out.println("------------------------");
		}
		
	}
}
