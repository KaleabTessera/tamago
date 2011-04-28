/*******************************************
 * Copyright (C) 2005, Frederic Peschanski*
 *         >> DO NOT DISTRIBUTE <<         *
 *******************************************/

package tamago;

public class ServiceTypeDescriptor implements ServiceType {
 	private String type_name;
	private Class type_class;
	
	public ServiceTypeDescriptor(String type_name, Class type_class) {
		this.type_name=type_name;
		this.type_class=type_class;
    }
    
    public String getName() {
        return type_name;
    }
    
    public Class getImplementation() {
        return type_class;
    }
    
    public boolean isSuperType(ServiceType subtype) {
        return type_class.isAssignableFrom(subtype.getClass());   
    }
    
    public boolean isSubType(ServiceType supertype) {
        return supertype.getClass().isAssignableFrom(type_class);    
    }
    
    public boolean isSameType(ServiceType type2) {
        return type_name.equals(type2.getName()) && type_class.equals(type2.getClass());   
    }
    
    public boolean isStrictSuperType(ServiceType subtype) {
        return isSuperType(subtype) && !isSameType(subtype);
    }
    
    public boolean isStrictSubType(ServiceType supertype) {
        return isSubType(supertype) && !isSameType(supertype);
    }

    public boolean isProvidedBy(Object o) {
        if(o==null) {
            //System.err.println("Warning: null may not provide anything");
            return false;
        }
        return type_class.isAssignableFrom(o.getClass());
    }
    
}
