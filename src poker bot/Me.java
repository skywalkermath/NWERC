import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Me implements Player {
	Deck deck = new Deck();
	BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

	@Override
	public String getPlayerName() {
		try {
			System.out.println("* Please enter team name");
			return reader.readLine();
		} catch (IOException e) {
			e.printStackTrace();
		} 
		return null;
	}

	@Override
	public String dealCards(int dealerCard, int card1, int card2) {
		System.out.println("* Dealer has : " + deck.convertCardNum(dealerCard) + " \n* Your hand : " + deck.convertCardNum(card1) + ",  "+ deck.convertCardNum(card2));
		System.out.println("* Respond d(double down), s(stand) or h(hit)");
		try {
			return reader.readLine();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public boolean hit(int card) {
		System.out.println("* You have been delt a " + deck.convertCardNum(card));
		System.out.println("* Respond h(hit) or s(stand)");
		try {
			return (reader.readLine().equals("h"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public int getBet(int balance) {
		System.out.println("* Enter an integer bet");
		try {
			int bet = Integer.parseInt(reader.readLine());
			if (bet >0) return bet;
		} catch (IOException e) {
			e.printStackTrace();
		}
		return 1;
	}

	@Override
	public void roundEnd(int[] hand, int[] dealer,int won, int cashWon, int newbalance) {
		if(won == 1) System.out.println("* You won " + cashWon + " and now your new balance is " + newbalance);
		else if(won == -1) System.out.println("* You lost " + cashWon + " and now your new balance is " + newbalance);
		else System.out.println("* It was a draw, your balance is still " + newbalance);
		String yourHand = "* Your hand : " + deck.convertCardNum(hand[0]);
		String dealerHand = "* Dealer hand : " +  deck.convertCardNum(dealer[0]);
		for (int i = 1;i<hand.length;i++) yourHand = yourHand + ", " + deck.convertCardNum(hand[i]);
		for (int i = 1;i<dealer.length;i++) dealerHand = dealerHand + ", " + deck.convertCardNum(dealer[i]);
		System.out.println(yourHand + "\n" + dealerHand);
	}
}
