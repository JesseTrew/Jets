package com.skilldistillery.jetsapp;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

public class JetsApplication {
	boolean menu = true;
	List<Jet> jets = new ArrayList<>();
	private AirField airField;
	Scanner kb = new Scanner(System.in);

	public static void main(String[] args) {
		Scanner kb = new Scanner(System.in);
		JetsApplication ja = new JetsApplication();
//		System.out.println(jets);
		ja.launch(kb);
		kb.close();
	}

	public void launch(Scanner kb) {
		String fileName = "jets.txt";
		jets = readJets(fileName);
		do {
			displayUserMenu();
			menuSelection(kb);
		} while(menu);
	}

	public void displayUserMenu() {
		System.out.println("Welcome to the Jets Application main menu\n" + "**********************************\n"
				+ "Please make a selection\n" + "**********************************\n" + "1) List fleet\n"
				+ "2) Fly all jets\n" + "3) View fastest jet\n" + "4) View jet with longest range\n"
				+ "5) Load all Cargo Jets\n" + "6) Dogfight!\n" + "7) Add a jet to Fleet\n"
				+ "8) Remove a jet from Fleet\n" + "9) Quit");
	}

	public boolean menuSelection(Scanner kb) {
		
		if (kb.nextInt() == 1) {
			listFleet(jets);
		} 
		else if (kb.nextInt() == 2) {
			flyJets();
		} 
		else if (kb.nextInt() == 3) {
			viewFastest();
		}
		else if(kb.nextInt() == 4) {
			viewLongestRange();
		}
		else if(kb.nextInt() == 5) {
			loadCargo();
		}
		else if(kb.nextInt() == 6) {
			dogfight();
		}
		else if(kb.nextInt() == 7) {
			addToFleet();
		}
		else if(kb.nextInt() == 8) {
			removeFromFleet();
		}
		else if(kb.nextInt() == 9) {
			System.out.println("Goodbye.");
			return false;
		}
		else {
			System.err.println("Invalid input.");
			System.out.println("Enter another selection:");
			menuSelection(kb);
		}
		return true;
	}

	public List<Jet> readJets(String fileName) {
		List<Jet> jets = new ArrayList<>();
		try (BufferedReader bufIn = new BufferedReader(new FileReader(fileName))) {
			String line;
			while ((line = bufIn.readLine()) != null) {
				String[] jetRecord = line.split(", ");
				String model = jetRecord[0];
				double speed = Double.parseDouble(jetRecord[1]);
				int range = Integer.parseInt(jetRecord[2]);
				long price = Long.parseLong(jetRecord[3]);
				String type = jetRecord[4];
				if (type.equals("Standard")) {
					Jet j = new JetImpl(model, speed, range, price);
//					System.out.println(j);
					jets.add(j);
				}
				if (type.equals("Fighter")) {
					Jet j = new FighterJet(model, speed, range, price);
//					System.out.println(j);
					jets.add(j);
				}
				if (type.equals("Cargo")) {
					Jet j = new CargoPlane(model, speed, range, price);
//					System.out.println(j);
					jets.add(j);
				}
			}
		} catch (IOException e) {
			System.err.println(e);
		}
		return jets;
	}

	public void listFleet(List<Jet> jets) {
		for (Jet jet : jets) {
			System.out.println(jet);
		}
		System.out.println();
	}
	
	public void flyJets() {
		
	}
	
	public void viewFastest() {
		
	}
	
	public void viewLongestRange() {

	}
	
	public void loadCargo() {
		
	}
	
	public void dogfight() {
		
	}
	
	public void addToFleet() {
		
	}
	
	public void removeFromFleet() {
		
	}
}
