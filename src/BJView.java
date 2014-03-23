import javax.swing.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class BJView extends JFrame 
{
	private JLabel betLabel;
	private JLabel betValueLabel;
	private JLabel bankLabel;
	private JLabel bankValueLabel;
	private JLabel cardLabelP1;
	private JLabel cardNameLabelP1;
	private JLabel handValueLabelP1;
	private String inputMessageResult;
	private JLabel cardNameLabelDealer;
	private JLabel cardLabelDealer;
	private JLabel handValueLabelDealer;
	private JLabel player1ScoreLabel;
	private JLabel dealerScoreLabel;
	private JButton exitButton;

	public BJView()
	{
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(new Dimension(500,500));
		JPanel panel1 = new JPanel();
		
		betLabel = new JLabel("Player 1 Current Bet");
		betValueLabel = new JLabel("---");
		bankLabel = new JLabel("Player 1 Bank");
		bankValueLabel = new JLabel("---");
		cardLabelP1 = new JLabel("Player 1 Current Hand");
		cardNameLabelP1 = new JLabel("---");
		handValueLabelP1 = new JLabel("---");
		cardNameLabelDealer = new JLabel("---");
		cardLabelDealer = new JLabel("Dealer Current Hand");
		player1ScoreLabel = new JLabel("Player 1 Hand Score");
		dealerScoreLabel = new JLabel("Dealer Hand Score");
		handValueLabelDealer = new JLabel("---");
		exitButton = new JButton("Exit");
		
		inputMessageResult = null;
		
		panel1.add(cardLabelDealer);
		panel1.add(cardNameLabelDealer);
		panel1.add(dealerScoreLabel);
		panel1.add(handValueLabelDealer);
		panel1.add(bankLabel);
		panel1.add(bankValueLabel);
		panel1.add(betLabel);
		panel1.add(betValueLabel);
		panel1.add(cardLabelP1);
		panel1.add(cardNameLabelP1);
		panel1.add(player1ScoreLabel);
		panel1.add(handValueLabelP1);		
		
		exitButton.addActionListener(new Listener());
		
		this.getContentPane().add(panel1);
	}
	
	
	public void setBet(Integer betAmount)
	{
		betValueLabel.setText(betAmount.toString());
	}
	
	public void setBank(Integer bankAmount)
	{
		bankValueLabel.setText(bankAmount.toString());
	}
	
	public void setCardNameP1(List<String> card)
	{
		String interimCardString = "";
		for(String c : card)
		{
			interimCardString += c + " ";
		}
		cardNameLabelP1.setText(interimCardString);
	}
	
	public void setHandValueP1(Integer totalHandVal)
	{
		handValueLabelP1.setText(totalHandVal.toString());
	}
	
	public void setInitialCardNameDlr(List<String> card)
	{
		String interimCardString = card.get(0);
		cardNameLabelDealer.setText(interimCardString);
	}
	
	public void setInitalHandValueDlr(String initialHandVal)
	{
		handValueLabelDealer.setText(initialHandVal);
	}
	
	public void setCardNameDlr(List<String> card)
	{
		String interimCardString = "";
		for(String c : card)
		{
			interimCardString += c + " ";
		}
		cardNameLabelDealer.setText(interimCardString);
	}
	
	public void setHandValueDlr(Integer totalHandVal)
	{
		handValueLabelDealer.setText(totalHandVal.toString());
	}
	
	public Integer displayPlayMessagePrompt(String message)
	{
		String methodMessage = message;
		Object options[] = {exitButton, "Play"};
		Integer playExitMessage = null;
		if (!(methodMessage == null)){
			playExitMessage = JOptionPane.showOptionDialog(this, message +"\nBegin a new game?", "Play or Exit", 
				JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[1]);
		}
		else
			playExitMessage = JOptionPane.showOptionDialog(this, "Begin a new game?", "Play or Exit", 
					JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[1]);
		return playExitMessage;
	}
	
	public Integer displayHitOrStayMessage()
	{
		Object options[] = {"Stay", "Hit"};
		Integer hitStayResult =  JOptionPane.showOptionDialog(this, "Would you like to hit or stay?", "Hit or Stay", 
				JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[0]);
		if(hitStayResult == -1)
		{
			displayHitOrStayMessage();	
		}
		return hitStayResult;
	}
	
	public Integer displayBetMessage() 
	{ 
		inputMessageResult = null;
		inputMessageResult = JOptionPane.showInputDialog(this, "What is your bet");	
		if(inputMessageResult == null)
		{
			displayBetMessage();
		}
		else
		{
			for(int i = 0; i < inputMessageResult.length(); i++)
			{
				if(!Character.isDigit(inputMessageResult.charAt(i)))
				{
				displayBetMessage();
				}
			}
		}
		return Integer.parseInt(inputMessageResult);
	}
	
	//Listener for Exit Button on Play Game dialog box
	private class Listener implements ActionListener
	{
		public void actionPerformed(ActionEvent arg0) 
		{
			System.exit(0); //shuts down application when exit button is clicked
		}
	}
}