/*NEXT ACTION---->calculate dealer deal after player stands, stand on 16, etc.
 * --->work out soft Ace
 * --->clean up code and output for Derek
 * -->iterate after a hand is done---do you want to continue?
 */

public class BJController {
private int bank;	
int bet, status, hit, again;		//status 1=playing 2=win 3=loss 4=push
private String message;
private BJView view;

	public BJController(BJView view)
	{
		this.view = view;
		bank=200;	//set the initial bank level that a player receives at the beginning of the game
		playGame();
	}
	public void playGame()
	{
		again=this.view.displayPlayMessagePrompt(null);
		
		while (again==1)
		{	
		CardStack cs = new CardStack(); /* This instance creates and initializes the static cardStack hash map that the
		   static deal method operates on */	
		this.view.setBank(bank);
		bet=this.view.displayBetMessage();
		this.view.setBet(bet);
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
				this.view.setHandValueP1(p1.getScore());
				this.view.setCardNameP1(p1.getHandName());
				this.view.setInitialCardNameDlr(p2.getHandName());
				this.view.setInitalHandValueDlr(p2.getDealer());
				hit=this.view.displayHitOrStayMessage();
					
				while (hit==1)	//player chooses Hit
				{
					p1.deal();
					p1.scoreCalc();
					this.view.setHandValueP1(p1.getScore());
					this.view.setCardNameP1(p1.getHandName());
					System.out.println(p2.getPlayerName());
					if (p1.getScore()<=21)	//if player hasnt busted, they can continue to hit
					{
						hit=this.view.displayHitOrStayMessage();
					}
					else
						hit=0;
				}
				if (p1.getScore()>21)	//player busts
				{
					status=3;
					bank-=bet;
					this.view.setBank(bank);
					message = p1.getScore()+", You bust, and lose: "+bet+" bitcoins.\nBankroll:"+bank;	
					
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
						this.view.setBank(bank);
						message = "Dealer busts "+p2.getScore();
					}
					else
					{	
					if (p2.getScore()>p1.getScore())	//dealer wins
					{
						status=3;
						bank-=bet;
						this.view.setBank(bank);
						message = "Dealer has"+p2.getScore()+", You lose: "+bet+" bitcoins.\nBankroll:"+bank;	
					}
					else
						if (p2.getScore()<p1.getScore())	//player1 wins
						{
							status=2;
							bank+=bet;
							this.view.setBank(bank);
							message = "You win: "+bet+" bitcoins.\nBankroll:"+bank;
						}
						else //player2 score=player 1 score
						{
							status=3;
							this.view.setBank(bank);
							message = "It's a push!";
						}
					}
				}//end of hand comparison
			}
			else //player has blackjack, dealer doesn't
			{		
				status=2;
				bank+=bet;
				this.view.setBank(bank);
				message = "Blackjack, You win: "+bet+" bitcoins.\nBankroll:"+bank;
			}	
		}	
		else		//dealer has blackjack, checks if player has blackjack 
			if (p1.getScore()!=21)	//player does not have blackjack-->dealer wins
			{
				status=3;
				bank-=bet;
				this.view.setBank(bank);
				message = "Dealer has 21, You lose: "+bet+" bitcoins.\nBankroll:"+bank;
			}
			else		//dealer has blackjack, and player has blackjack-->push
			{
				status=4;
				this.view.setBank(bank);
				message = "Dealer has 21, and so do you! It's a Push!\nBankroll:"+bank;
			}
			
		//end ResultClass code

		this.view.setHandValueDlr(p2.getScore());
		this.view.setCardNameDlr(p2.getHandName());
		System.out.println();
		if (bank>0)
		{
			again=this.view.displayPlayMessagePrompt(message);
		}
		else //NEED to add a JDialog box for this message
		{
			System.out.println("You have exhausted your bankroll, better luck next time and thanks for playing.");
			again=0;
		}

		}// end of while
	} //end of game method
}	
