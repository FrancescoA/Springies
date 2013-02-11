package simulation.wallforce;

import simulation.Mass;

/**
 * Implements left force as part of wall force class. For details on
 * methods, see WallForce class.
 * @author francescoagosti
 *
 */
public class LeftForce extends WallForce {
	private static LeftForce myInstance;
	 
	public LeftForce(Double magnitude, Double exponent){
		super(magnitude,exponent);
		myAngle = 180.0;
	}
	
	public static synchronized WallForce getInstance(){
		return getInstance(MAGNITUDE,EXPONENT);
	}
	
	public static synchronized WallForce getInstance(Double magnitude, Double exponent){
		if(myInstance == null){
			myInstance = new LeftForce(magnitude, exponent);
		}
		return myInstance;
	}
	
	@Override
	protected double calculateDistance(Mass m){
		return myRightWallPosition - m.getX();
	}
	
	
}
