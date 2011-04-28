/*******************************************
 * Copyright (C) 2005, Frederic Peschanski *
 *         >> DO NOT DISTRIBUTE <<         *
 *******************************************/

import tamago.*;

public class Car extends BasicComposite
       implements CarService,
		  ExportReceiverService,
		  ReflectionController,
		  CompositeIntrospectionController,
		  CompositeReflectionController {

    // sub components
    protected Receiver receiver;
    protected Engine engine;
    protected Direction direction;
    protected Effector effector;

    // Interface fonctionnelle
    public Car() {
	receiver = new Receiver();
        engine = new Engine();
	direction = new Direction();
	effector = new Effector();
    }

    public void on() throws LifeCycleException {
	// ne rien faire
    }

    public void off() throws LifeCycleException {
	// ne rien faire
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
}
