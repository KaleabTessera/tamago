/*******************************************
 * Copyright (C) 2005, Frederic Peschanski *
 *         >> DO NOT DISTRIBUTE <<         *
 *******************************************/

package tamago;

public class ComponentTypeDescriptor implements ComponentType {
	private String type_name;
	private Class type_class;
	
	public ComponentTypeDescriptor(String type_name, Class type_class) {
		this.type_name=type_name;
		this.type_class=type_class;
    }
    
    public String getName() {
        return type_name;
    }
    
    public Class getImplementation() {
        return type_class;
    }
    
    public boolean isSuperType(ComponentType subtype) {
        return type_class.isAssignableFrom(subtype.getClass());   
    }
    
    public boolean isSubType(ComponentType supertype) {
        return supertype.getClass().isAssignableFrom(type_class);    
    }
    
    public boolean isSameType(ComponentType type2) {
        return type_name.equals(type2.getName()) && type_class.equals(type2.getClass());   
    }
    
    public boolean isStrictSuperType(ComponentType subtype) {
        return isSuperType(subtype) && !isSameType(subtype);
    }
    
    public boolean isStrictSubType(ComponentType supertype) {
        return isSubType(supertype) && !isSameType(supertype);
    }

    public boolean equals(Object o2) {
        if(o2 instanceof ComponentType)
            return isSameType((ComponentType) o2);
        else
            return false;
    }
    
    public boolean isTypeOf(Object o) {
        return o.getClass().equals(type_class);   
    }
}
