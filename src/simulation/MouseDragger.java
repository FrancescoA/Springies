package src.simulation;

import java.awt.Dimension;
import java.awt.Graphics2D;
import java.util.List;

import util.Location;
import view.Canvas;

public class MouseDragger {
	Mass myMass;
	Mass myMouseMass;
	Spring mySpring;
	Canvas myView;
	List<Assembly> myAssemblies;

	/**
	 * Creates a MouseDragger, which keeps track of all the components necessary
	 * for the mousedragging feature.
	 */
	public MouseDragger(Canvas canvas, List<Assembly> assemblies) {
		myView = canvas;
		myAssemblies = assemblies;
		attachMasstoMouse();
	}

	/**
	 * Updates the components of the mouse dragger
	 */
	public void update(double elapsedTime, Dimension bounds) {
		myMouseMass.setCenter(new Location(myView.getMousePosition()));
		myMouseMass.update(elapsedTime, bounds);
		mySpring.update(elapsedTime, bounds);
	}

	/**
	 * Paints the spring and mass created by the mousedragger.
	 */
	public void paint(Graphics2D pen) {
		myMouseMass.paint(pen);
		mySpring.paint(pen);
	}

	/**
	 * When the mouse is clicked, this function creates a spring and mass at the
	 * location of the click.
	 */
	private void attachMasstoMouse() {
		double distance = -1;
		Mass mousepoint = new FixedMass(myView.getMousePosition().getX(),
				myView.getMousePosition().getY(), 0);
		Mass selected = mousepoint;
		for (Assembly a : myAssemblies) {
			for (Mass m : a.getMasses()) {
				if (distance == -1) {
					selected = m;
					distance = Math.abs(mousepoint.distance(m));
				} else {
					if (distance > Math.abs(mousepoint.distance(m))) {
						selected = m;
					}
				}
			}

		}
		myMouseMass = mousepoint;
		myMass = selected;
		mySpring = new Spring(mousepoint, selected, distance, 0.1);
	}

}
