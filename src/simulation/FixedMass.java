package simulation;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics2D;

import util.Location;
import util.Vector;

public class FixedMass extends Mass {

	public FixedMass(double x, double y, double mass) {
		super(x, y, mass);
	}

    @Override
    public void update (double elapsedTime, Dimension bounds) {
        super.update(elapsedTime, bounds);
    }

    /**
     * XXX.
     */
    @Override
    public void paint (Graphics2D pen) {
    	super.paint(pen);
    }

    /**
     * Use the given force to change this mass's acceleration.
     */
    public void applyForce (Vector force) {
    }

    /**
     * Convenience method.
     */
    public double distance (Mass other) {
    	return super.distance(other);
    }
	

}
