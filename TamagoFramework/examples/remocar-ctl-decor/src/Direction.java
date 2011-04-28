/*******************************************
 * Copyright (C) 2005, FrÃ©dÃ©ric Peschanski *
 *         >> DO NOT DISTRIBUTE <<         *
 *******************************************/

import java.util.*;

import tamago.*;

public class Direction extends BasicComponent
    implements DirectionService, 
	       RequireEffectorDirectionService,
	       IntrospectionController,
	       ReflectionController {
    
    // Required services
    private EffectorDirectionService effector;

    // attributes
    private double angle;

    // properties
    private double angle_step = Math.PI / 8.0;

    // Interface fonctionnelle
    public Direction() {
        effector = null;
	angle = 0.0;
    }
    
    public void goLeft() {
	angle-=angle_step;
	if(angle<0.0)
	    angle = 2.0 * Math.PI + angle;
	System.err.println("[DIRECTION] : Going Left ANGLE="+angle);
	effector.deliverDirection(angle);
    }

    public void goRight() {
	angle+=angle_step;
	if(angle>2.0 * Math.PI)
	    angle -= 2.0 * Math.PI;
	System.err.println("[DIRECTION] : Going Right ANGLE="+angle);
	effector.deliverDirection(angle);
    }

    // Laison des fournisseurs
    public void bind(EffectorDirectionService effector) throws ServiceBindException {
	this.effector = effector;
    }
    
    // Cycle de vie
    
    // Propriétés
    public void setAngleStep(double angle_step) {
	this.angle_step = angle_step;
    }

    public double getAngleStep() {
	return angle_step;
    }

    // Implantation de l'interface IntrospectionController
    public ComponentType getComponentType() {
        return new ComponentTypeDescriptor(this.getClass().getName(),this.getClass());
    }
    
    public ServiceType[] getProvidedServiceTypes() {
        ServiceType[] ret = new ServiceType[1];
        ret[0] = new ServiceTypeDescriptor("DirectionService", DirectionService.class);
        return ret;
    }
            
    public ServiceType[] getRequiredServiceTypes() {
        ServiceType[] ret = new ServiceType[1];
        ret[0] = new ServiceTypeDescriptor("EffectorDirectionService", EffectorDirectionService.class);
        return ret;
    }    
    
    public ServiceType getProvidedServiceType(String from_name) {
        if(from_name.equals("DirectionService"))
            return new ServiceTypeDescriptor("DirectionService", DirectionService.class);
        else 
            return null;
    }
    
    public ServiceType getRequiredServiceType(String from_name) {
        if(from_name.equals("EffectorDirectionService"))
            return new ServiceTypeDescriptor("EffectorDirectionService", EffectorDirectionService.class);
        else 
            return null;        
    }
    
    // implantation de l'interface ReflectionController
    public Object[] getProviders(ServiceType service) {
        Object[] ret = null;
        ArrayList list = new ArrayList();
        if(service.isProvidedBy(effector))
            list.add(effector);
        if(list.size()==0)
            return null;
        ret = new Object[list.size()];
        for(int i=0;i<list.size();i++) {
            ret[i] = list.get(i);
        }
        return ret;
    }
}
