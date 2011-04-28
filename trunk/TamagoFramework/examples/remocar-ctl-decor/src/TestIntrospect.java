/*******************************************
 * Copyright (C) 2005, Frederic Peschanski *
 *         >> DO NOT DISTRIBUTE <<         *
 *******************************************/

import tamago.*;

public class TestIntrospect {
    public static void print(String str) {
        System.out.print(str);
    }

    public static void println(String str) {
        System.out.println(str);
    }

    public static void introspectComponent(Component c, ComponentType ct) {
        println("Nom = " + ct.getName());
        println("  Implementation = " + ct.getImplementation());
        println("  Component du bon type ? " + ct.isTypeOf(c));
    }
    
    public static void introspectRequiredService(Component c, ServiceType st) {
        println("Nom = "+st.getName());
        println("  Implementation = " + st.getImplementation());
        println("  Fournisseurs = " + ((ReflectionController) c).getProviders(st));        
    }
    
    public static void introspectProvidedService(Component c, ServiceType st) {
        println("Nom = "+st.getName());
        println("  Implementation = " + st.getImplementation());
        println("  Est-ce un fournisseur ? " + st.isProvidedBy(c));        
    }

}
