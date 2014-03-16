import java.util.*;
import java.util.Random;
import java.util.Scanner;	//for testing only

public class Hand {
private String playerName;
private final int MAX=10;
public int score;

List<Integer> handArray= new ArrayList<Integer>();
List<String> cardNameArray = new ArrayList<String>();

Scanner scan=new Scanner(System.in);

	//constructor
	public Hand (String player){
		playerName=player;
	}
	
	//returns a card
	public void deal()
	{
		List<String> methodList = new ArrayList<String>();		
		methodList.addAll(CardStack.deal());		
		
		handArray.add(Integer.parseInt(methodList.get(1)));		
		cardNameArray.add(methodList.get(0));
		
		//System.out.println("enter test card number "+handArray.size()+1+ " for "+playerName);
		//handArray.add(scan.nextInt());
		} 
	
	public void scoreCalc()
	{
		score=0;
		for (int num=0;num<handArray.size();num++)
			{
			score+=(int)handArray.get(num);
			}
	}
	
	public int getScore()
	{
		return score;
	}
//***********************************************
//				Return Functions for Testing
//***********************************************
	
	public String getHand()
	{
		return handArray.toString();
	}

	
	public String getHandName()
	{
		return cardNameArray.toString();
	}
	
	
	public String getDealer()	//this just shows the first card in an array for testing
	{
		return handArray.get(0).toString();
		
	}
	
	public String getDealerHandName()	//this just shows the first card in an array for testing
	{
		return cardNameArray.get(0).toString();
		
	}
	
	
	public String getPlayerName()
	{
		return playerName;	
	}
	
}
