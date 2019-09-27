package com.skilldistillery.jetsapp;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class JetsApplication {

	private AirField airField;
	Scanner kb = new Scanner(System.in);

	public static void main(String[] args) {
		JetsApplication ja = new JetsApplication();
		String fileName = "jets.txt";
		List<Jet> jets = ja.readJets(fileName);
		System.out.println(jets);
//		ja.launch();
	}

	public void launch() {

	}

	public void displayUserMenu() {

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
				if(type.equals("Standard")) {
					Jet j = new JetImpl(model, speed, range, price);
					System.out.println(j);
					jets.add(j);
				}
				if(type.equals("Fighter")) {
					Jet j = new FighterJet(model, speed, range, price);
					System.out.println(j);
					jets.add(j);
				}
				if(type.equals("Cargo")) {
					Jet j = new CargoPlane(model, speed, range, price);
					System.out.println(j);
					jets.add(j);
				}
			}
		} catch (IOException e) {
			System.err.println(e);
		}
		return jets;
	}

}
