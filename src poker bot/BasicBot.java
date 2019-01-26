
public class BasicBot implements Player {
	int curVal;
	boolean hasAce;
	@Override
	public String getPlayerName() {
		return "Team Max";
	}

	@Override
	public boolean hit(int card) {
		curVal += cVal(card);
		if (card/4 == 0) hasAce = true;
		if ((hasAce && curVal > 7)||(!hasAce && curVal >16)) return false;
		return true;
	}

	@Override
	public String dealCards(int dealerCard, int card1, int card2) {
		curVal = cVal(card1) + cVal(card2);
		hasAce = card1/4 == 0 || card2/4 ==0;
		if ((hasAce && curVal > 7)||(!hasAce && curVal >16)) return "s";
		return "h";
	}

	@Override
	public int getBet(int balance) {
		if (balance> 10) return balance/5;
		else return balance;
	}

	@Override
	public void roundEnd(int[] hand, int[] dealer, int won, int cashWon, int newBalance) {
		//could use this for something, i.e. recording data for self learning.
	}

	public int cVal(int c) {
		if (c/4 > 8) return 10;
		else return 1 + c/4;
	}
}
