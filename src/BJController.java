/*NEXT ACTION---->calculate dealer deal after player stands, stand on 16, etc.
 * --->work out soft Ace
 * --->clean up code and output for Derek
 * -->iterate after a hand is done---do you want to continue?
 */



import java.util.Scanner;

public class BJController {
private int bank;	//does this need to be private? 
int bet, status, hit, again;		//status 1=playing 2=win 3=loss 4=push


	public BJController()
	{
		bank=200;	//set the initial bank level that a player receives at the beginning of the game
		Scanner scan=new Scanner(System.in);
		do
		{	
		CardStack cs = new CardStack(); /* This instance creates and initializes the static cardStack hash map that the
		   static deal method operates on */	
		System.out.println("What is your bet?");
		bet=scan.nextInt();
		hit=0;
		
		
		Hand p1=new Hand("Player 1");
		Hand p2=new Hand("Dealer");
		
		
		//Initial Deal===>Could turn in this into a for loop
		p2.deal();
		p1.deal();
		p2.deal();
		p1.deal();
		p1.scoreCalc();
		p2.scoreCalc();
		
//***********************************************************		
		//Check score, calculate wins/losses/hits/stands
		//may make this another class to decentralize code Result Class?
		
		if (p2.getScore()!=21) 	//checks dealer score for blackjack
		{
			if (p1.getScore()!=21)	//checks player score for blackjack if dealer does not have blackjack
			{
				System.out.println (p1.getPlayerName()+"     Score: "+p1.getScore());
				System.out.println(p1.getHandName()+" "+p1.getHand());
				System.out.println(p2.getPlayerName());
				System.out.println(p2.getDealerHandName()+" ["+p2.getDealer()+"]");
				System.out.println("Do you want to hit (1) or stand (0)?");
					hit=scan.nextInt();
					
				while (hit==1)	//player chooses Hit
				{
					p1.deal();
					p1.scoreCalc();
					System.out.println (p1.getPlayerName()+"     Score: "+p1.getScore());
					System.out.println(p1.getHandName()+" "+p1.getHand());
					System.out.println(p2.getPlayerName());
					System.out.println(p2.getDealerHandName()+" ["+p2.getDealer()+"]");
					if (p1.getScore()<=21)	//if player hasnt busted, they can continue to hit
					{
						System.out.println("Do you want to hit (1) or stand (0)?");
						hit=scan.nextInt();	
					}
					else
						hit=0;
				}
				if (p1.getScore()>21)	//player busts
				{
					status=3;
					bank-=bet;
					System.out.println(p1.getScore()+", You bust, and lose: "+bet+" bitcoins.\nBankroll:"+bank);	
					
				}
				else	 //player doesn't bust, player stands, dealer plays, compare and score hands
				{
					//Dealer Plays
					while (p2.getScore()<17)
					{
						p2.deal();
						p2.scoreCalc();
					}
					if (p2.getScore()>21)
					{
						status=1;
						bank+=bet;
						System.out.println("Dealer busts "+p2.getScore());
					}
					else
					{	
					if (p2.getScore()>p1.getScore())	//dealer wins
					{
						status=3;
						bank-=bet;
						System.out.println("Dealer has"+p2.getScore()+", You lose: "+bet+" bitcoins.\nBankroll:"+bank);	
					}
					else		//derek do you want brackets on else statements like this--just calls an if statement
						if (p2.getScore()<p1.getScore())	//player1 wins
						{
							status=2;
							bank+=bet;
							System.out.println("You win: "+bet+" bitcoins.\nBankroll:"+bank);
						}
						else //player2 score=player 1 score
						{
							status=3;
							System.out.println("It's a push!");
						}
					}
				}//end of hand comparison
			}
			else //player has blackjack, dealer doesn't
			{		
				status=2;
				bank+=bet;
				System.out.println("Blackjack, You win: "+bet+" bitcoins.\nBankroll:"+bank);
			}	
		}	
		else		//dealer has blackjack, checks if player has blackjack 
			if (p1.getScore()!=21)	//player does not have blackjack-->dealer wins
			{
				status=3;
				bank-=bet;
				System.out.println("Dealer has 21, You lose: "+bet+" bitcoins.\nBankroll:"+bank);
			}
			else		//dealer has blackjack, and player has blackjack-->push
			{
				status=4;
				System.out.println("Dealer has 21, and so do you! It's a Push!\nBankroll:"+bank);
			}
			
		
		//end ResultClass code
		
		
//***********************************************
//		Printouts for Testing
//***********************************************
		System.out.println (p1.getPlayerName()+"     Score: "+p1.getScore());
		System.out.println(p1.getHandName()+" "+p1.getHand());
		System.out.println (p2.getPlayerName()+"     Score: "+p2.getScore());
		System.out.println(p2.getHand());
		System.out.println(p2.getDealerHandName()+" "+p2.getHand());
		System.out.println();
		if (bank>0)
		{
			System.out.println("Do you want to play again? (1 if yes, 0 if no)?");
			again=scan.nextInt();
			System.out.println("again: "+again);
		}
		else 
		{
			System.out.println("You have exhausted your bankroll, better luck next time and thanks for playing.");
			again=0;
		}

		
		} ///end of Do statement
		while (again==1);
		}	
}
