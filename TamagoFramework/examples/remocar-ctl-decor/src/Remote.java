
/*******************************************
 * Copyright (C) 2005, Frederic Peschanski *
 *         >> DO NOT DISTRIBUTE <<         *
 *******************************************/

import java.util.*;

import tamago.*;

public class Remote extends BasicComponent
    implements RemoteService, 
	       RequireReceiverService,
	       IntrospectionController,
	       ReflectionController,
               RemoteLifeCycleController {

    // Required services
    private ReceiverService receiver;

    // Attributes
    private RemoteLifeCycleDelegate lc_check;
    private int next_command;

    // Interface fonctionnelle
    public Remote() {
        receiver = null;
	next_command = Command.RESET;
	lc_check = new RemoteLifeCycleDelegate();
    }
    
    public void on() {
	System.err.println("[REMOTE] : goes on()");
	try {
	    initialize();
	    start();
	} catch(LifeCycleException e) {
	    System.err.println("Cannot on() : + e ");
	}
    }

    public void off() {
	System.err.println("[REMOTE] : goes off()");
	try {
	    stop();
	} catch(LifeCycleException e) {
	    System.err.println("Cannot off() : "+e.getMessage());
	}
    }

    public void forward() {
	System.err.println("[REMOTE] : select forward()");
	if(!lc_check.checkStarted())
	    return;
	next_command |= Command.FORWARD;
    }

    public void backward() {
	System.err.println("[REMOTE] : select backward()");
	if(!lc_check.checkStarted())
	    return;
	next_command |= Command.BACKWARD;
    }

    public void left() {
	System.err.println("[REMOTE] : select left()");
	if(!lc_check.checkStarted())
	    return;
	next_command |= Command.LEFT;
    }

    public void right() {
	System.err.println("[REMOTE] : select right()");
	if(!lc_check.checkStarted())
	    return;
	next_command |= Command.RIGHT;
    }

    public void reset() {
	System.err.println("[REMOTE] : select reset()");
	if(!lc_check.checkStarted())
	    return;
	next_command = Command.RESET;
    }

    // Laison des fournisseurs
    public void bind(ReceiverService receiver) throws ServiceBindException {
	if(lc_check.checkStarted())
	    throw new ServiceBindException("Cannot configure while running");
        this.receiver = receiver;
    }
    
    // Implantation de l'interface IntrospectionController
    public ComponentType getComponentType() {
        return new ComponentTypeDescriptor(this.getClass().getName(),this.getClass());
    }

    
    public ServiceType[] getProvidedServiceTypes() {
        ServiceType[] ret = new ServiceType[1];
        ret[0] = new ServiceTypeDescriptor("RemoteService", RemoteService.class);
        return ret;
    }
            
    public ServiceType[] getRequiredServiceTypes() {
        ServiceType[] ret = new ServiceType[1];
        ret[0] = new ServiceTypeDescriptor("ReceiverService", ReceiverService.class);
        return ret;
    }    
    
    public ServiceType getProvidedServiceType(String from_name) {
        if(from_name.equals("RemoteService"))
            return new ServiceTypeDescriptor("RemoteService", RemoteService.class);
        else 
            return null;
    }
    
    public ServiceType getRequiredServiceType(String from_name) {
        if(from_name.equals("ReceiverService"))
            return new ServiceTypeDescriptor("ReceiverService", ReceiverService.class);
        else 
            return null;        
    }
    
    // implantation de l'interface ReflectionController
    public Object[] getProviders(ServiceType service) {
        Object[] ret = null;
        ArrayList list = new ArrayList();
        if(service.isProvidedBy(receiver))
            list.add(receiver);
        if(list.size()==0)
            return null;
        ret = new Object[list.size()];
        for(int i=0;i<list.size();i++) {
            ret[i] = list.get(i);
        }
        return ret;
    }

    // implantation de l'interface RemoteLifeCycleController
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

    // LCActivateController interface
    public void activate()  throws LifeCycleException {
	lc_check.activate();
	receiver.receive(new Command(next_command));	
    }

    public void deactivate()  throws LifeCycleException {
	lc_check.deactivate();
    }

}
