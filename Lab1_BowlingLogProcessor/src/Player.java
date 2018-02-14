
public class Player {
	public String Name;
	public int Score;
	public int Strike;
	public int Spare;
	
	private boolean IsSpare = false;
	private boolean IsStrike = false;
	private int StrikeCount = 0;

	private int ContinousStrike;
	
	//temporary holders for each line
	private int tempScore;
	private int tempStrike;
	private int tempSpare;
	private boolean tempIsSpare = false;
	private boolean tempIsStrike = false;
	private int tempStrikeCount = 0;

	
	
	public boolean ProcessScore(String scoreLine, int lineNumber)
	{
		int localScore = 0;
		
		tempScore = Score;
		tempStrike = Strike;
		tempSpare = Spare;
		tempIsSpare = IsSpare;
		tempIsStrike = IsStrike;
		tempStrikeCount = StrikeCount;
		
		String localScoreLine = scoreLine.replace("[", "").replace("]", "");
		String splitValue[] = StringUtility.GetTokensFromString(localScoreLine, ",");
		
		for(String x:splitValue)
		{
			x = x.trim();
		
			if (x.equals("/"))
			{
				tempSpare += 1;
				tempIsSpare = true;
			}
			else if (x.equals("X"))
			{
				tempStrike +=1;
				if (tempIsStrike)
				{
					tempScore += 30;
				}
				else
				{
					tempIsStrike = true;
					tempStrikeCount = 2;
				}
			}
			else
			{
				int validValue = 0;
				try
				{
					validValue = Integer.parseInt(x);
				}
				catch(Exception ex)
				{
					tempScore = 0;
					System.out.println("Player " + Name + " invalid value " + x + " at line " + lineNumber);
					return false;
				}
				
				if (validValue < 0 || validValue > 9)
				{
					System.out.println("Player " + Name + " invalid value " + validValue + " at line " + lineNumber);
					return false;
				}
				
				if (tempIsSpare == true)
				{
					validValue = 10 + validValue;
					tempIsSpare = false;
				}
				
				if (tempIsStrike == true)
				{
					validValue =  10 + validValue;
					tempStrikeCount -= 1;
					if (tempStrikeCount == 0)
						tempIsStrike = false;
				}
				
				localScore += validValue; 
			}
		}
		
		tempScore += localScore;
		return true;
	}
	
	public void CommitScore()
	{
		Score = tempScore;
		Spare = tempSpare;
		Strike = tempStrike;
		
		StrikeCount = tempStrikeCount;
		
		IsSpare = tempIsSpare;
		IsStrike = tempIsStrike;
	}
}
