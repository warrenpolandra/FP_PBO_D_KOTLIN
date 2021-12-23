package com.main;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

import com.memorygame.Card;
import com.memorygame.CardStatus;
import com.quiz.Quiz;
import com.riddlegame.Question;
import com.rockpaperscissors.RPSCard;

public class Game extends Canvas implements Runnable{
	
	private static final long serialVersionUID = -262125430428731760L;

	//cardType array for randomizer
	Integer[] cardType = {1, 1, 2, 2, 3, 3, 4, 4, 5, 5, 6, 6, 7, 7, 8, 8};	
	List <Integer> typeList = Arrays.asList(cardType);
	
	private boolean isRunning = false;
	private Thread thread;
	private Handler handler;
	private Camera camera;
	private Menu menu = new Menu();
	private Start start = new Start();
	public int stoneTabletCounter = 0;
	
	//location
	public Location sumatera = new Location("Sumatera", "Pulau Sumatera adalah pulau keenam  terbesar  di  dunia  yang\nterletak  di  Indonesia,  "
			+ "dengan  luas  473.481  km  persegi  dan\ntotal  penduduk  sekitar  57.940.351  jiwa.  Secara  umum,  pulau\nSumatra didiami  oleh  bangsa Melayu,  "
			+ "yang  terbagi  ke  dalam\nbeberapa suku/subsuku.", "Penduduk  di  Pulau  Sumatra  melebihi  negara  Kanada. Kanada\nadalah   negara  yang  "
			+ "ada  di  Benua Amerika, Negara ini untuk\njumlahnya penduduknya masih kalah dengan  Jumlah  Penduduk\ndi   Pulau   Sumatra  yang  "
			+ " totalnya mencapai 52.210.926 Jiwa,\nsehingga  bisa dikatakan bahwa Pulau  Sumatra  adalah  Pulau\ndengan  salah satu Penduduk "
			+ "yang cukup ramai.", 140, 240, true);
	
	public Location jawa = new Location("Jawa", "Pulau Jawa adalah sebuah pulau di Indonesia dan merupakan\npulau terluas ke-13 di dunia."
			+ " Dengan jumlah penduduk sekitar\n150 juta, pulau ini pulau berpenduduk terbanyak di dunia dan\nmerupakan  salah  satu  "
			+ "tempat terpadat di dunia. Meskipun\nhanya menempati urutan terluas ke-5, Pulau Jawa dihuni oleh\n60% penduduk Indonesia.",
			"Bahasa   jawa   merupakan   bahasa   dengan  ucapan  paling\nterbanyak  yang  digunakan  oleh  orang  Indonesia  daripada\nbahasa  nasional  "
			+ "itu  sendiri yaitu bahasa Indonesia. Hal ini\nterjadi   pada   tahun   2013.   Bahasa   jawa   digunakan   di\nbeberapa daerah di malaysia "
			+ "jadi bahasa jawa tidak hanya\ndigunakan di daerah jawa saja.", 320, 380, false);
	
	public Location kalimantan = new Location("Kalimantan", "Pulau Kalimantan yang disebut Borneo di dunia internasional,\nadalah  pulau  terbesar  "
			+ "ketiga  di  dunia  yang  terletak  di\nsebelah   utara   Pulau   Jawa  dan  di  sebelah  barat  Pulau\nSulawesi. Pulau ini dibagi menjadi "
			+ "3  wilayah:  Indonesia  (73%),\nMalaysia  (26%),  dan  Brunei  (1%).  Pulau  ini terkenal dengan\njulukan \"Pulau Seribu Sungai\" "
			+ "karena  banyaknya  sungai  yang\nmengalir di pulau ini.", "Pulau kalimantan merupakan pulau yang paling aman dari gempa.\nPulau "
			+ "ini termasuk kedalam daerah  yang  memiliki  daerah yang\nsangat luas dan berada di daerah yang  memang  tidak  memiliki\ngunung "
			+ "berapi dan juga tidak ada tabrakan  antara  lempengan\nyang ada di lautnya tersebut.", 390, 250, false);
	
	public Location sulawesi = new Location("Sulawesi", "Pulau Sulawesi dahulu dikenal sebagai Celebes.  Pulau  Sulawesi\nterletak di sebelah timur "
			+ "Kalimantan, sebelah barat Kepulauan\nMaluku,  dan  sebelah  selatan  Mindanao  dan  Kepulauan  Sulu,\nFilipina.  Di Indonesia, hanya  "
			+ "Pulau  Sumatra,  Kalimantan,  dan\nPapua yang  lebih  besar  luas  wilayahnya  serta  hanya  Pulau\nJawa dan Sumatra  yang  memiliki  populasi  "
			+ "lebih  banyak  dari\nSulawesi.", "Sulawesi adalah pulai yang telah banyak melahirkan duta-duta\nyang sangat genius dan memiliki suatu "
			+ "posisi yang cukup penting\ndi   Indonesia,   salah   satunya  yang  paling  menonjol  adalah\nPresiden BJ  Habibie  yang  lahir  di  Kota  "
			+ "Pare-Pare,  Provinsi\nSulawesi Selatan.", 525, 260, false);
	
	public Location papua = new Location("Papua", "Papua sebelumnya disebut sebagai Irian Jaya. Sejak tahun 2003,\nPapua dibagi menjadi dua provinsi, dengan  bagian  "
			+ "timur  tetap\nmemakai nama Papua sedangkan bagian baratnya memakai nama\nPapua Barat (Pabar). Provinsi Papua memiliki luas 312.224,37 km\npersegi "
			+ "dan merupakan provinsi terbesar dan terluas pertama\ndi Indonesia.", "Danau   Sentani   yang   terletak   di  Jayapura,  Ibukota  Papua\nmenyajikan "
			+ "keindahan  latar  Pegunungan  Cyclops  di  sebelah\nutara dan pepohonan yang hijau.  Danau  ini  memiliki  keunikan\ndengan keberadaan 22 "
			+ "pulau yang tersebar  di  Danau  Sentani.\nSalah satunya yaitu  Pulau  Asei  terkenal  dengan  kerajinan\ntangan khas Papua, "
			+ "seperti kain kulit kayu bermotif indah.", 880, 300, false);
	
	//game states
	public static enum STATE{
		MENU,
		MAP,
		START,
		PAUSE,
		MINIGAME,
		WIN,
		FINISH
	};
	public static STATE state = STATE.START;
	
	//current location
	public Location curLoc = sumatera;
	
	//current minigame
	public static enum CurMinGame{
		MEMORY,
		MAZE,
		RIDDLE,
		QUIZ,
		RPS
	}
	public static CurMinGame curMinGame = CurMinGame.RIDDLE;
	
	//riddle questions
	public Question quest1 = new Question("Nama pulau di tengah Danau Toba adalah...", "Punjani", "Samosir", "Baloi", "Kuliran", 2);	
	public Question quest2 = new Question("Di bawah ini yang merupakan\nprovinsi di pulau Sumatera adalah...", "Lampung", "Sutera", "Gorontalo", "Dontori", 1);
	public Question quest3 = new Question("Gugusan pulau-pulau yang secara administratif\nmasuk ke dalam provinsi Sumatera Barat adalah...", "Nagoya", "Gameru", "Pontianak", "Mentawai", 4);
	public Question quest4 = new Question("Salah satu nama Gunung\ndi Pulau Sumatra adalah...", "Semeru", "Jaya Wijaya", "Kerinci", "Foranti", 3);
	public Question quest5 = new Question("Pulau yang dikenal dengan\nolahraga tradisional fahombo adalah...", "Botswana", "Nias", "Pontianak", "Natal", 3);
	public Question quest6 = new Question("Sungai terpanjang di Pulau Sumatera adalah...", "Numani", "Boras", "Musi", "Bengawan Solo", 3);
	public Question quest7 = new Question("Kota Terbesar Di Pulau Sumatera adalah...", "Medan", "Pontianak", "Lampung", "Bukittinggi", 1);
	public Question quest8 = new Question("Provinsi yang terletak\ndi bagian tengah Pulau Sumatera adalah...", "Malaya", "Bintan", "Riau", "Tanjungpinang", 3);
	public Question quest9 = new Question("Kerajaan bahari di pulau Sumatra adalah...", "Ternate-Tidore", "Majapahit", "Samudra Pasai", "Sriwijaya", 4);
	public Question quest10 = new Question("Salah satu objek wisata pulau di\nlaut barat Sumatera, Sumatera Barat adalah...", "Nusa Kambang", "Ganitarikan", "Pagang", "Martani", 3);
	
	//quiz questions
	public Quiz quiz1 = new Quiz("Kalimantan memiliki hutan hujan tropis tertua\ndi Indonesia yang memiliki umur 130 tahun", "Hutan Hujan Kalimantan "
			+ "merupakan hutan tertua\ndi dunia dengan daya tarik pada rumah bagi 600 spesies burung,\n10 primata, beragam fauna dan flora lainnya.", true);
	public Quiz quiz2 = new Quiz("Kalimantan merupakan salah satu habitat dari\nhewan eksotis di Indonesia yang bernama komodo", "Kalimantan merupakan salah "
			+ "satu habitat dari\nhewan eksotis di Indonesia yang bernama bekantan", false);
	public Quiz quiz3 = new Quiz("Taman Nasional Sebangau di Kalimantan menjadi\ntempat tinggal orang utan yang kini makin punah\n"
			+ "akibat pemburuan liar manusia.", "Taman Nasional Sebangau merupakan salah satu taman nasional yang\nterletak di Kalimantan Tengah, Indonesia "
			+ "yang menjadi tempat tinggal\n808 jenis flora, 35 jenis mamalia, 182 jenis burung, dan 54 spesies ular", true);
	public Quiz quiz4 = new Quiz("Kalimantan memiliki bandara\ntermegah kedua di Asia Tenggara.", "Kalimantan tidak memiliki bandara termegah kedua di Asia Tenggara,\n"
			+ "tetapi Kalimantan memiliki Masjid Islamic Center Samarinda\nyang merupakan masjid termegah kedua di asia Tenggara", false);
	public Quiz quiz5 = new Quiz("Di Kalimantan, Terdapat lumba-lumba air tawar\ndi Sungai Mahakam yang merupakan salah satu\nsatwa yang terancam punah.", "Pesut mahakam "
			+ "(Orcaella brevirostris) adalah sejenis hewan mamalia\nyang sering disebut lumba-lumba air tawar yang berstatus terancam.\nHewan ini dapat ditemukan di Sungai "
			+ "Mahakam di Kalimantan Timur", true);
	
	//image
	private BufferedImage front_page = null;
	private BufferedImage maze = null;
	private BufferedImage dirt = null;
	private BufferedImage tablet = null;
	private BufferedImage sand = null;
	private BufferedImage stone_button = null;
	private BufferedImage grass = null;
	private BufferedImage jungle_bg = null;
	private BufferedImage card_bg = null;
	private BufferedImage tablet_icon = null;
	private BufferedImage tablet_icon_fade = null;
	private BufferedImage finish_bg = null;
	private BufferedImage quitButton = null;
	private BufferedImage rps_sign = null;
	
	//Background
	private BufferedImage map = null;
	private BufferedImage memory_bg = null;
	
	//memory card image
	private BufferedImage cardBack = null;
	private BufferedImage cardImg1 = null;
	private BufferedImage cardImg2 = null;
	private BufferedImage cardImg3 = null;
	private BufferedImage cardImg4 = null;
	private BufferedImage cardImg5 = null;
	private BufferedImage cardImg6 = null;
	private BufferedImage cardImg7 = null;
	private BufferedImage cardImg8 = null;
	
	//window
	public static int window_width = 1000;
	public static int window_height = 563;
	
	//Game manager
	public Game() {
		new Window(window_width, window_height, "Komodo Travelling Indonesia", this);
		start();
		
		handler = new Handler(this);
		camera = new Camera(0, 0, window_width, window_height);
		this.addKeyListener(new KeyInput(handler));
		this.addMouseListener(new MouseInput(handler, this));
		
		//insert questions
		handler.addQuest(quest1);
		handler.addQuest(quest2);
		handler.addQuest(quest3);
		handler.addQuest(quest4);
		handler.addQuest(quest5);
		handler.addQuest(quest6);
		handler.addQuest(quest7);
		handler.addQuest(quest8);
		handler.addQuest(quest9);
		handler.addQuest(quest10);
		handler.curQuest = quest1;
		
		//insert quiz
		handler.addQuiz(quiz1);
		handler.addQuiz(quiz2);
		handler.addQuiz(quiz3);
		handler.addQuiz(quiz4);
		handler.addQuiz(quiz5);
		handler.curQuiz = quiz1;
		
		//insert RPS cards
		//Player's Card
		RPSCard rockCard = new RPSCard(Game.window_width/2 + 110, 320, 0, CardStatus.OPENED, true);
		RPSCard paperCard = new RPSCard(Game.window_width/2 - 60, 320, 1, CardStatus.OPENED, true);
		RPSCard scissorsCard = new RPSCard(Game.window_width/2 - 230, 320, 2, CardStatus.OPENED, true);
		
		handler.addRPSCard(rockCard);
		handler.addRPSCard(paperCard);
		handler.addRPSCard(scissorsCard);
		
		//computer's card
		int compChoice = handler.getRandomNumberInRange(0, 2);
		RPSCard comCard = new RPSCard(Game.window_width/2 - 60, 60, compChoice, CardStatus.CLOSED, false);
		handler.addRPSCard(comCard);
		
		BufferedImageLoader loader = new BufferedImageLoader();
		front_page = loader.loadImage("/front_page.png");
		maze = loader.loadImage("/maze.png");
		dirt = loader.loadImage("/dirt.png");
		tablet = loader.loadImage("/tablet.png");
		sand = loader.loadImage("/sand.png");
		stone_button = loader.loadImage("/stone_button.png");
		grass = loader.loadImage("/grass.png");
		tablet_icon = loader.loadImage("/tablet_icon.png");
		tablet_icon_fade = loader.loadImage("/tablet_icon_fade.png");
		quitButton = loader.loadImage("/quitButton.png");
		
		//background
		map = loader.loadImage("/map.png");
		memory_bg = loader.loadImage("/memory_bg.png");
		jungle_bg = loader.loadImage("/jungle_bg.jpg");
		card_bg = loader.loadImage("/card_bg.png");
		finish_bg = loader.loadImage("/finish_bg.png");
		rps_sign = loader.loadImage("/rps_sign.png");
		
		//cards
		cardBack = loader.loadImage("/card_back.png");
		cardImg1 = loader.loadImage("/batik.png");
		cardImg2 = loader.loadImage("/borobudur.png");
		cardImg3 = loader.loadImage("/gamelan.png");
		cardImg4 = loader.loadImage("/joglo.png");
		cardImg5 = loader.loadImage("/keris.png");
		cardImg6 = loader.loadImage("/reog.png");
		cardImg7 = loader.loadImage("/tumpeng.png");
		cardImg8 = loader.loadImage("/wayang.png");
		
		//insert locations
		handler.addLocation(sumatera);
		handler.addLocation(jawa);
		handler.addLocation(kalimantan);
		handler.addLocation(sulawesi);
		handler.addLocation(papua);
		
		//load maze
		loadLevel(maze);
		
		//shuffle memory cards
		Collections.shuffle(typeList);
		typeList.toArray(cardType);
		//insert memory cards
		for(int i = 0; i < 16; i++) {
			Card tempCard;
			if(cardType[i] == 1) {
				tempCard = new Card(i+1, cardType[i], cardBack, cardImg1);
			}else if(cardType[i] == 2) {
				tempCard = new Card(i+1, cardType[i], cardBack, cardImg2);
			}else if(cardType[i] == 3) {
				tempCard = new Card(i+1, cardType[i], cardBack, cardImg3);
			}else if(cardType[i] == 4) {
				tempCard = new Card(i+1, cardType[i], cardBack, cardImg4);
			}else if(cardType[i] == 5) {
				tempCard = new Card(i+1, cardType[i], cardBack, cardImg5);
			}else if(cardType[i] == 6) {
				tempCard = new Card(i+1, cardType[i], cardBack, cardImg6);
			}else if(cardType[i] == 7) {
				tempCard = new Card(i+1, cardType[i], cardBack, cardImg7);
			}else{
				tempCard = new Card(i+1, cardType[i], cardBack, cardImg8);
			}
			
			handler.addCard(tempCard);
		}
	}
	
	private void start() {
		isRunning = true;
		thread = new Thread(this);
		thread.start();
	}
	
	private void stop() {
		isRunning = false;
		try {
			thread.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	//loop function to run the game
	public void run() {
		this.requestFocus();
		long lastTime = System.nanoTime();
		double amountOfTicks = 60.0;
		double ns = 1000000000 / amountOfTicks;
		double delta = 0;
		long timer = System.currentTimeMillis();
		while(isRunning) {
			long now = System.nanoTime();
			delta += (now - lastTime) / ns;
			lastTime = now;
			while(delta >= 1) {
				tick();
				delta--;
			}
			render();
			
			if(System.currentTimeMillis() - timer > 1000) {
				timer += 1000;
			}
		}
		stop();
	}
	
	//refresh
	public void tick() {
		if(state == STATE.MINIGAME) {
			if(curMinGame == CurMinGame.MAZE) {
				for(int i = 0; i < handler.object.size(); i++) {
					if(handler.object.get(i).getId() == ID.Player) {
						camera.tick(handler.object.get(i));
					}
				}
			}
		}
		if(state == STATE.MAP) {
			if(stoneTabletCounter == 5) {
				state = STATE.FINISH;
			}
		}
		handler.tick();
	}
	
	//render graphic
	public void render() {
		BufferStrategy bs = this.getBufferStrategy();
		if(bs == null) {
			this.createBufferStrategy(3);
			return;
		}
		
		Graphics g = bs.getDrawGraphics();
		Graphics2D g2d = (Graphics2D) g;
		
		g.setColor(Color.black);
		g.fillRect(0, 0, window_width, window_height);
		
		if(state == STATE.MAP) {
			int j = 0;
			g.drawImage(map, 0, 0, this);
			Font font = new Font("tower ruins regular", Font.BOLD, 40);
			Font font1 = new Font("tower ruins regular", Font.BOLD, 20);
			
			g.setFont(font1);
			g.setColor(Color.WHITE);
			g.drawString("Tablets found : ", 15, 495);
			for(int i = 0; i < stoneTabletCounter; i++) {
				g.drawImage(tablet_icon, 195 + i*60, 470, null);
				j = i + 1;
			}
			
			for(int k = j; k < 5; k++) {
				g.drawImage(tablet_icon_fade, 195 + k*60, 470, null);
			}
			
			g.setFont(font);
			g.setColor(Color.green);
			g.drawString("Pilihlah 1 area", window_width/2 - 170, 60);
			
			handler.render(g);
			
		}else if (state == STATE.MENU) {
			g.drawImage(front_page, 0, 0, this);
			menu.render(g);
		}else if(state == STATE.START) {
			start.render(g);
		}else if(state == STATE.MINIGAME) {
			if(curMinGame == CurMinGame.MEMORY) {
				g.drawImage(memory_bg, 0, 0, this);
				handler.render(g);
				
			}else if(curMinGame == CurMinGame.MAZE) {
				//////////////////////////////////
				g2d.translate(-camera.getX(), -camera.getY());
				
				for(int xx = 0; xx < 30*72; xx += 32) {
					for(int yy = 0; yy < 30 *72; yy += 32) {
						g.drawImage(dirt, xx, yy, null);
					}
				}				
				handler.render(g);
				
				g2d.translate(camera.getX(), camera.getY());
				
				//////////////////////////////////
				
			}else if(curMinGame == CurMinGame.RPS) {
				g.drawImage(card_bg, 0, 0, null);
				g.drawImage(rps_sign, 11, 12, null);
				handler.render(g);
				
			}else if(curMinGame == CurMinGame.QUIZ) {
				g.drawImage(jungle_bg, 0, 0, null);
				handler.render(g);
			}else if(curMinGame == CurMinGame.RIDDLE) {
				g.drawImage(grass, 0, 0, null);
				handler.render(g);
			}
		} else if(state == STATE.WIN) {
			g.drawImage(sand, 0, 0, null);
			g.drawImage(tablet, 45, 15, null);
			g.drawImage(stone_button, 720, 30, null);
			Font font0 = new Font("tower ruins regular", Font.BOLD, 30);
			Font font1 = new Font("tower ruins regular", Font.BOLD, 21);
			g.setFont(font0);
			g.setColor(Color.BLACK);
			drawStringMid("Pulau " + curLoc.getName(), window_width, window_height, g, 2, 10);
			g.setFont(font1);
			g.drawString("Deskripsi : ", 120, 120);
			drawString(g, curLoc.getDescription(), 120, 120);
			g.drawString("Fun Fact : ", 120, 320);
			drawString(g, curLoc.getFunFact(), 120, 320);
		} else if(state == STATE.FINISH) {
			g.drawImage(finish_bg, 0, 0, null);
			g.drawImage(quitButton, 440, 380, null);
		}
		
		g.dispose();
		bs.show();
	}
	
	//information on stone tablet
	private void drawString(Graphics g, String text, int x, int y) {
	    for (String line : text.split("\n"))
	        g.drawString(line, x, y += g.getFontMetrics().getHeight());
	}
	
	private void drawStringMid(String s, int w, int h, Graphics g, int xx, int yy) {
	    FontMetrics fm = g.getFontMetrics();
	    int x = (w - fm.stringWidth(s)) / xx;
	    int y = (fm.getAscent() + (h - (fm.getAscent() + fm.getDescent())) /yy);
	    g.drawString(s, x, y);
	 }
	
	//player position
	private int playerX, playerY;
	
	//loading the level
	private void loadLevel(BufferedImage image) {
		int w = image.getWidth();
		int h = image.getHeight();
		
		for(int xx = 0; xx < w; xx++) {
			for(int yy = 0; yy < h; yy++) {
				int pixel = image.getRGB(xx, yy);
				int red = (pixel >> 16) & 0xff;
				int green = (pixel >> 8) & 0xff;
				int blue = (pixel) & 0xff;
				
				if(red == 255)
					handler.addObject(new Bush(xx*32, yy*32, ID.BUSH));
				if(blue == 255 && green == 0) {
					playerX = xx;
					playerY = yy;
				}
			}
		}
		
		handler.addObject(new Player(playerX*32, playerY*32, ID.Player, handler, this));
	}
	
	public static void main(String[] args) throws Exception{
		new Game();
	}
	
	public static void playMusic(String filePath) {
		try {
			File musicPath = new File(filePath);
			
			if(musicPath.exists()) {
				AudioInputStream audioInput = AudioSystem.getAudioInputStream(musicPath);
				Clip clip = AudioSystem.getClip();
				clip.open(audioInput);
				clip.start();
				clip.loop(Clip.LOOP_CONTINUOUSLY);
			}
		}catch(Exception e) {
			System.out.println("error");
		}
	}
	

}
