import java.util.Scanner;

public class ChuckALuck {

	public static void main(String[] args) {
		Scanner inputScanner = new Scanner(System.in);
		String betType = "";
		boolean validBetType = false;
		boolean quit = false;
		boolean noMoreMoney = false;
		Wallet wallet = new Wallet();
		wallet.setAmountOfCash();
		
		while(!quit)
		{
			while(!validBetType && !quit && !noMoreMoney) {
				System.out.print("\n                                 ODDS:   30:1      1:1     1:1     1:1     ");
				System.out.println("\nEnter one of four bets or type 'quit': (Triple), (Field), (High), (Low) -> ");
				betType = inputScanner.next();
				
				validBetType = betType.equalsIgnoreCase("Triple")? true:
						betType.equalsIgnoreCase("Field")? true:
						betType.equalsIgnoreCase("High")? true:
						betType.equalsIgnoreCase("Low")? true: false;
				
				quit = betType.equalsIgnoreCase("Quit")? true: false;
			}
			
			
			if(validBetType)
			{
				resolveBet(betType, wallet);
				validBetType = false;
			}
			
			noMoreMoney = (wallet.getAmountOfCash() <= 0)? true: false;
			
			if(noMoreMoney) 
			{
				System.out.println("\nYou ran out of cash! ");
				quit = true;
			}
			
		}
		
		System.out.println("Thank you for playing!");
	}
	
	public static void resolveBet(String betType, Wallet wallet) {
		Dice firstDie = new Dice();
		Dice secondDie = new Dice();
		Dice thirdDie = new Dice();
		String invalidInput = "";
		
		Scanner inputScanner = new Scanner(System.in);
		boolean quit = false;
		boolean validBet = false;
		boolean win = false;
		boolean triple = false;
		boolean highTriple = false;
		boolean lowTriple = false;
		int totalValueOfDice = 0;
		int bet = 0;
		
		while(!validBet && !quit)
		{
			System.out.println("\nAmount in your wallet -> " + wallet.getAmountOfCash() + "\n\nEnter your bet -> ");
			if(inputScanner.hasNextInt())
			{
				bet = inputScanner.nextInt();
				validBet = (bet <= wallet.getAmountOfCash() && bet > 0)? true: false;
			}
			else
				invalidInput = inputScanner.next();
				
			quit = invalidInput.equalsIgnoreCase("quit")? true: false;
		}
		
		if(!quit)
		{
			firstDie.rollDice();
			secondDie.rollDice();
			thirdDie.rollDice();
			
			totalValueOfDice = firstDie.getDieValue() + secondDie.getDieValue() + thirdDie.getDieValue();
			
			triple = (firstDie.getDieValue() == secondDie.getDieValue() && 
					  firstDie.getDieValue() == thirdDie.getDieValue())? true: false;
			
			highTriple = (triple && firstDie.getDieValue() >= 4)? true: false;
			lowTriple = (triple && firstDie.getDieValue() <= 3)? true: false;
			
			if(betType.equalsIgnoreCase("Triple"))
				win = (triple  && firstDie.getDieValue() != 1 && firstDie.getDieValue() != 6)? true: false;
			
			else if(betType.equalsIgnoreCase("Field"))
				win = (totalValueOfDice < 8 || totalValueOfDice > 11)? true: false;
			
			else if(betType.equalsIgnoreCase("High"))
				win = (totalValueOfDice > 10 && !highTriple)? true: false;
			
			else
				win = (totalValueOfDice <= 10 && !lowTriple)? true: false;
			
			System.out.println("\nDie 1 -> " + firstDie.getDieValue() + "\n\nDie 2 -> " + secondDie.getDieValue() + "\n\nDie 3 -> " + thirdDie.getDieValue());
			
			if(win)
			{	
				System.out.println("\nYou won!" + "\n\nPrevious Balance -> " + wallet.getAmountOfCash() 
				                   + "\n\nWinnings -> " + (betType.equalsIgnoreCase("Triple")? bet*30: bet));
				wallet.addWinningsToWallet((betType.equalsIgnoreCase("Triple"))? bet*30: bet);
				
			}
			else
			{
				System.out.println("\nYou lost!" + "\n\nPrevious Balance -> " +  wallet.getAmountOfCash() + "\n\nAmount lost -> " + bet);
				wallet.removeBetFromWallet(bet);
			}
			
			System.out.println("\nNew Balance -> " + wallet.getAmountOfCash());
			
		}
		
		
	}
	
	

}

