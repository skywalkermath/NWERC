
import java.util.ArrayList;
import java.util.Arrays;

public class GameLogic {

	private Player player;
	private Deck deck;
	private int cash;
	public String botName;

	public static void main(String[] args) {
		//to set up a game where u r playing personally self (useful for testing)
		//(new GameLogic(new Me())).runSet(1000,5,2);
		
		
		//to find the average for the very basic bot i made (BasicBot)
		GameLogic gam = new GameLogic(new BasicBot());
		int startingCash = 1000, numHandsToASetOfGames = 100, numOfSetsForAverage = 1000;
		System.out.println(gam.botName + " scored an average of " + gam.runSet(startingCash, numHandsToASetOfGames, numOfSetsForAverage));
	}
	
	public GameLogic(Player player) {
		this.player = player;
		deck = new Deck();
		botName = player.getPlayerName();
	}

	public int runSet(int cash, int numRounds, int numSamples) {
		int[] scores = new int[numSamples];
		for (int i = 0; i<numSamples; i++) {
			this.cash = cash;
			for (int j = 0; j<numRounds && this.cash > 0;j++) runGame();
			scores[i] = this.cash;
		}
		return Arrays.stream(scores).sum()/numSamples;
	}

	public void runGame() {
		deck.resetDeck();
		int bet = player.getBet(cash);
		if (bet > cash || bet <= 0) {
			System.out.println("CHEATING DETECTED : PLAY FINED 50%!");
			cash = cash/2;
			return;
		}
		ArrayList<Integer> hand = new ArrayList<Integer>();
		ArrayList<Integer> dealer = new ArrayList<Integer>();
		hand.add(deck.getCardNum());
		hand.add(deck.getCardNum());
		dealer.add(deck.getCardNum());
		dealer.add(deck.getCardNum());
		String response = player.dealCards(dealer.get(0), hand.get(0), hand.get(1));
		if (response.equals("h")) {
			hand.add(deck.getCardNum());
			while(getVal(hand) < 21 && player.hit(hand.get(hand.size() -1)))
				hand.add(deck.getCardNum());
		}
		if (response.equals("d")) {
			bet = bet*2;
			if (bet > cash) {
				System.out.println("CHEATING DETECTED : PLAYER LOOSES BET!");
				cash += -bet/2;
				return;
			}
			hand.add(deck.getCardNum());
		}
		while (getVal(dealer) <16) dealer.add(deck.getCardNum());
		if (getVal(hand)>21 || (getVal(dealer) <= 21 && getVal(dealer) > getVal(hand))) {
			cash += -bet;
			player.roundEnd(hand.stream().mapToInt(i -> i).toArray(),dealer.stream().mapToInt(i -> i).toArray(),-1, (int) (bet), cash);
			return;
		}
		if (getVal(hand) == getVal(dealer)) {
			player.roundEnd(hand.stream().mapToInt(i -> i).toArray(),dealer.stream().mapToInt(i -> i).toArray(), 0, 0, cash);
			return;
		}
		if (getVal(hand) == 21) {
			cash += bet*1.5;
			player.roundEnd(hand.stream().mapToInt(i -> i).toArray(),dealer.stream().mapToInt(i -> i).toArray(), 1, (int) (bet*1.5), cash);
			return;
		}
		cash += bet;
		player.roundEnd(hand.stream().mapToInt(i -> i).toArray(),dealer.stream().mapToInt(i -> i).toArray(),1, (int) (bet), cash);
	}

	public int getVal(ArrayList<Integer> cards) {
		int total = 0;
		boolean aces = false;
		for (int c : cards) {
			if (c/4 == 0) aces = true;
			if (c/4 > 9) total += 10;
			else total += 1 + c/4;
		}
		if (aces && total <= 11) total += 10;
		return total;
	}
}
