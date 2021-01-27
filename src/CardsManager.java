import java.util.Random;

public class CardsManager {
	private String[] cardFirstSides;
	private String[] cardSecondSides;
	private boolean currentSide = true;
	
	//sets the card arrays equal to the given arrays
	public void InitCards(String[] firstSideCards, String[] secondSideCards) {
		cardFirstSides = firstSideCards;
		cardSecondSides = secondSideCards;
	}
	
	//reads the current side of a card
	public String ReadCard(int cardID) {
		if(currentSide) {
			return cardFirstSides[cardID];
		} else {
			return cardSecondSides[cardID];
		}
	}
	
	public int CardCount() {
		return cardFirstSides.length;
	}
	
	public void flipCard() {
		currentSide = !currentSide;
	}
	
	public boolean GetCurrentSide() {
		return currentSide;
	}
	
	public void ShuffleCards() {
		Random rndm = new Random();
		//run the shuffle for the number of cards in the deck, so every card will be shuffled
		//at least once
		for(int i = 0; i < cardFirstSides.length; i++) {
			//create temp vals of both arrays as to 
			//not mix up which answer is with which question
			int randPos = rndm.nextInt(cardFirstSides.length);
			String tempValF = cardFirstSides[i];
			String tempValS = cardSecondSides[i];
			
			//swap the first card
			cardFirstSides[i] = cardFirstSides[randPos];
			cardSecondSides[i] = cardSecondSides[randPos];
			
			//swap the second with temp
			cardFirstSides[randPos] = tempValF;
			cardSecondSides[randPos] = tempValS;
		}
	}
}
