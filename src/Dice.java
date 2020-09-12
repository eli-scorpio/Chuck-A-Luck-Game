import java.util.Random;

public class Dice {
	
	private int dieValue;
	
	public int getDieValue() {
		return dieValue;
	}
	public void rollDice() {
		Random generator = new Random();
		dieValue = generator.nextInt(6) + 1;
	}
}
