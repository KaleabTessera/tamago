/*******************************************
 * Copyright (C) 2005, Frédéric Peschanski *
 *         >> DO NOT DISTRIBUTE <<         *
 *******************************************/

import tamago.*;

public class Car extends BasicComposite
       implements CarService,
		  ExportReceiverService,
		  ReflectionController,
		  CompositeIntrospectionController,
		  CompositeReflectionController,
                  CarLifeCycleController {

    // sub components
    private Receiver receiver;
    private Engine engine;
    private Direction direction;
    private Effector effector;

    // attributes
    private CarLifeCycleDelegate lc_check;
    
    // Interface fonctionnelle
    public Car() {
	receiver = new Receiver();
        engine = new Engine();
	direction = new Direction();
	effector = new Effector();
	lc_check = new CarLifeCycleDelegate();
    }

    public void on() throws LifeCycleException {
	if(lc_check.checkStarted())
	    throw new LifeCycleException("Car component already started");
	start();
    }

    public void off() throws LifeCycleException {
	if(!lc_check.checkStarted())
	    throw new LifeCycleException("Car component already stopped");
	effector.stop();
	direction.stop();
	engine.stop();
	receiver.stop();
    }

    // Exportation des services
    public ReceiverService exportReceiverService() {
	return (ReceiverService) receiver;
    }

    // Laison des fournisseurs
    // aucun fournisseur

    // Implantation de l'interface IntrospectionController
    public ComponentType getComponentType() {
        return new ComponentTypeDescriptor(this.getClass().getName(),this.getClass());
    }

    
    public ServiceType[] getProvidedServiceTypes() {
        ServiceType[] ret = new ServiceType[1];
        ret[0] = new ServiceTypeDescriptor("CarService", CarService.class);
        return ret;
    }
            
    public ServiceType[] getRequiredServiceTypes() {
	return null;
    }    
    
    public ServiceType getProvidedServiceType(String from_name) {
        if(from_name.equals("CarService"))
            return new ServiceTypeDescriptor("CarService", CarService.class);
        else 
            return null;
    }
    
    public ServiceType getRequiredServiceType(String from_name) {
	return null;        
    }
    
    // implantation de l'interface ReflectionController
    public Object[] getProviders(ServiceType service) {
	return null;
    }
   
    // implantation de l'interface CompositeIntrospectionController
    public ServiceType[] getExportedServiceTypes() {
        ServiceType[] ret = new ServiceType[1];
        ret[0] = new ServiceTypeDescriptor("ReceiverService", ReceiverService.class);
        return ret;
    }

    public ServiceType getExportedServiceType(String from_name) {
        if(from_name.equals("ReceiverService"))
            return new ServiceTypeDescriptor("ReceiverService", ReceiverService.class);
        else 
            return null;
    }

    // implantation de l'interface CompositeReflectionController
    public Component[] getSubComponents() {
	Component[] comps = new Component[4];
	comps[0] = receiver;
	comps[1] = engine;
	comps[2] = direction;
	comps[3] = effector;
	return comps;
    }

    // implantation de l'interface CarLifeCycleController
    public void initialize() throws LifeCycleException {
	lc_check.initialize();

	// we initialize the subcomponents here
	receiver.initialize();
	engine.initialize();
	direction.initialize();	
	effector.initialize();
    }

    // LCConfigureControler interface
    public void configure() throws LifeCycleException {
	lc_check.configure();

	// we bind the subcomponents here
	try { 
	    receiver.bind(engine);
	    receiver.bind(direction);
	    engine.bind(effector);
	    direction.bind(effector);
	} catch(ServiceBindException e) {
	    throw new LifeCycleException("Cannot configure, reason is : "+e.getMessage());
	}

	// and we configure them
	receiver.configure();
	engine.configure();
	direction.configure();	
	effector.configure();
    }

    // LCStartStopController interface
    public void start()  throws LifeCycleException {
	lc_check.start();
	receiver.start();
	engine.start();
	direction.start();
	effector.start();
    }

    public void stop()  throws LifeCycleException {
	lc_check.stop();
	receiver.stop();
	engine.stop();
	direction.stop();
	effector.stop();
    }
   
}
