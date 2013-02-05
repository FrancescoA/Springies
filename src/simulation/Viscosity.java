package simulation;

import java.awt.Dimension;
import java.util.List;

import util.Vector;

/**
 * Implements the viscocity environmental force on an array of masses.  
 * 
 * 
 * @author Francesco Agosti
 *
 */
public class Viscosity extends EnvironmentalForce{
	
	double myMagnitude;

	public Viscosity (Double magnitude){
		super();
		myMagnitude = magnitude;	
	}
	
	public void update (double elapsedTime, Dimension bounds){
		
		
	}

	@Override
	public void Apply(List<Mass> Masses) {
		for(Mass m: Masses){
			double angle = m.getVelocity().getDirection() - 180;
			double magnitude = m.getVelocity().getMagnitude()*myMagnitude;
			Vector vForce = new Vector(angle, magnitude);
			m.applyForce(vForce);
		}
		
	}
	
	
}
