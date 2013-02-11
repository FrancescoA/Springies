package simulation.wallforce;

import simulation.Mass;

/**
 * Implements up force as part of wall force class. For details on
 * methods, see WallForce class.
 * @author Francesco Agosti
 *
 */
public class UpForce extends WallForce {
	private static UpForce myInstance;
	
	public UpForce(Double magnitude, Double exponent){
		super(magnitude,exponent);
		myAngle = 270.0;
	}
	
	public static synchronized WallForce getInstance(){
		return getInstance(MAGNITUDE,EXPONENT);
	}
	
	public static synchronized WallForce getInstance(Double magnitude, Double exponent){
		if(myInstance == null){
			myInstance = new UpForce(magnitude, exponent);
		}
		return myInstance;
	}
	
	@Override
	protected double calculateDistance(Mass m){
		return myLowerWallPosition - m.getY();
	}
}
