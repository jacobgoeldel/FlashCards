import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		
		boolean keepLooping = true;
		
		while(keepLooping) {
			
			//output main screen
			System.out.println("Welcome to the Flash Card Program!");
			System.out.println("Created by Jacob Goeldel");
		    System.out.println("");
		    System.out.println("Please Type the number of the option you want:");
			System.out.println("1. Create a Deck");
			System.out.println("2. Study a Deck");
			System.out.println("3. Quit");
			
			//get input for next screen
			char menuInput = WaitForAnswer(new char[] { '1', '2', '3' }, "");
			
			//loop through and run code for the option that they chose
			switch(menuInput) {
				case '1':
					CreateDeck();
					break;
				case '2':
					ReadThroughDeck();
					break;
				case '3':
					keepLooping = false;
					break;
			}
		}
		
	}
	
	public static ArrayList<Card> InitCards(String[] firstSideCards, String[] secondSideCards) {
		ArrayList<Card> cards = new ArrayList<Card>();
		for(int i = 0; i < firstSideCards.length; i++)
			cards.add(new Card(firstSideCards[i], secondSideCards[i]));
		
		return cards;
	}
	
	public static void ShuffleCards(ArrayList<Card> cards) {
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
	
	//repeatedly ask the user for an input until a correct input is given
	//ex: wait until they respond y/n to something before returning answer
	public static char WaitForAnswer(char[] answers, String question) {
		boolean answeredQuestion = false;
		char answer = 'l';
		Scanner ansScnr = new Scanner(System.in);
		while(!answeredQuestion) {
			//get the input
			
			//ask question
			System.out.print(question);
			char input = ansScnr.next().charAt(0);
			
			//make sure it is one of the given valid inputs
			boolean validInput=false;
			for(int i = 0; i < answers.length; i ++) {
				if(answers[i] == input) {
					validInput= true;
				}
			}
			
			//exit loop if the value is valid
			if(validInput) {
				answeredQuestion=true; //stop running the while loop
				answer = input;
			}	
		}
		
		return answer;
	}
	
	//code for the screen to create new decks
	public static void CreateDeck() {
		//init vars
		FileManager fileManager = new FileManager();
		Scanner scnr = new Scanner(System.in);
		String deckName;
		boolean stillAddingCards = true;
		
		//get input for name
		System.out.println("");
		System.out.println("");
		System.out.println("What do you want the name of your new deck to be?");
		
		deckName = scnr.nextLine();
		
		//init listarrays to temp store fronts and backs
		ArrayList<String> frontCards = new ArrayList<String>();
		ArrayList<String> backCards = new ArrayList<String>();
		
		//add as many cards as the user wants
		while(stillAddingCards) {
			System.out.println("Enter the text on the front of the card:");
			frontCards.add(scnr.nextLine());
			
			System.out.println("Enter the back of that card: ");
			backCards.add(scnr.nextLine());
			
			char keepGoingInput = WaitForAnswer(new char[] { 'Y', 'y', 'N', 'n' }, "Do you want to keep adding cards? (Y/N): ");
			if(keepGoingInput == 'N' || keepGoingInput == 'n') {
				stillAddingCards = false;
			}
		}
		
		//save entered cards to a text file
		fileManager.saveCards(frontCards, deckName + "f.txt");
		fileManager.saveCards(backCards, deckName + "b.txt");
		
		System.out.println("Deck Saved Successfully!");
	}
	
	public static void ReadThroughDeck() {
		//init vars
		FileManager fileManager = new FileManager();
		Scanner scnr = new Scanner(System.in);
		
		//ask for the name of the set of cards
		System.out.println("What is the name of the deck you want to load? ('tutorial' for tutorial deck)");
		String deckname = scnr.nextLine();
		
		ArrayList<Card> cards = InitCards(fileManager.loadCards(deckname+"f.txt"), fileManager.loadCards(deckname+"b.txt"));
		
		
		//mix up the card order if the user wants to
		char Mixinput = WaitForAnswer(new char[] {'Y','y','N','n'},"Do you want the cards mixed up? (Y/N)");
		if(Mixinput == 'Y' || Mixinput == 'y') {
			ShuffleCards(cards);
		}
		
		//loop through every card in the deck to display the front and back
		for(int i = 0; i < cards.size(); i++) {
			boolean stayOnCard=true;
			while(stayOnCard) {
				
				cards.get(i).print();
				char cardInput = WaitForAnswer(new char[] {'f', 'F', 'n', 'N' }, "To flip type: F. To go to the next card, type: N.");
				
				if(cardInput == 'n' || cardInput == 'N') {
					//flip to next card
					stayOnCard = false;
				} else { //they want to flip
					cards.get(i).flip();
				}
			}
		}
		
		//end of the deck
		System.out.println("");
		System.out.println("");
		System.out.println("That was the end of the deck. Hopefully you learned something!");
		System.out.println("");
		System.out.println("");
	}
	
		
}
