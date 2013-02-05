package simulation;

import java.awt.Dimension;
import java.awt.Graphics2D;
import java.util.List;
import java.util.ArrayList;
import view.Canvas;


/**
 * XXX.
 * 
 * @author Robert C. Duvall
 * @modified by Jack Matteucci
 * @modified by Francesco Agosti
 */
public class Model {
    // bounds and input for game
    private Canvas myView;
    // simulation state
    private List<Mass> myMasses;
    private List<Spring> mySprings;
    private List<EnvironmentalForce> myEnvironmentalForces;

    /**
     * Create a game of the given size with the given display for its shapes.
     */
    public Model (Canvas canvas) {
        myView = canvas;
        myMasses = new ArrayList<Mass>();
        mySprings = new ArrayList<Spring>();
        myEnvironmentalForces = new ArrayList<EnvironmentalForce>();
    }

    /**
     * Draw all elements of the simulation.
     */
    public void paint (Graphics2D pen) {
        for (Spring s : mySprings) {
            s.paint(pen);
        }
        for (Mass m : myMasses) {
            m.paint(pen);
        }
    }

    /**
     * Update simulation for this moment, given the time since the last moment.
     */
    public void update (double elapsedTime) {
        Dimension bounds = myView.getSize();
        for (Spring s : mySprings) {
            s.update(elapsedTime, bounds);
        }
        for (Mass m : myMasses) {
            m.update(elapsedTime, bounds);
        }
        if(myEnvironmentalForces != null){
    	for(EnvironmentalForce e : myEnvironmentalForces){
    		e.Apply(myMasses);
    	}
    	}
        }
 
        
    

    /**
     * Add given mass to this simulation.
     */
    public void add (Mass mass) {
        myMasses.add(mass);
    }

    /**
     * Add given spring to this simulation.
     */
    public void add (Spring spring) {
        mySprings.add(spring);
    }
    /**
     * Add given Environmental Force to this simulation.
     */
    public void add (EnvironmentalForce force) {
    	myEnvironmentalForces.add(force);
    }

}
