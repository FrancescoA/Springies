package simulation.wallforce;

import simulation.Mass;



public class DownForce extends WallForce {
	private static DownForce myInstance;
	
	public DownForce(Double magnitude, Double exponent) {
		super(magnitude, exponent);	
		myAngle = 90.0;
	}
	
	public static synchronized WallForce getInstance(){
		return getInstance(MAGNITUDE, EXPONENT);
	}
	
	public static synchronized WallForce getInstance(Double magnitude, Double exponent){
		if(myInstance == null){
			myInstance = new DownForce(magnitude, exponent);
		}
		return myInstance;
	}
	
	@Override
	protected double calculateDistance(Mass m){
		return m.getY();
	}
	

	


}
