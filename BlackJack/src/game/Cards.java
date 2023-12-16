package game;

import java.util.*;

import com.entity.Card;
public class Cards {
	static final long serialVersionUID = 1;
	private String[] suits = {"红桃","方块","黑桃","梅花"};
	private String[] names = {"A","2","3","4","5","6","7","8","9","10","J","Q","K"};
	private Vector cards = new Vector();
	

	Cards(){
		init();
	}

	public void init(){
		cards.clear();
		for(int i = 0; i < 4; i++){
			for( int j = 0; j < 13; j++){
				cards.add(new Card(suits[i],names[j],(j>=10) ? 10:(j+1)));
			}
		}
	}
	
	
	public Card deal(){
		Random cardIndexRandom = new Random();
		int cardIndex = cardIndexRandom.nextInt(cards.size()-1);
		Card tempCard = (Card)cards.elementAt(cardIndex);
		cards.remove(cardIndex);
		return tempCard;
	}
	
	public Vector getCards(){
		return cards;
	}
}
