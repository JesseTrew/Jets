package com.skilldistillery.jetsapp;

import java.util.List;

public class Airfield {

	// F i e l d s
	
	private List<Jet> jets;
	
	// C o n s t r u c t o r s
	
	public Airfield() {
	}
	
	public Airfield(List<Jet> jets) {
		this.jets = jets;
	}

	// M e t h o d s
	
	public List<Jet> getJets() {
		return jets;
	}
	
	public void setJets(List<Jet> jets) {
		this.jets = jets;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((jets == null) ? 0 : jets.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Airfield other = (Airfield) obj;
		if (jets == null) {
			if (other.jets != null)
				return false;
		} else if (!jets.equals(other.jets))
			return false;
		return true;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("AirField [jets=").append(jets).append("]");
		return builder.toString();
	}

}
