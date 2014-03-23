import java.util.*;
import java.util.Random;
import java.util.Scanner;	//for testing only

public class Hand {
private String playerName;
private final int MAX=10;
public int score, soft=-1,scoreCalc=0;


//ArrayList<Integer> handArray= new ArrayList<Integer>();
List<Integer> handArray= new ArrayList<Integer>();
List<String> cardNameArray = new ArrayList<String>();

Scanner scan=new Scanner(System.in);

	//constructor
	public Hand (String player){
		playerName=player;
	}
	
	//returns a card, eventually will call method from Dereks cardStack class
	public void deal()
	{
		//handArray.add((int)(Math.random()*MAX)+2);	//for now just calculates an integer from 2-11					
		//System.out.println("enter test card number "+handArray.size()+1+ " for "+playerName);
		//handArray.add(scan.nextInt());
		//if (handArray.get(handArray.size()-1)==11)
		//	soft+=1;
		List<String> methodList = new ArrayList<String>();		
		methodList.addAll(CardStack.deal());		
		
		handArray.add(Integer.parseInt(methodList.get(1)));		
		cardNameArray.add(methodList.get(0));
	}
	
	public void scoreCalc()
	{
		score=0;
		
		for (int num=0;num<handArray.size();num++)
		{	
			if (handArray.get(num)==0){
				handArray.remove(num);	//removes value at the index where last Ace was found
				handArray.add(num,11);	
				
				soft=num;					//feeding the soft variable the index for the last Ace 
											//in the array					
				System.out.println(playerName+"Soft= "+soft);	//just for testing
				System.out.println("Soft="+soft+" Hand: "+handArray.toString());	//just for testing
			}
			
			score+=(int)handArray.get(num);	//add value of handArray to score

			if (score>21&&soft>=0)
			{
				handArray.remove(soft);	//removes value at the index where last Ace was found
				handArray.add(soft,1);	//replaces Ace value of 11 with 1
				soft=-1;					//initializes soft value to -1
				num=-1;					//initializes num to -1 so for loop starts at 0 again
				score=0;					//initializes score to calculate from scratch at beginning of for loop
				System.out.println("Soft 11 was changed to 1");	//just for testing
				System.out.println("Soft="+soft+" Hand: "+handArray.toString());	//just for testing	
			}	//end if score score>21&&soft>=0
		}	//end for loop
	}	//end ScoreCalc Method
	
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
	
	public List<String> getHandName()
	{
		return cardNameArray;
	}
		
	
	public String getDealer()	//this just shows the first card in an array for testing
	{
		return handArray.get(0).toString();
		
	}
	
	public String getPlayerName()
	{
		return playerName;	
	}
	
}
