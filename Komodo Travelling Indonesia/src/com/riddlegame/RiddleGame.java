package com.riddlegame;

import java.util.Scanner;

public class RiddleGame {
	private int choice;
	
	public RiddleGame() {
		int RightGuessCounter = 0;
		Scanner scan = new Scanner(System.in);
		
		System.out.println("\n\t\t---------Tebak-Tebakan Seputar Pulau Sumatra---------");
		System.out.println("\t\t*Minimal benar 7 dari 10 agar bisa menang!\n\n");
		
		System.out.println("Pertanyaan #1");
		System.out.println("Nama pulau di tengah Danau Toba.");
		System.out.println("1) Punjani \t\t 2) Samosir \t\t 3) Baloi \t\t 4) Kuliran");
		System.out.print("Jawabanmu : ");
		choice = scan.nextInt();
		
		if (choice == 2) RightGuessCounter++;
		
		System.out.println("\nPertanyaan #2");
		System.out.println("Provinsi di pulau Sumatera.");
		System.out.println("1) Lampung \t\t 2) Sutera \t\t 3) Gorontalo \t\t 4) Dontori");
		System.out.print("Jawabanmu : ");
		choice = scan.nextInt();
		
		if (choice == 1) RightGuessCounter++;
		
		System.out.println("\nPertanyaan #3");
		System.out.println("Gugusan pulau-pulau yang secara administratif masuk ke dalam provinsi Sumatera Barat.");
		System.out.println("1) Nagoya \t\t 2) Gameru \t\t 3) Pontianak \t\t 4) Mentawai");
		System.out.print("Jawabanmu : ");
		choice = scan.nextInt();
		
		if (choice == 4) RightGuessCounter++;
		
		System.out.println("\nPertanyaan #4");
		System.out.println("Nama Gunung Di Pulau Sumatra.");
		System.out.println("1) Semeru \t\t 2) Jaya Wijaya \t\t 3) Kerinci \t\t 4) Foranti");
		System.out.print("Jawabanmu : ");
		choice = scan.nextInt();
		
		if (choice == 3) RightGuessCounter++;
		
		System.out.println("\nPertanyaan #5");
		System.out.println("Pulau ini dikenal dengan olahraga tradisional fahombo.");
		System.out.println("1) Botswana \t\t 2) Nias \t\t 3) Pontianak \t\t 4) Natal");
		System.out.print("Jawabanmu : ");
		choice = scan.nextInt();
		
		if (choice == 3) RightGuessCounter++;
		
		System.out.println("\nPertanyaan #6");
		System.out.println("Sungai terpanjang di Pulau Sumatera.");
		System.out.println("1) Numani \t\t 2) Boras \t\t 3) Musi \t\t 4) Bengawan Solo");
		System.out.print("Jawabanmu : ");
		choice = scan.nextInt();
		
		if (choice == 3) RightGuessCounter++;
		
		System.out.println("\nPertanyaan #7");
		System.out.println("Kota Terbesar Di Pulau Sumatera.");
		System.out.println("1) Medan \t\t 2) Pontianak \t\t 3) Lampung \t\t 4) Bukittinggi");
		System.out.print("Jawabanmu : ");
		choice = scan.nextInt();
		
		if (choice == 1) RightGuessCounter++;
		
		System.out.println("\nPertanyaan #8");
		System.out.println("Provinsi yang terletak di bagian tengah Pulau Sumatera.");
		System.out.println("1) Malaya \t\t 2) Bintan \t\t 3) Riau \t\t 4) Tanjungpinang");
		System.out.print("Jawabanmu : ");
		choice = scan.nextInt();
		
		if (choice == 3) RightGuessCounter++;
		
		System.out.println("\nPertanyaan #9");
		System.out.println("Kerajaan bahari di pulau Sumatra.");
		System.out.println("1) Ternate-Tidore \t\t 2) Majapahit \t\t 3) Samudra Pasai \t\t 4) Sriwijaya");
		System.out.print("Jawabanmu : ");
		choice = scan.nextInt();
		
		if (choice == 4) RightGuessCounter++;
		
		System.out.println("\nPertanyaan #10");
		System.out.println("Objek wisata pulau di laut barat Sumatera, Sumatera Barat.");
		System.out.println("1) Nusa Kambang \t\t 2) Ganitarikan \t\t 3) Pagang \t\t 4) Martani");
		System.out.print("Jawabanmu : ");
		choice = scan.nextInt();
		
		if (choice == 3) RightGuessCounter++;
		
		System.out.println("\n\nSkor Kamu " + RightGuessCounter + "!");
		
		if (RightGuessCounter >= 7) {
			System.out.println("\nSelamat kamu berhasil menyelesaikan tantangan!\n");
		}
		else {
			System.out.println("\nCoba lagi...\n");
		}
		scan.close();
	}
}
