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
	private static double UP = 270.0;
	
	public UpForce(Double magnitude, Double exponent){
		super(magnitude,exponent);
		super.setAngle(UP);
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
		return super.getLowerWall() - m.getY();
	}
}
