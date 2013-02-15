package src.simulation;

import java.awt.event.KeyEvent;

import simulation.wallforce.DownForce;
import simulation.wallforce.LeftForce;
import simulation.wallforce.RightForce;
import simulation.wallforce.UpForce;
import view.Canvas;

/**
 * Manages various keys for the model (makes it more organized), and deals with
 * most of the user interaction.
 * 
 * @author Francesco Agosti
 * 
 */
public class KeyManager {

	private static final int TOGGLE_VISCOSITY = KeyEvent.VK_V;
	private static final int TOGGLE_GRAVITY = KeyEvent.VK_G;
	private static final int TOGGLE_COM = KeyEvent.VK_M;
	private static final int TOGGLE_ONE = KeyEvent.VK_1;
	private static final int TOGGLE_TWO = KeyEvent.VK_2;
	private static final int TOGGLE_THREE = KeyEvent.VK_3;
	private static final int TOGGLE_FOUR = KeyEvent.VK_4;
	private static final int INCREASE_WINDOW = KeyEvent.VK_UP;
	private static final int DECREASE_WINDOW = KeyEvent.VK_DOWN;

	private Canvas myView;

	public KeyManager(Canvas canvas) {
		myView = canvas;
	}

	/**
	 * Check for what keys are pressed and update the view and forces
	 * accordingly.
	 */
	public void update() {
		updateBounds();
		updateSwitches();

	}

	/**
	 * Updates the bounds of the view in response to user input (UP and DOWN
	 * arrow keys)
	 */
	public void updateBounds() {
		int lastKey = myView.getLastKeyPressed();
		int x = myView.getWidth();
		int y = myView.getHeight();
		if (lastKey == DECREASE_WINDOW) {
			myView.setBounds(0, 0, x - 10, y - 10);
		}
		if (lastKey == INCREASE_WINDOW) {
			myView.setBounds(0, 0, x + 10, y + 10);
		}
	}

	/**
	 * Toggles EnvironmentalForces on and off depending on user input
	 * (v,m,g,1,2,3,4)
	 */
	public void updateSwitches() {
		int lastKeyPressed = myView.getLastKeyPressed();

		if (lastKeyPressed == TOGGLE_VISCOSITY) {
			Viscosity.getInstance().toggle();
		} else if (lastKeyPressed == TOGGLE_GRAVITY) {
			Gravity.getInstance().toggle();
		} else if (lastKeyPressed == TOGGLE_COM) {
			CenterOfMass.getInstance().toggle();
		} else if (lastKeyPressed == TOGGLE_ONE) {
			DownForce.getInstance().toggle();
		} else if (lastKeyPressed == TOGGLE_TWO) {
			LeftForce.getInstance().toggle();
		} else if (lastKeyPressed == TOGGLE_THREE) {
			UpForce.getInstance().toggle();
		} else if (lastKeyPressed == TOGGLE_FOUR) {
			RightForce.getInstance().toggle();
		}

		myView.resetLastKeyPressed();

	}

}
