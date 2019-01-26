import java.util.ArrayList;
import java.util.Random;

public class Deck {
	ArrayList<Integer> myList;
	public Deck() {
		resetDeck();
	}

	public void resetDeck() {
		myList = new ArrayList<Integer>();
		for (int i = 0; i <52; i++) {
			myList.add(i);
		}
	}

	public String convertCardNum(int num) {
		String ans ="";
		switch((int)(num/4)) {
		case 0: ans = "Ace";
		break;
		case 1: ans = "Two";
		break;
		case 2: ans = "Three";
		break;
		case 3: ans = "Four";
		break;
		case 4: ans = "Five";
		break;
		case 5: ans = "Six";
		break;
		case 6: ans = "Seven"; 
		break;
		case 7: ans = "Eight"; 
		break;
		case 8: ans = "Nine"; 
		break;
		case 9: ans = "Ten"; 
		break;
		case 10: ans = "Jack"; 
		break;
		case 11: ans = "Queen";
		break;
		case 12: ans = "King";
		break;
		default: break;
		}
		switch(num%4) {
		case 0: ans = ans + " of Spades";
		break;
		case 1: ans = ans + " of Clubs";
		break;
		case 2: ans = ans + " of Diamonds";
		break;
		case 3: ans = ans + " of Hearts";
		break;
		default: break;
		}
		return ans;
	}

	public int getCardNum() {
		Random rand = new Random();
		int num = Math.abs(rand.nextInt()) % myList.size();
		int ans = myList.get(num);
		myList.remove(num);
		return ans;
	}
}
