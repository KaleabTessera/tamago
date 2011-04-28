/*******************************************
 * Copyright (C) 2005, Frederic Peschanski *
 *         >> DO NOT DISTRIBUTE <<         *
 *******************************************/

import tamago.*;

import java.util.*;

public class TestCarCompositionMain {

    public static void main(String args[]) {
	
	try {

	    // Instantiate the remote controller
	    Remote remote = new Remote();
	    
	    // Instantiate the car parts
	    Receiver receiver = new Receiver();
	    Tamago.Initialize(receiver);
	    Engine engine = new Engine();
	    Tamago.Initialize(engine);
	    Direction direction = new Direction();
	    Tamago.Initialize(direction);
	    Effector effector = new Effector();
	    Tamago.Initialize(effector);

	    /* Bindings of car parts   // Generic Bind interface does not work (sadly)
	    Tamago.Bind( receiver, (EngineService) engine);
	    Tamago.Bind( receiver, (DirectionService) direction);
	    Tamago.Bind( engine, (EffectorEngineService) effector);
	    Tamago.Bind( direction, (EffectorDirectionService) effector);
	    */

	    // Bindings of car parts   
	    receiver.bind( (EngineService) engine);
	    receiver.bind( (DirectionService) direction);
	    engine.bind( (EffectorEngineService) effector);
	    direction.bind( (EffectorDirectionService) effector);

	    
	    // Start the car parts
	    Tamago.Start(receiver);
	    Tamago.Start(engine);
	    Tamago.Start(direction);
	    Tamago.Start(effector);

	    // Bindings for remote
	    remote.bind( (ReceiverService) receiver);

	    // Start the remote
	    remote.on();

	    // Test the car
	    Random rand = new Random();
	    for(int i = 1; i<= 10 ; i++) {
		int cmd = rand.nextInt(70);
		if(cmd<10) {
		    remote.reset();
		    remote.forward();
		} else if(cmd<20) {
		    remote.reset();
		    remote.forward();
		    remote.left();
		}  else if(cmd<30) {
		    remote.reset();
		    remote.forward();
		    remote.right();
		} else if(cmd<40) {
		    remote.reset();
		    remote.backward();
		} else if(cmd<50) {
		    remote.reset();
		    remote.backward();
		    remote.left();
		}  else if(cmd<60) {
		    remote.reset();
		    remote.backward();
		    remote.right();
		} else if(cmd<70) {
		    remote.reset();
		}
		System.out.println(">>>> Remote Activation #"+i);
		Tamago.Activate(remote);
		Tamago.DeActivate(remote);
	    }

	    // shutdown the remote controller
	    remote.off();

	    // Stopping car parts
	    Tamago.Stop( receiver );
	    Tamago.Stop( engine );
	    Tamago.Stop( direction );
	    Tamago.Stop( effector );

	} catch(Exception e) {
	    e.printStackTrace(System.err);
	}

    }
}
