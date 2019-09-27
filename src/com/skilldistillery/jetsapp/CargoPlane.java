package com.skilldistillery.jetsapp;

public class CargoPlane extends Jet implements CargoCarrier {

	// F i e l d s

	// C o n s t r u c t o r s

	public CargoPlane() {
	}

	public CargoPlane(String model, double speed, int range, long price) {
		super(model, speed, range, price);
	}

	// M e t h o d s

	@Override
	public void fly() {
		System.out.println(getModel() + " : I'm flyin'!");
	}

	@Override
	public double getSpeedInMach() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int hashCode() {
		return super.hashCode();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		return true;
	}

	@Override
	public void loadCargo() {
		System.out.println("Loading cargo");
	}

}
