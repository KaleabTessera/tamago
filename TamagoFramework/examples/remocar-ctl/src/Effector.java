/*******************************************
 * Copyright (C) 2005, Frédéric Peschanski *
 *         >> DO NOT DISTRIBUTE <<         *
 *******************************************/

import tamago.*;

public class Effector 
    extends BasicComponent
    implements EffectorDirectionService,
               EffectorEngineService,
	       IntrospectionController,
	       ReflectionController,
               CarLifeCycleController {

    // Required services
    private CarLifeCycleDelegate lc_check;

    // Properties
    private double power_distance_ratio = 1/100.0*10; 
    private double last_angle;

    // Instantiation
    public Effector() {
	lc_check = new CarLifeCycleDelegate();
    }
    

    public void deliverPower(double power) {
	if(!lc_check.checkStarted())
	    return;

	double dist = power * power_distance_ratio;
	double dx = dist * Math.cos(last_angle) - dist * Math.sin(last_angle) ;
	double dy = dist * Math.sin(last_angle) + dist * Math.cos(last_angle) ;
	goForward(dx,dy);
    }

    public void deliverDirection(double angle) {
	if(!lc_check.checkStarted())
	    return;

	last_angle = angle;
    }

    protected void goForward(double dx, double dy) {
	System.out.println("[EFFECTOR] : Moved X="+dx+" cm , Y="+dy+" cm, DIST="+Math.sqrt(dx*dx+dy*dy)+" cm"); 
    }

    // Propriétés

    public double getPowerDistanceRatio() {
	return power_distance_ratio;
    }

    public void setPowerDistanceRatio(double power_distance_ratio) {
	this.power_distance_ratio = power_distance_ratio;
    }

    // Implantation de l'interface IntrospectionController
    public ComponentType getComponentType() {
        return new ComponentTypeDescriptor(this.getClass().getName(),this.getClass());
    }

    
    public ServiceType[] getProvidedServiceTypes() {
        ServiceType[] ret = new ServiceType[2];
        ret[0] = new ServiceTypeDescriptor("EffectorEngineService", EffectorEngineService.class);
        ret[1] = new ServiceTypeDescriptor("EffectorDirectionService", EffectorDirectionService.class);
        return ret;
    }
            
    public ServiceType[] getRequiredServiceTypes() {
	return null;
    }    
    
    public ServiceType getProvidedServiceType(String from_name) {
        if(from_name.equals("EffectorEngineService"))
            return new ServiceTypeDescriptor("EffectorEngineService", EffectorEngineService.class);
        else if(from_name.equals("EffectorDirectionService"))
            return new ServiceTypeDescriptor("EffectorDirectionService", EffectorEngineService.class);
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

	       
