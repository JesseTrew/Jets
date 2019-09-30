package com.skilldistillery.jetsapp;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class JetsApplication {
	boolean menu = true;
	List<Jet> jets = new ArrayList<>();
	private Airfield airfield = new Airfield();
	Scanner kb = new Scanner(System.in);

	
	public static void main(String[] args) {
		JetsApplication ja = new JetsApplication();
		ja.launch();
	}

	public void launch() {
		String fileName = "jets.txt";
		jets = readJets(fileName);
		airfield = new Airfield(jets);
		do {
			displayUserMenu();
			menuSelection(kb);
		} while(menu);
		kb.close();
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
			kb.nextLine();
			listFleet(jets);
		} 
		if (kb.nextInt() == 2) {
			kb.nextLine();
			flyJets(jets);
		} 
		if (kb.nextInt() == 3) {
			viewFastest();
		}
		if(kb.nextInt() == 4) {
			viewLongestRange();
		}
		if(kb.nextInt() == 5) {
			loadCargo();
		}
		if(kb.nextInt() == 6) {
			dogfight();
		}
		if(kb.nextInt() == 7) {
			addToFleet();
		}
		if(kb.nextInt() == 8) {
			removeFromFleet();
		}
		if(kb.nextInt() == 9) {
			System.out.println("Goodbye.");
			return false;
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
	
	public void flyJets(List<Jet> jets) {
		for (Jet jet : jets) {
			jet.fly();
		}
		System.out.println();
	}
	
	public void viewFastest() {
		double fastestSpeed = 0;
		int fastestIndex = 0;
		int counter = 0;
		
		for (Jet jet : jets) {
			if (jet.getSpeed() > fastestSpeed) {
				fastestSpeed = jet.getSpeed();
				fastestIndex = counter;
			}
			counter++;
		}
		System.out.println(jets.get(fastestIndex));
		System.out.println();
	}
	
	public void viewLongestRange() {
		double longestRange = 0;
		int longestIndex = 0;
		int counter = 0;
		
		for (Jet jet : jets) {
			if (jet.getRange() > longestRange) {
				longestRange = jet.getRange();
				longestIndex = counter;
			}
			counter++;
		}
		System.out.println(jets.get(longestIndex));
		System.out.println();
	}
	
	public void loadCargo() {
		for (Jet jet : jets) {
			if(jet instanceof CargoPlane) {
				((CargoPlane) jet).loadCargo();	
			}
		}
		System.out.println();
	}
	
	public void dogfight() {
		for (Jet jet : jets) {
			if(jet instanceof FighterJet) {
				((FighterJet) jet).fight();	
			}
		}
		System.out.println();
	}
	
	public void addToFleet() {
		String model;
		double speed;
		int range;
		long price;
		System.out.println("Enter the new jet's model:");
		model = kb.next();
		System.out.println("Enter the speed:");
		speed = kb.nextDouble();
		System.out.println("Enter the range:");
		range = kb.nextInt();
		System.out.println("Enter the price:");
		price = kb.nextLong();
		System.out.println();
		JetImpl jet = new JetImpl(model, speed, range, price);
		jets.add(jet);
		airfield.setJets(jets);
	}
	
	public void removeFromFleet() {
		System.out.println("Enter the number of the jet you want to remove:");
		int i = 1;
		for (Jet jet : jets) {
			System.out.println(i + ") " + jet);
			i++;
		}

		int selection = kb.nextInt();
		jets.remove(selection - 1);
		airfield.setJets(jets);
	}
}
