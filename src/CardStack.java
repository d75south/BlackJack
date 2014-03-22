import java.util.*;
import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Multimap;

public class CardStack 
{	
	
	private static Multimap<Integer, Object> CardStack = ArrayListMultimap.create();	
	private int counter;
	private static int stackLength; 
	
	public CardStack()
	{		
		counter = 1;		
		addNumCards("Spades");
		addNumCards("Diamonds");
		addNumCards("Hearts");
		addNumCards("Clubs");
		addOtherCards("Spades");
		addOtherCards("Diamonds");
		addOtherCards("Hearts");
		addOtherCards("Clubs");	
		stackLength = CardStack.size()/2;
				
	}
		
	private void addNumCards(String suite)// utility method for adding numbered cards to hashmap
	{
		/*takes a number for value and a name of suit and in a for loop 
		 * adds counter value as key, and x + of + suite, and x as value to hashmap */		
		Integer x;
		for(x = 2; x <= 10; x++)
		{			
			CardStack.put(counter, x.toString()+" of "+ suite);
			CardStack.put(counter, x);
			counter++;
		}							
	}
	
	
	private void addOtherCards(String suite)// utility method for adding king, queen, jack, ace of suit to hashmap
	{			
			CardStack.put(counter,"Queen of "+ suite);
			CardStack.put(counter, 10);
			counter++;
			CardStack.put(counter,"King of "+ suite);
			CardStack.put(counter, 10);
			counter++;
			CardStack.put(counter,"Jack of "+ suite);
			CardStack.put(counter, 10);
			counter++;
			CardStack.put(counter,"Ace of "+ suite);
			CardStack.put(counter, 11);
			counter++;				
	}
	
	
	private static String getCardName(int cardNum) 	
	{ 
		//retrieves card name based on integer passed in from random number generator
		String result = null;		
		List<Object> arr1;
		arr1 = (List<Object>) CardStack.get(cardNum);
		result = arr1.get(0).toString();					
		return	result;
	}
	
	
	private static String getCardValue(int cardNum) 	
	{ 
		//retrieves card value based on integer passed in from random number generator
		String result = null;		
		List<Object> arr1;
		arr1 = (List<Object>) CardStack.get(cardNum);
		result = arr1.get(1).toString();					
		return	result;
	}
	

	private static int randNumGen(int stackLength) //maxNum == stackLength (see deal method below)
	{
		//generates a random number from 0 to size of hashmap		
		Random rand = new Random();		
		int resultRandNum = rand.nextInt(stackLength)+1; 		
		return resultRandNum;
	}
	
	private static int checkKeyExists(int randNum)
	{							
		/*checks CardStack for the existence of the key specified by randNum
		* if key exists, randNum is returned
		* if key does not exist method is recalled until randNum = key in hashmap*/			
		
		if(CardStack.containsKey(randNum))
		{
			return randNum;
		}		
		else
			return checkKeyExists(randNumGen(stackLength));	//stackLength is a class level private field		
	}
	
	public static ArrayList<String> deal() 	
	{
		/** public method for dealing card and determining associated name and card value
		 *  returns a List<String> with name(0) and value(1) **/
		
		/*calls checkKeyExists on random number generated from 1 to size of hashmap
		 * uses that number to get card value and card name 
		 * and adds them to a list of string called cardResultList */
		
		ArrayList<String> cardResultList = new ArrayList<String>();		
		int newRandom = checkKeyExists(randNumGen(stackLength));
		String cardVal = getCardValue(newRandom);
		String cardName = getCardName(newRandom);
		cardResultList.add(cardName);
		cardResultList.add(cardVal);
		CardStack.removeAll(newRandom);
		stackLength--;
		return cardResultList;
	}
	
}
