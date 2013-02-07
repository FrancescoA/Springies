package simulation.wallforce;

import simulation.Mass;

public class RightForce extends WallForce {
	
	private static RightForce myInstance;
	
	public RightForce(Double magnitude, Double exponent) {
		super(magnitude, exponent);
		myAngle = 0.0;
	}
	
	public static synchronized WallForce getInstance(){
		return getInstance(MAGNITUDE,EXPONENT);
	}

	private static synchronized WallForce getInstance(Double magnitude, Double exponent) {
		if(myInstance == null){
			myInstance = new RightForce(magnitude, exponent);
		}
		return myInstance;
	}

	@Override
	protected double calculateDistance(Mass m){
		return m.getX();
	}
	
}
