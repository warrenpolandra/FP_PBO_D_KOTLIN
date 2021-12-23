package com.main;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.util.LinkedList;
import java.util.Random;

import com.memorygame.Card;
import com.memorygame.CardStatus;
import com.quiz.Quiz;
import com.quiz.Quiz.CHECK;
import com.riddlegame.Question;
import com.riddlegame.Question.TOF;
import com.rockpaperscissors.RPSCard;

//handle all elements
public class Handler {
	
	private int winCounter = 0;
	private int solvedCounter = 0;
	private Game game;
	public Question curQuest;
	public Quiz curQuiz;
	boolean finished, quizFinished;
	private int riddleIndex = 0, quizIndex = 0;
	
	public boolean sumateraFinished = false, jawaFinished = false, kalimantanFinished = false, sulawesiFinished = false, papuaFinished = false;
	
	//all elements grouped
	LinkedList<GameObject> object = new LinkedList<GameObject>();
	LinkedList<Location> location = new LinkedList<Location>();
	LinkedList<Card> card = new LinkedList<Card>();
	LinkedList<RPSCard> rpsCard = new LinkedList<RPSCard>();
	LinkedList<GameObject> temp = new LinkedList<GameObject>();
	LinkedList<Question> question = new LinkedList<Question>();
	LinkedList<Quiz> quiz = new LinkedList<Quiz>();
	
	//movement
	private boolean up = false, down = false, right = false, left = false;
	
	public static enum Facing{
		UP,
		DOWN,
		LEFT,
		RIGHT
	};
	public static Facing face = Facing.RIGHT;
	
	public Handler(Game game) {
		this.game = game;
	}
	
	public void tick() {
		
		if(Game.state == Game.STATE.MINIGAME) {
			if(Game.curMinGame == Game.CurMinGame.MEMORY) {
				solvedCounter = 0;
				int counter = 0;
				for(int i = 0; i < card.size(); i++) {
					Card tempCard = card.get(i);
					if(tempCard.getStatus() == CardStatus.OPENED) {
						counter++;
					}
				}
				if(counter == 2) {
					int pair = 0;
					int position = 0;
					for(int i = 0; i < card.size(); i++) {
						if(card.get(i).getStatus() == CardStatus.OPENED && pair == 0) {
							pair = card.get(i).getType();
							position = card.get(i).getPosition();
						}else if(card.get(i).getStatus() == CardStatus.OPENED && pair != 0) {
							if(card.get(i).getType() == pair) {
								try {
									Thread.sleep(500);
							    } catch (InterruptedException e) {
							    	e.printStackTrace();
							    }
								card.get(i).setStatus(CardStatus.SOLVED);
								card.get(position - 1).setStatus(CardStatus.SOLVED);
							}else if(card.get(i).getType() != pair) {
								try {
									Thread.sleep(1000);
							    } catch (InterruptedException e) {
							    	e.printStackTrace();
							    }
								card.get(i).setStatus(CardStatus.CLOSED);
								card.get(position - 1).setStatus(CardStatus.CLOSED);
							}
						}
					}
				}
				for(int i = 0; i < card.size(); i++) {
					if(card.get(i).getStatus() == CardStatus.SOLVED) {
						solvedCounter++;
					}
				}
				
				if(solvedCounter == 16) {
					solvedCounter = 0;
					try {
						Thread.sleep(1000);
				    } catch (InterruptedException e) {
				    	e.printStackTrace();
				    }
					for(int i = 0; i < card.size(); i++) {
						card.get(i).setStatus(CardStatus.CLOSED);
					}
					game.kalimantan.setUnlocked(true);
					if(jawaFinished == false) {
						game.stoneTabletCounter++;
						jawaFinished = true;
					}
					Game.state = Game.STATE.WIN;
				}else {
					solvedCounter = 0;
				}
				
			}else if(Game.curMinGame == Game.CurMinGame.MAZE) {
				
				for(int i = 0; i < object.size(); i++) {
					GameObject tempObject = object.get(i);
					
					tempObject.tick();
				}
			}else if(Game.curMinGame == Game.CurMinGame.RPS) {
				if(winCounter < 3) {
					int cardOpened = 0;
					
					for(int i = 0; i < rpsCard.size(); i++) {
						if(rpsCard.get(i).getStatus() == CardStatus.OPENED && rpsCard.get(i).isPlayerCard() == true) {
							cardOpened++;
						}
					}
					
					if(cardOpened == 1) {
						try {
							Thread.sleep(1000);
					    } catch (InterruptedException e) {
					    	e.printStackTrace();
					    }
						
						for(int i = 0; i < rpsCard.size(); i++) {
							if(rpsCard.get(i).getStatus() == CardStatus.OPENED && rpsCard.get(i).isPlayerCard() == true) {
								if(rpsCard.get(i).getType() == rpsCard.getLast().getType()) {
									//seri
								}else if(rpsCard.get(i).getType() == 0) {
									for(int j = 0; j < rpsCard.size(); j++) {
										if(rpsCard.get(j).isPlayerCard() == false) {
											if(rpsCard.get(j).getType() == 1) {
												//comp win
											}else if(rpsCard.get(j).getType() == 2) {
												//player win
												winCounter++;
											}
										}
										
									}
								}else if(rpsCard.get(i).getType() == 1) {
									for(int j = 0; j < rpsCard.size(); j++) {
										if(rpsCard.get(j).isPlayerCard() == false) {
											if(rpsCard.get(j).getType() == 0) {
												//player win
												winCounter++;
											}
											else if(rpsCard.get(j).getType() == 2) {
												//comp win
											}
										}
									}
								}else if(rpsCard.get(i).getType() == 2) {
									for(int j = 0; j < rpsCard.size(); j++) {
										if(rpsCard.get(j).isPlayerCard() == false) {
											if(rpsCard.get(j).getType() == 0) {
												//comp win
											}
											else if(rpsCard.get(j).getType() == 1) {
												//player win
												winCounter++;
											}
										}
									}
								}
							}
						}
						
						int compChoice = getRandomNumberInRange(0, 2);
						for(int i = 0; i < rpsCard.size(); i++) {
							if(rpsCard.get(i).isPlayerCard() == false) {
								rpsCard.get(i).setType(compChoice);
							}
						}
						
						for(int i = 0; i < rpsCard.size(); i++) {
							if(rpsCard.get(i).getStatus() == CardStatus.CLOSED && rpsCard.get(i).isPlayerCard() == true) {
								rpsCard.get(i).setStatus(CardStatus.OPENED);
							}else if(rpsCard.get(i).isPlayerCard() == false) {
								rpsCard.get(i).setStatus(CardStatus.CLOSED);
							}
						}
					}
				}
				else {
					try {
						Thread.sleep(1000);
				    } catch (InterruptedException e) {
				    	e.printStackTrace();
				    }
					winCounter = 0;
					if(sulawesiFinished == false) {
						game.stoneTabletCounter++;
						sulawesiFinished = true;
					}
					game.papua.setUnlocked(true);
					Game.state = Game.STATE.WIN;
				}
				
			}else if(Game.curMinGame == Game.CurMinGame.QUIZ) {
				if(quiz.get(quizIndex).isSolved() == false) {
					curQuiz = quiz.get(quizIndex);
					
					if(curQuiz.isAnswered()) {
						try {
							Thread.sleep(1000);
					    } catch (InterruptedException e) {
					    	e.printStackTrace();
					    }
						if(curQuiz.getKey() == curQuiz.getAnswer()) {
							try {
								Thread.sleep(9000);
						    } catch (InterruptedException e) {
						    	e.printStackTrace();
						    }
							quiz.get(quizIndex).setSolved(true);
						}
						quizIndex++;
					}
					curQuiz.setStatus(CHECK.NULL);
					curQuiz.setAnswered(false);
				}else {
					quizIndex++;
				}
				if(quizIndex == 5) {
					quizIndex = 0;
				}
				
				quizFinished = true;
				for(int i = 0; i < quiz.size(); i++) {
					if(quiz.get(i).isSolved() == false) {
						quizFinished = false;
						break;
					}
				}
				if(quizFinished == true) {
					try {
						Thread.sleep(1000);
				    } catch (InterruptedException e) {
				    	e.printStackTrace();
				    }
					for(int i = 0; i < quiz.size(); i++) {
						quiz.get(i).setSolved(false);
					}
					if(kalimantanFinished == false) {
						game.stoneTabletCounter++;
						kalimantanFinished = true;
					}
					game.sulawesi.setUnlocked(true);
					Game.state = Game.STATE.WIN;
				}
			}else if(Game.curMinGame == Game.CurMinGame.RIDDLE) {
				if(question.get(riddleIndex).isSolved() == false) {
					curQuest = question.get(riddleIndex);
					
					if(curQuest.isAnswered()) {
						try {
							Thread.sleep(1000);
					    } catch (InterruptedException e) {
					    	e.printStackTrace();
					    }
						if(curQuest.getChoice() == curQuest.getAnswer()) {
							question.get(riddleIndex).setSolved(true);
						}
						riddleIndex++;
					}
					curQuest.setTof(TOF.NULL);
					curQuest.setAnswered(false);
				}else {
					riddleIndex++;
				}
				if(riddleIndex == 10) {
					riddleIndex = 0;
				}
				
				finished = true;
				for(int i = 0; i < question.size(); i++) {
					if(question.get(i).isSolved() == false) {
						finished = false;
						break;
					}
				}
				if(finished == true) {
					try {
						Thread.sleep(1000);
				    } catch (InterruptedException e) {
				    	e.printStackTrace();
				    }
					if(sumateraFinished == false) {
						game.stoneTabletCounter++;
						sumateraFinished = true;
					}
					for(int i = 0; i < question.size(); i++) {
						question.get(i).setSolved(false);
					}
					game.jawa.setUnlocked(true);
					Game.state = Game.STATE.WIN;
				}
			}
		}
	}
	
	public void render(Graphics g) {
		if(Game.state == Game.STATE.MAP) {
			for(int i = 0; i < location.size(); i++) {
				Location tempLoc = location.get(i);
				
				tempLoc.render(g);
			}
		} else if(Game.state == Game.STATE.MINIGAME) {
			if(Game.curMinGame == Game.CurMinGame.MEMORY) {
				for(int i = 0; i < card.size(); i++) {
					card.get(i).render(g);
				}
				
			}else if(Game.curMinGame == Game.CurMinGame.MAZE) {
				for(int i = 0; i < object.size(); i++) {
					GameObject tempObject = object.get(i);
					
					tempObject.render(g);
				}
			}else if(Game.curMinGame == Game.CurMinGame.RPS) {
				Font font = new Font("tower ruins regular", Font.BOLD, 20);
				Font font1 = new Font("tower ruins regular", Font.BOLD, 30);
				Font font2 = new Font("tower ruins regular", Font.BOLD, 25);
				g.setFont(font1);
				g.setColor(Color.WHITE);
				g.setFont(font2);
				g.drawString("ROCK PAPER SCISSORS", 25, 50);
				g.drawString("Win: " + winCounter, 110, 140);
				g.setFont(font);
				g.drawString("Menangkan 3x", 80, 90);
				
				for(int i = 0; i < rpsCard.size(); i++) {
					rpsCard.get(i).render(g);
				}
			}else if(Game.curMinGame == Game.CurMinGame.QUIZ) {
				curQuiz.render(g);
			}else if(Game.curMinGame == Game.CurMinGame.RIDDLE) {
				curQuest.render(g);
			}
		}
	}
	
	public int getRandomNumberInRange(int min, int max) {
		Random r = new Random();
		return r.ints(min, (max + 1)).limit(1).findFirst().getAsInt();
	}
	
	public void addQuiz(Quiz tempQuiz) {
		quiz.add(tempQuiz);
	}
	
	public void addQuest(Question tempQuest) {
		question.add(tempQuest);
	}
	
	public void addCard(Card tempCard) {
		card.add(tempCard);
	}
	
	public void addRPSCard(RPSCard tempCard) {
		rpsCard.add(tempCard);
	}
	
	public void removeRPSCard(RPSCard tempCard) {
		rpsCard.remove(tempCard);
	}
	
	public void addLocation(Location tempLoc) {
		location.add(tempLoc);
	}
	
	public void addObject(GameObject tempObject) {
		object.add(tempObject);
	}
	
	public void addTempObject(GameObject tempObject) {
		temp.add(tempObject);
	}
	
	public void removeObject(GameObject tempObject) {
		object.remove(tempObject);
	}
	
	public boolean isUp() {
		return up;
	}
	public void setUp(boolean up) {
		this.up = up;
	}
	public boolean isDown() {
		return down;
	}
	public void setDown(boolean down) {
		this.down = down;
	}
	public boolean isRight() {
		return right;
	}
	public void setRight(boolean right) {
		this.right = right;
	}
	public boolean isLeft() {
		return left;
	}
	public void setLeft(boolean left) {
		this.left = left;
	}
	
}
