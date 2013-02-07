package src.simulation;

import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.util.List;
import java.util.ArrayList;

import util.Vector;
import util.Location;
import view.Canvas;


/**
 * XXX.
 * @author Robert C. Duvall
 * @modified by Jack Matteucci
 * @modified by Francesco Agosti
 */
public class Model {
    // bounds and input for game
    private Canvas myView;
    // simulation state
    private List<Assembly> myAssemblies;
    private MouseDragger myMouseDragger;
 
    private List<EnvironmentalForce> myEnvironmentalForces;
    
	private static final int NEW_ASSEMBLY= KeyEvent.VK_N;
	private static final int CLEAR_ASSEMBLY= KeyEvent.VK_C;
	private boolean checked;

    /**
     * Create a game of the given size with the given display for its shapes.
     */
    public Model (Canvas canvas) {
        myView = canvas;
        myAssemblies = new ArrayList<Assembly>();
        myEnvironmentalForces = new ArrayList<EnvironmentalForce>();
        checked = false;
    }

    /**
     * Draw all elements of the simulation.
     */
    public void paint (Graphics2D pen) {
        for (Assembly a : myAssemblies) {
        	a.paint(pen);
        }
        if(myMouseDragger != null){
			myMouseDragger.paint(pen);
        }
    }

    /**
     * Update simulation for this moment, given the time since the last moment.
     */
    public void update (double elapsedTime) {
        Dimension bounds = myView.getSize();
        for (Assembly a : myAssemblies) {
            a.update(elapsedTime, bounds);
            if(myEnvironmentalForces != null){
            	for(EnvironmentalForce e : myEnvironmentalForces){
            		e.Apply(a.getMasses());
            	}
            	}
        }
        checkAssemblies();
        dragMass(elapsedTime, bounds);
        }
 
    /**
     * Add given Assembly to this simulation.
     */
    public void add (Assembly assembly) {
        myAssemblies.add(assembly);
    }
    /**
     * Add given Environmental Force to this simulation.
     */
    public void add (EnvironmentalForce force) {
    	myEnvironmentalForces.add(force);
    }
    
	private void checkAssemblies(){
		if(myView.getLastKeyPressed() == NEW_ASSEMBLY){
			myView.loadAssemblyFile();
			myView.resetLastKeyPressed();
		}
		else if(myView.getLastKeyPressed() == CLEAR_ASSEMBLY){
			myAssemblies.clear();
			myView.resetLastKeyPressed();
		}
	}
	
	private void dragMass(double elapsedTime, Dimension bounds){
		if(myView.getLastMousePosition() != null){
			if(myMouseDragger== null) myMouseDragger = new MouseDragger(myView, myAssemblies);
			else{
				myMouseDragger.update(elapsedTime, bounds);
			}
		}
		else{
			if(myMouseDragger!=null) myMouseDragger = null;
		}
	}
	
}


