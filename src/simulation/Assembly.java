package src.simulation;

import java.awt.Dimension;
import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.List;

public class Assembly {
    private List<Mass> myMasses;
    private List<Spring> mySprings;

    /**
     * Creates an Assembly which keeps track of a set of Masses and Springs.
     */
	public Assembly() {
        myMasses = new ArrayList<Mass>();
        mySprings = new ArrayList<Spring>();
	}
    /**
     * Updates all the masses and springs in the Assembly
     */
    public void update (double elapsedTime, Dimension bounds) {
        for (Spring s : mySprings) {
            s.update(elapsedTime, bounds);
        }
        for (Mass m : myMasses) {
            m.update(elapsedTime, bounds);
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
     * returns list of masses
     */
    public List<Mass> getMasses(){
    	return myMasses;
    }
    /**
     * returns list of springs
     */
    public List<Spring> getSprings(){
    	return mySprings;
    }
    /**
     * Paints all masses and springs in assembly
     */
    public void paint(Graphics2D pen){
    for (Spring s : mySprings) {
        s.paint(pen);
    }
    for (Mass m : myMasses) {
        m.paint(pen);
    }
    }

}
