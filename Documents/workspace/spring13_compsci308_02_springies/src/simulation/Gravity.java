package simulation;

import java.util.List;
import java.awt.Dimension;
import util.Vector;

public class Gravity extends EnvironmentalForce{
    private Vector myAcceleration;
    
	public Gravity(Double direction, Double magnitude) {
		super();
		myAcceleration = new Vector(direction, magnitude);
	}
	
	@Override
	public void Apply(List<Mass> Masses){
		for(Mass m : Masses){
			m.applyForce(myAcceleration);
		}
	}
	public Vector getAcceleration(){
		return myAcceleration;
	}
	public void scaleMagnitude(double change){
		myAcceleration.scale(change);
	}
	public void setAngle(double angle){
		myAcceleration = new Vector(angle, myAcceleration.getMagnitude());
	}
}
