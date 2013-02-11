package src.simulation;

import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.util.List;
import java.util.ArrayList;

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

    private static final int NEW_ASSEMBLY = KeyEvent.VK_N;
    private static final int CLEAR_ASSEMBLY = KeyEvent.VK_C;
    // simulation state
    private List<Assembly> myAssemblies;
    private MouseDragger myMouseDragger;
    private KeyManager myKeyManager;
 
    private List<EnvironmentalForce> myEnvironmentalForces;
    

    /**
     * Create a game of the given size with the given display for its shapes.
     */
    public Model (Canvas canvas) {
        myView = canvas;
        myAssemblies = new ArrayList<Assembly>();
        myEnvironmentalForces = new ArrayList<EnvironmentalForce>();
        myKeyManager = new KeyManager(canvas);
        
    }

    /**
     * Draw all elements of the simulation.
     */
    public void paint(Graphics2D pen) {
        for (Assembly a : myAssemblies) {
        	a.paint(pen);
        }
        if (myMouseDragger != null) {
			myMouseDragger.paint(pen);
        }
    }

    /**
     * Update simulation for this moment, given the time since the last moment.
     */
    public void update (double elapsedTime) {
        checkAssemblies();
    	myKeyManager.update();
        Dimension bounds = myView.getSize();
        for (Assembly a : myAssemblies) {
            a.update(elapsedTime, bounds);
            if(myEnvironmentalForces != null){
            	for(EnvironmentalForce e : myEnvironmentalForces){
            		if(e.getStatus()){
            			e.update(myView);
            			e.Apply(a.getMasses());
            		}
            	}
            	}
        }
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
    
 
    /**
     * Checks user inputs as to whether they want to add
     * or clear assemblies
     */
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
    /**
     * Drags the mass closest to the mouse click and deletes
     * the MouseDragger the mouse is not currents being pressed
     */
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
	
	public Dimension getDimension(){
		return myView.getSize();
	}
	
}


