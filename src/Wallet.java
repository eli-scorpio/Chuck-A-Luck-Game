import java.util.Scanner;

public class Wallet {
	private int amountOfCash;
	
	public int getAmountOfCash() {
		return amountOfCash;
	}
	
	public void setAmountOfCash() {
		Scanner inputScanner = new Scanner(System.in);
		boolean valid = false;
		
		while(!valid)
		{
			System.out.print("\nEnter the amount of cash you have in your wallet -> ");
			
			if(inputScanner.hasNextInt()) 
			{
				amountOfCash = inputScanner.nextInt();
				
				if(amountOfCash > 0)
					valid = true;
			}
			else
				inputScanner.next();
		}
	}
	
	public void addWinningsToWallet(int winnings){
		amountOfCash += winnings;
	}
	
	public void removeBetFromWallet(int bet){
		amountOfCash -= bet;
	}

}
