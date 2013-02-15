package src.simulation;

import java.util.List;
import util.Vector;

/**
 * XXX.
 * 
 * @author Jack Matteucci
 * @modified Francesco Agosti
 */

public class Gravity extends EnvironmentalForce {
	private static final Double DIRECTION = 90.0;
	private static final Double MAGNITUDE = 2.0;

	private Vector myAcceleration;
	private static Gravity myInstance;

	/**
	 * Create a gravity object with a direction and magnitude
	 */
	private Gravity(Double direction, Double magnitude) {
		super();
		myAcceleration = new Vector(direction, magnitude);
	}

	/**
	 * Implements singleton design principle to ensure that only one instance of
	 * this class is made. If called without parameters, default values are
	 * used.
	 * 
	 */
	public static synchronized Gravity getInstance() {
		return getInstance(DIRECTION, MAGNITUDE);
	}

	public static synchronized Gravity getInstance(Double direction,
			Double magnitude) {
		if (myInstance == null) {
			myInstance = new Gravity(direction, magnitude);
		}
		return myInstance;

	}

	@Override
	/**
	 * Cycles through a list of Masses, applying the constructed gravity to each 
	 * of these masses
	 */
	public void Apply(List<Mass> Masses) {
		for (Mass m : Masses) {
			m.applyForce(myAcceleration);
		}
	}

	/**
	 * returns the Gravity object's acceleration constant
	 */
	public Vector getAcceleration() {
		return myAcceleration;
	}

}
