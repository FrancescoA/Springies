package simulation;

import java.util.List;
import util.Location;
import util.Vector;

public class CenterOfMass extends EnvironmentalForce {

	private double myMagnitude;
	private double myExponent;
	private Location myCenterOfMass;
	
	
	
	public CenterOfMass(double magnitude, double exponent) {
		myMagnitude = magnitude;
		myExponent = exponent;
	}

	@Override
	public void Apply(List<Mass> Masses) {
		updateCenterOfMass(Masses);
		for(Mass m : Masses){
			m.applyForce(new Vector(getForceAngle(m),getForceMagnitude(m)));
		}
	}
	
	private double getForceMagnitude(Mass mass){
		return Math.pow(distance(mass), -myExponent)*Math.abs(myMagnitude);
	}
	
	private double getForceAngle(Mass mass){
		Double angle = Vector.angleBetween(myCenterOfMass.getX() - mass.getX(), myCenterOfMass.getY() - mass.getY());
		if(myMagnitude<0){
			return angle + 180;
		}
		return angle;
	}
	
	private double distance(Mass mass){
		return new Location(mass.getX(), mass.getY()).distance(myCenterOfMass);
	}
	
	private void updateCenterOfMass(List<Mass> Masses) {
		double XCenterOfMass = 0;
		double YCenterOfMass = 0;
		double TotalMass = 0;
		for(Mass m : Masses){
			TotalMass += m.getMass();
			XCenterOfMass += m.getMass()*m.getX();
			YCenterOfMass += m.getMass()*m.getY();
		}
		XCenterOfMass /= TotalMass;
		YCenterOfMass /= TotalMass;
		myCenterOfMass = new Location(XCenterOfMass,YCenterOfMass);
	}

}
