
public class Card {
	private String front;
	private String back;
	private boolean lookingAtFront;
	
	public Card(String front, String back) {
		this.front = front;
		this.back = back;
		lookingAtFront = true;
	}
	
	public void flip() {
		lookingAtFront = !lookingAtFront;
	}
	
	public String read() {
		if(lookingAtFront)
			return front;
		else
			return back;
	}
}
