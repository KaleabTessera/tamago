
import java.util.*;

import tamago.*;

public class Receiver extends BasicComponent
    implements ReceiverService, 
	       RequireEngineService,
	       RequireDirectionService,
	       IntrospectionController,
	       ReflectionController,
               CarLifeCycleController {

    // Required services
    private EngineService engine;
    private DirectionService direction;

    // Attributes
    private CarLifeCycleDelegate lc_check;

    // Interface fonctionnelle
    public Receiver() {
        engine = null;
        direction = null;
	lc_check = new CarLifeCycleDelegate();
    }
    
    public void receive(Command command) {
	if(!lc_check.checkStarted())
	    return;
	
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
	if(lc_check.checkStarted())
	    throw new ServiceBindException("Cannot configure while running");

        this.engine = engine;
    }

    public void bind(DirectionService direction) throws ServiceBindException {
	if(lc_check.checkStarted())
	    throw new ServiceBindException("Cannot configure while running");

	this.direction = direction;
    }
    
    
    // Cycle de vie
    
    // Propriétés

    // Implantation de l'interface IntrospectionController
    public ComponentType getComponentType() {
        return new ComponentTypeDescriptor(this.getClass().getName(),this.getClass());
    }

    
    public ServiceType[] getProvidedServiceTypes() {
        ServiceType[] ret = new ServiceType[1];
        ret[0] = new ServiceTypeDescriptor("ReceiverService", ReceiverService.class);
        return ret;
    }
            
    public ServiceType[] getRequiredServiceTypes() {
        ServiceType[] ret = new ServiceType[2];
        ret[0] = new ServiceTypeDescriptor("EngineService", EngineService.class);
        ret[1] = new ServiceTypeDescriptor("DirectionService", DirectionService.class);
        return ret;
    }    
    
    public ServiceType getProvidedServiceType(String from_name) {
        if(from_name.equals("ReceiverService"))
            return new ServiceTypeDescriptor("ReceiverService", ReceiverService.class);
        else 
            return null;
    }
    
    public ServiceType getRequiredServiceType(String from_name) {
        if(from_name.equals("EngineService"))
            return new ServiceTypeDescriptor("EngineService", EngineService.class);
        else if(from_name.equals("DirectionService"))
            return new ServiceTypeDescriptor("DirectionService", DirectionService.class);        
        else 
            return null;        
    }
    
    // implantation de l'interface ReflectionController
    public Object[] getProviders(ServiceType service) {
        Object[] ret = null;
        ArrayList list = new ArrayList();
        if(service.isProvidedBy(engine))
            list.add(engine);
        else if(service.isProvidedBy(direction))
            list.add(direction);
        if(list.size()==0)
            return null;
        ret = new Object[list.size()];
        for(int i=0;i<list.size();i++) {
            ret[i] = list.get(i);
        }
        return ret;
    }

    // implantation de l'interface CarLifeCycleController
    public void initialize() throws LifeCycleException {
	lc_check.initialize();
    }

    // LCConfigureControler interface
    public void configure() throws LifeCycleException {
	lc_check.configure();
    }

    // LCStartStopController interface
    public void start()  throws LifeCycleException {
	lc_check.start();
    }

    public void stop()  throws LifeCycleException {
	lc_check.stop();
    }

}
