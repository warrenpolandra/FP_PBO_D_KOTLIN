package com.main;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Random;

import com.memorygame.CardStatus;
import com.quiz.Quiz.CHECK;
import com.riddlegame.Question.TOF;

public class MouseInput extends MouseAdapter implements MouseListener{
	
	private Handler handler;
	private Game game;
	
	public MouseInput(Handler handler, Game game) {
		this.handler = handler;
		this.game = game;
	}
	
	public void mousePressed(MouseEvent e) {
		
		int mx = e.getX();
		int my = e.getY();
		
		if(Game.state == Game.STATE.MENU) {
			
			//Play Button
			if(mx >= Game.window_width / 2 - 100 && mx <= Game.window_width / 2 + 100) {
				if(my >= 280 && my <= 355) {
					//pressed Play Button
					Game.state = Game.STATE.MAP;
				}
			}
			
			//Quit Button
			if(mx >= Game.window_width - 125 && mx <= Game.window_width - 35) {
				if(my >= 400 && my <= 490) {
					System.exit(1);
				}
			}
			
		} else if(Game.state == Game.STATE.START) {
			Game.state = Game.STATE.MENU;
		} else if(Game.state == Game.STATE.MAP) {
	
			//Sumatera
			if(mx >= 140 && mx <= 180) {
				if(my >= 240 && my <= 280) {
					game.curLoc = game.sumatera;
					Game.curMinGame = Game.CurMinGame.RIDDLE;
					Game.state = Game.STATE.MINIGAME;
				}
			}
			
			//Jawa
			if(mx >= 320 && mx <= 360) {
				if(my >= 380 && my <= 420) {
					if(game.jawa.isUnlocked()) {
						game.curLoc = game.jawa;
						Game.curMinGame = Game.CurMinGame.MEMORY;
						Game.state = Game.STATE.MINIGAME;
					}
				}
			}
			
			//Kalimantan
			if(mx >= 390 && mx <= 430) {
				if(my >= 250 && my <= 290) {
					if(game.kalimantan.isUnlocked()) {
						game.curLoc = game.kalimantan;
						Game.curMinGame = Game.CurMinGame.QUIZ;
						Game.state = Game.STATE.MINIGAME;
					}
				}
			}
			
			//Sulawesi
			if(mx >= 525 && mx <= 565) {
				if(my >= 260 && my <= 300) {
					if(game.sulawesi.isUnlocked()) {
						game.curLoc = game.sulawesi;
						Game.curMinGame = Game.CurMinGame.RPS;
						Game.state = Game.STATE.MINIGAME;
					}
				}
			}
			
			//Papua
			if(mx >= 880 && mx <= 920) {
				if(my >= 300 && my <= 340) {
					if(game.papua.isUnlocked()) {
						game.curLoc = game.papua;
						Game.state = Game.STATE.MINIGAME;
						Game.curMinGame = Game.CurMinGame.MAZE;
					}
				}
			}
			
			
		} else if(Game.state == Game.STATE.MINIGAME) {
			if(Game.curMinGame == Game.CurMinGame.MEMORY) {
				for(int i = 0; i < handler.card.size(); i++) {
					if(handler.card.get(i).getStatus() != CardStatus.SOLVED) {
						if(mx >= handler.card.get(i).getxDraw() && mx <= handler.card.get(i).getxDraw() + handler.card.get(i).width) {
							if(my >= handler.card.get(i).getyDraw() && my <= handler.card.get(i).getyDraw() + handler.card.get(i).height) {
								handler.card.get(i).setStatus(CardStatus.OPENED);
							}
						}
					}
				}
			}else if(Game.curMinGame == Game.CurMinGame.MAZE) {
				//do nothing
			}else if(Game.curMinGame == Game.CurMinGame.RPS) {
				
				//rock button
				if(mx >= Game.window_width/2 + 110 && mx <= Game.window_width/2 + 110 + 120) {
					if(my >= 320 && my <= 455) {
						for(int i = 0; i < handler.rpsCard.size(); i++) {
							if(handler.rpsCard.get(i).getType() != 0 && handler.rpsCard.get(i).isPlayerCard() == true) {
								handler.rpsCard.get(i).setStatus(CardStatus.CLOSED);
							}else if(handler.rpsCard.get(i).isPlayerCard() == false) {
								handler.rpsCard.get(i).setStatus(CardStatus.OPENED);
							}
						}
					}
				}
				
				//paper button
				if(mx >= Game.window_width/2 - 60 && mx <= Game.window_width/2 - 60 + 120) {
					if(my >= 320 && my <= 455) {
						for(int i = 0; i < handler.rpsCard.size(); i++) {
							if(handler.rpsCard.get(i).getType() != 1 && handler.rpsCard.get(i).isPlayerCard() == true) {
								handler.rpsCard.get(i).setStatus(CardStatus.CLOSED);
							}else if(handler.rpsCard.get(i).isPlayerCard() == false) {
								handler.rpsCard.get(i).setStatus(CardStatus.OPENED);
							}
						}
					}
				}
				
				//scissors button
				if(mx >= Game.window_width/2 - 230 && mx <= Game.window_width/2 - 230 + 120) {
					if(my >= 320 && my <= 455) {
						for(int i = 0; i < handler.rpsCard.size(); i++) {
							if(handler.rpsCard.get(i).getType() != 2 && handler.rpsCard.get(i).isPlayerCard() == true) {
								handler.rpsCard.get(i).setStatus(CardStatus.CLOSED);
							}else if(handler.rpsCard.get(i).isPlayerCard() == false) {
								handler.rpsCard.get(i).setStatus(CardStatus.OPENED);
							}
						}
					}
				}
				
			}else if(Game.curMinGame == Game.CurMinGame.QUIZ) {
				
				//true button
				if(mx >= 280 && mx <= 510) {
					if(my >= 245 && my <= 375) {
						handler.curQuiz.setAnswer(true);
						handler.curQuiz.setAnswered(true);
						handler.curQuiz.setTruePressed(true);
						if(handler.curQuiz.getKey() == handler.curQuiz.getAnswer()) {
							handler.curQuiz.setStatus(CHECK.TRUE);
						}else if(handler.curQuiz.getKey() != handler.curQuiz.getAnswer()) {
							handler.curQuiz.setStatus(CHECK.FALSE);
						}
					}
				}
				
				//false button
				if(mx >= 580 && mx <= 710) {
					if(my >= 245 && my <= 375) {
						handler.curQuiz.setAnswer(false);
						handler.curQuiz.setAnswered(true);
						handler.curQuiz.setFalsePressed(true);
						if(handler.curQuiz.getKey() == handler.curQuiz.getAnswer()) {
							handler.curQuiz.setStatus(CHECK.TRUE);
						}else if(handler.curQuiz.getKey() != handler.curQuiz.getAnswer()) {
							handler.curQuiz.setStatus(CHECK.FALSE);
						}
					}
				}
				
			}else if(Game.curMinGame == Game.CurMinGame.RIDDLE) {
				
				//choice1
				if(mx >= 150 && mx <= 450) {
					if(my >= 250 && my <= 350) {
						handler.curQuest.setChoice(1);
						handler.curQuest.setAnswered(true);
						if(handler.curQuest.getChoice() == handler.curQuest.getAnswer()) {
							handler.curQuest.setTof(TOF.TRUE);
						}else if(handler.curQuest.getChoice() != handler.curQuest.getAnswer()) {
							handler.curQuest.setTof(TOF.FALSE);
						}
					}
				}
				
				//choice2
				if(mx >= 520 && mx <= 820) {
					if(my >= 250 && my <= 350) {
						handler.curQuest.setChoice(2);
						handler.curQuest.setAnswered(true);
						if(handler.curQuest.getChoice() == handler.curQuest.getAnswer()) {
							handler.curQuest.setTof(TOF.TRUE);
						}else if(handler.curQuest.getChoice() != handler.curQuest.getAnswer()) {
							handler.curQuest.setTof(TOF.FALSE);
						}
					}
				}
				
				//choice3
				if(mx >= 150 && mx <= 450) {
					if(my >= 380 && my <= 480) {
						handler.curQuest.setChoice(3);
						handler.curQuest.setAnswered(true);
						if(handler.curQuest.getChoice() == handler.curQuest.getAnswer()) {
							handler.curQuest.setTof(TOF.TRUE);
						}else if(handler.curQuest.getChoice() != handler.curQuest.getAnswer()) {
							handler.curQuest.setTof(TOF.FALSE);
						}
					}
				}
				
				//choice4
				if(mx >= 520 && mx <= 820) {
					if(my >= 380 && my <= 480) {
						handler.curQuest.setChoice(4);
						handler.curQuest.setAnswered(true);
						if(handler.curQuest.getChoice() == handler.curQuest.getAnswer()) {
							handler.curQuest.setTof(TOF.TRUE);
						}else if(handler.curQuest.getChoice() != handler.curQuest.getAnswer()) {
							handler.curQuest.setTof(TOF.FALSE);
						}
					}
				}
				
			}
		}else if(Game.state == Game.STATE.WIN) {
			//map button
			if(mx >= 720 && mx <= 870) {
				if(my >= 30 && my <= 100) {
					Game.state = Game.STATE.MAP;
				}
			}
		}else if(Game.state == Game.STATE.FINISH) {
			
			if(mx >= 440 && mx <= 530) {
				if(my >= 380 && my <= 470) {
					System.exit(1);
				}
			}
		}
	}
	
	public void mouseReleased(MouseEvent e) {
		int mx = e.getX();
		int my = e.getY();
		
		if(Game.curMinGame == Game.CurMinGame.QUIZ) {
			
			//true button
			if(mx >= 280 && mx <= 510) {
				if(my >= 245 && my <= 375) {
					handler.curQuiz.setTruePressed(false);
				}
			}
			
			//false button
			if(mx >= 580 && mx <= 710) {
				if(my >= 245 && my <= 375) {
					handler.curQuiz.setFalsePressed(false);
				}
			}
		}
	}
	
	public int getRandomNumberInRange(int min, int max) {
		Random r = new Random();
		return r.ints(min, (max + 1)).limit(1).findFirst().getAsInt();
	}
}
