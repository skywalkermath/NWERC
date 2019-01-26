
public interface Player {

	public String getPlayerName();
	public boolean hit(int card);
	public String dealCards(int dealerCard, int card1, int card2);
	public int getBet(int balance);
	public void roundEnd(int[] hand, int[] dealer,int won, int cashWon, int newbalance);
}
