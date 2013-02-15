package src.simulation;

import java.util.List;

import util.Vector;

/**
 * Implements the viscosity environmental force on an array of masses.
 * 
 * @author Francesco Agosti
 * 
 */
public class Viscosity extends EnvironmentalForce {
	private static final Double MAGNITUDE = .025;

	double myMagnitude;
	private static Viscosity myInstance;

	private Viscosity(Double magnitude) {
		super();
		myMagnitude = magnitude;
	}

	/**
	 * Implements singleton design principle to ensure that only one instance of
	 * this class is made. If called without parameters, default values are
	 * used.
	 * 
	 */
	public static synchronized Viscosity getInstance() {
		return getInstance(MAGNITUDE);
	}

	public static synchronized Viscosity getInstance(Double magnitude) {
		if (myInstance == null) {
			myInstance = new Viscosity(magnitude);
		}
		return myInstance;
	}

	@Override
	/**
	 * Cycles through a list of Masses, applying the constructed viscosity to each 
	 * of these masses
	 */
	public void Apply(List<Mass> Masses) {
		for (Mass m : Masses) {
			double angle = m.getVelocity().getDirection() - 180;
			double magnitude = m.getVelocity().getMagnitude() * myMagnitude;
			Vector vForce = new Vector(angle, magnitude);
			m.applyForce(vForce);
		}

	}

}
