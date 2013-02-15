package simulation.wallforce;

import simulation.Mass;

/**
 * Implements right force as part of wall force class. For details on
 * methods, see WallForce class.
 * @author Francesco Agosti
 *
 */
public class RightForce extends WallForce {
	
	private static RightForce myInstance;
	private static double RIGHT = 0.0;
	
	public RightForce(Double magnitude, Double exponent) {
		super(magnitude, exponent);
		setAngle(RIGHT);
	}
	
	public static synchronized WallForce getInstance(){
		return getInstance(MAGNITUDE,EXPONENT);
	}

	public static synchronized WallForce getInstance(Double magnitude, Double exponent) {
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
