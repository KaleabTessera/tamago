
import tamago.*;

public class ReceiverIntrospector implements IntrospectionController {
    // Implantation de l'interface IntrospectionController
    public ComponentType getComponentType() {
        return new ComponentTypeDescriptor(Receiver.class.getName(),Receiver.class);
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
}
