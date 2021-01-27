import java.util.ArrayList;
import java.util.Random;

public class CardsManager {
	public ArrayList<Card> cards;
	
	//sets the card arrays equal to the given arrays
	public void InitCards(String[] firstSideCards, String[] secondSideCards) {
		cards = new ArrayList<Card>();
		for(int i = 0; i < firstSideCards.length; i++)
			cards.add(new Card(firstSideCards[i], secondSideCards[i]));
	}
	
	public void ShuffleCards() {
		Random rndm = new Random();
		//run the shuffle for the number of cards in the deck, so every card will be shuffled
		//at least once
		for(int i = 0; i < cards.size(); i++) {
			int randPos = rndm.nextInt(cards.size());
			Card temp = cards.get(i);
			
			//swap the cards
			cards.set(i, cards.get(randPos));
			cards.set(randPos, temp);
		}
	}
}
