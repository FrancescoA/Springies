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
	private static double LEFT = 180.0;
	 
	public LeftForce(Double magnitude, Double exponent){
		super(magnitude,exponent);
		setAngle(LEFT);
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
		return super.getRightWall() - m.getX();
	}
	
	
}
