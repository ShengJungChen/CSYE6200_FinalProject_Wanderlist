package model.Trip;

public class See extends Item {
	private String trafficMean;

	// constructor
	public See(Type type, Trip trip, String itemName) {
		super(type, trip, itemName);
	}

	// getter and setter
	public String getTrafficMean() {
		return trafficMean;
	}

	public void setTrafficMean(String trafficMean) {
		this.trafficMean = trafficMean;
	}

}
