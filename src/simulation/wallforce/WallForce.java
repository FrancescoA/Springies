package simulation.wallforce;

import java.util.List;

import simulation.EnvironmentalForce;
import simulation.Mass;
import util.Vector;

/**
 * Implements the wall environmental force on an array of masses.  
 * 
 * 
 * @author Francesco Agosti
 *
 */
public abstract class WallForce extends EnvironmentalForce {
	protected static final Double MAGNITUDE = 10.0;
	protected static final Double EXPONENT = 0.0;
	
	protected double myMagnitude;
	protected double myExponent;
	protected double myAngle;

	
	public WallForce(double magnitude, double exponent){
		super();
		myMagnitude = magnitude;
		myExponent = exponent;
	}
	
	

	//applies force to all masses
	@Override
	public void Apply(List<Mass> Masses) {
		for(Mass m : Masses){
			double distance = calculateDistance(m);
			double magnitude = myMagnitude*(Math.pow(distance, -myExponent));
			Vector force = new Vector(myAngle, magnitude);
			m.applyForce(force);
		}
		
		

	}
	//determine distance from wall
	protected double calculateDistance(Mass m) {
		return 0;
		
	}
	
	//determine what wall to repulse from using the wallID
	private double calculateAngle(int wallID) {
		if(wallID==1){
			return 90.0;
		}
		if(wallID==2){
			return 180.0;
		}
		if(wallID==3){
			return 270.0;
		}
		if(wallID==4){
			return 0.0;
		}
		System.out.print("invalid value");
		return 0.0;
	}
	

}
