
import java.util.*;

import tamago.*;

public class Receiver extends BasicComponent
    implements ReceiverService, 
	       RequireEngineService,
	       RequireDirectionService {

    // Required services
    protected EngineService engine;
    protected DirectionService direction;

    // Interface fonctionnelle
    public Receiver() {
        engine = null;
        direction = null;
    }
    
    public void receive(Command command) {
	// XXX: should be done by a LogService
	System.err.println("[RECEIVER] : received Command : " + command);

        if(command.isForward()) {
            engine.forward();
	}

        if(command.isBackward()) {
            engine.backward();
	}

        if(command.isLeft()) {
            direction.goLeft();
	}

        if(command.isRight()) {
            direction.goRight();
	}
    }


    // Laison des fournisseurs
    public void bind(EngineService engine) throws ServiceBindException {
        this.engine = engine;
    }

    public void bind(DirectionService direction) throws ServiceBindException {
	this.direction = direction;
    }
}
