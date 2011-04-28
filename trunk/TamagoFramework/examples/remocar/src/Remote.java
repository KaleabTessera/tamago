
/*******************************************
 * Copyright (C) 2005, Frederic Peschanski *
 *         >> DO NOT DISTRIBUTE <<         *
 *******************************************/

import java.util.*;

import tamago.*;

public class Remote extends BasicComponent
    implements RemoteService, 
	       RequireReceiverService {

    // Required services
    private ReceiverService receiver;

    // Attributes
    private int next_command;

    // Interface fonctionnelle
    public Remote() {
        receiver = null;
	next_command = Command.RESET;
    }
    
    public void on() {
	System.err.println("[REMOTE] : goes on()");
    }

    public void off() {
	System.err.println("[REMOTE] : goes off()");
    }

    public void forward() {
	System.err.println("[REMOTE] : select forward()");
	next_command |= Command.FORWARD;
    }

    public void backward() {
	System.err.println("[REMOTE] : select backward()");
	next_command |= Command.BACKWARD;
    }

    public void left() {
	System.err.println("[REMOTE] : select left()");
	next_command |= Command.LEFT;
    }

    public void right() {
	System.err.println("[REMOTE] : select right()");
	next_command |= Command.RIGHT;
    }

    public void reset() {
	System.err.println("[REMOTE] : select reset()");
	next_command = Command.RESET;
    }

    public void activate() {
	System.err.println("[REMOTE] : activated");
	receiver.receive(new Command(next_command));	
    }

    // Laison des fournisseurs
    public void bind(ReceiverService receiver) throws ServiceBindException {
        this.receiver = receiver;
    }
    
}
