package src.simulation;

import java.util.List;
import util.Location;
import util.Vector;

/**
 * XXX.
 * 
 * @author Jack Matteucci
 * @modified Francesco Agosti
 */
public class CenterOfMass extends EnvironmentalForce {
	private static final Double MAGNITUDE = 50.0;
	private static final Double EXPONENT = 0.0;

	private double myMagnitude;
	private double myExponent;
	private Location myCenterOfMass;
	private static CenterOfMass myInstance;
	private final static double HALFTURN = 180;
	
	/**
	 * Create a CenterOfMass object with a magnitude and an exponent, both of
	 * which will ultimately determine the strength of this force. Negative
	 * magnitudes give a repelling force.
	 */
	public CenterOfMass(double magnitude, double exponent) {
		myMagnitude = magnitude;
		myExponent = exponent;
	}

	/**
	 * Implements singleton design principle to ensure that only one 
	 * instance of this class is made. If called without parameters, 
	 * default values are used.
	 */
	public static synchronized CenterOfMass getInstance() {
		return getInstance(MAGNITUDE, EXPONENT);
	}

	public static synchronized CenterOfMass getInstance(Double magnitude,
			Double exponent) {
		if (myInstance == null) {
			myInstance = new CenterOfMass(magnitude, exponent);
		}
		return myInstance;
	}

	/**
	 * Cycles through a list of Masses, updating where the Center 
	 * of Mass is and applying the specified force to each mass.
	 */
	@Override
	public void Apply(List<Mass> Masses) {
		updateCenterOfMass(Masses);
		for (Mass m : Masses) {
			m.applyForce(new Vector(getForceAngle(m), getForceMagnitude(m)));
		}
	}

	// returns the magnitude of the force that should be applied to a
	// given mass, given the inputs to the CenterOfMass class
	private double getForceMagnitude(Mass mass) {
		return Math.pow(distance(mass), -myExponent) * Math.abs(myMagnitude);
	}

	// returns the direction of the force that should be applied to a
	// given mass, given the inputs to the CenterOfMass class
	private double getForceAngle(Mass mass) {
		Double angle = Vector.angleBetween(myCenterOfMass.getX() - mass.getX(),
				myCenterOfMass.getY() - mass.getY());
		if (myMagnitude < 0) {
			return angle + HALFTURN;
		}
		return angle;
	}

	// returns the distance from a given mass to the center of mass. This
	// helps the getForceMagnitude function
	private double distance(Mass mass) {
		return new Location(mass.getX(), mass.getY()).distance(myCenterOfMass);
	}

	// Updates the location of the center of mass
	private void updateCenterOfMass(List<Mass> Masses) {
		double XCenterOfMass = 0;
		double YCenterOfMass = 0;
		double TotalMass = 0;
		for (Mass m : Masses) {
			TotalMass += m.getMass();
			XCenterOfMass += m.getMass() * m.getX();
			YCenterOfMass += m.getMass() * m.getY();
		}
		XCenterOfMass /= TotalMass;
		YCenterOfMass /= TotalMass;
		myCenterOfMass = new Location(XCenterOfMass, YCenterOfMass);
	}

}
