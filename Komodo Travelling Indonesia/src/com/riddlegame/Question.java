package com.riddlegame;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.util.LinkedList;

import com.main.BufferedImageLoader;

public class Question {
	
	private String question, choice1, choice2, choice3, choice4;
	private int num, answer, choice; //1-4
	boolean solved = false, answered = false;
	public enum TOF{
		TRUE,
		FALSE,
		NULL
	}
	
	private TOF tof = TOF.NULL;
	private BufferedImage plank, cross, check;
	private BufferedImageLoader loader = new BufferedImageLoader();
	Font font = new Font("tower ruins regular", Font.BOLD, 32);
	Rectangle rectangle = new Rectangle(40, 40, 900, 70);
	
	//buttons
	LinkedList<Button> buttons = new LinkedList<Button>();
	private Button button1;
	private Button button2;
	private Button button3;
	private Button button4;
	
	public Question(String question, String choice1, String choice2, String choice3, String choice4, int answer){
		this.question = question;
		this.choice1 = choice1;
		this.choice2 = choice2;
		this.choice3 = choice3;
		this.choice4 = choice4;
		this.answer = answer;
		
		this.plank = loader.loadImage("/wood_plank.png");
		this.cross = loader.loadImage("/cross.png");
		this.check = loader.loadImage("/check.png");
		
		//buttons
		this.button1 = new Button(choice1, 1);
		this.button2 = new Button(choice2, 2);
		this.button3 = new Button(choice3, 3);
		this.button4 = new Button(choice4, 4);
		buttons.add(button1);
		buttons.add(button2);
		buttons.add(button3);
		buttons.add(button4);
		
	}
	
	public void render(Graphics g) {
		g.drawImage(plank, 40, 40, null);
		g.setColor(Color.white);
		drawStringMid(g, question, rectangle, font);
		if(tof == TOF.TRUE) {
			g.drawImage(check, 870, 40, null);
		}else if(tof == TOF.FALSE) {
			g.drawImage(cross, 870, 40, null);
		}
		for(int i = 0; i < buttons.size(); i++) {
			buttons.get(i).render(g);
		}
	}

	public void drawStringMid(Graphics g, String text, Rectangle rect, Font font) {
		int count = 0;
		for (int i = 0; i < text.length(); i++) {
		    if (text.charAt(i) == '\n') {
		        count++;
		    }
		}
	    FontMetrics metrics = g.getFontMetrics(font);
	    int y = rect.y + ((rect.height - metrics.getHeight()) / 2) + metrics.getAscent() - 20*count;
	    g.setColor(Color.white);
	    g.setFont(font);
	    for (String line : text.split("\n")) {
	    	int x = rect.x + (rect.width - metrics.stringWidth(line)) / 2;
	        g.drawString(line, x, y += g.getFontMetrics().getHeight());
	    }
	}
	
	public TOF getTof() {
		return tof;
	}

	public void setTof(TOF tof) {
		this.tof = tof;
	}
	
	public int getChoice() {
		return choice;
	}

	public void setChoice(int choice) {
		this.choice = choice;
	}

	public boolean isAnswered() {
		return answered;
	}

	public void setAnswered(boolean answered) {
		this.answered = answered;
	}
	
	public String getQuestion() {
		return question;
	}

	public void setQuestion(String question) {
		this.question = question;
	}

	public String getChoice1() {
		return choice1;
	}

	public void setChoice1(String choice1) {
		this.choice1 = choice1;
	}

	public String getChoice2() {
		return choice2;
	}

	public void setChoice2(String choice2) {
		this.choice2 = choice2;
	}

	public String getChoice3() {
		return choice3;
	}

	public void setChoice3(String choice3) {
		this.choice3 = choice3;
	}

	public String getChoice4() {
		return choice4;
	}

	public void setChoice4(String choice4) {
		this.choice4 = choice4;
	}

	public int getNum() {
		return num;
	}

	public void setNum(int num) {
		this.num = num;
	}

	public int getAnswer() {
		return answer;
	}

	public void setAnswer(int answer) {
		this.answer = answer;
	}

	public boolean isSolved() {
		return solved;
	}

	public void setSolved(boolean solved) {
		this.solved = solved;
	}
	
}
