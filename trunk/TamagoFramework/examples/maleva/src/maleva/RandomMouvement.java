
package maleva;

import tamago.*;

import java.util.*;

public class RandomMouvement extends ComportementAgent {
	
	/* fields */
	private Random rand;
	
	/* properties */
	private int angle_step = 10;
	
	public RandomMouvement(Agent outer) {
		super(outer);
		rand = new Random();
	}
	
	/* LCStepperController */
	
	public boolean step() throws LifeCycleException {
		
		// System.out.println("Random mouvement for "+outer.getID());
		int signe = rand.nextInt(100);
		if(signe<50)
			action = new Action(Action.MOVE,outer.getAngle()-rand.nextInt(angle_step));
		else
			action = new Action(Action.MOVE,outer.getAngle()+rand.nextInt(angle_step));
		
		return true;
	}
}
