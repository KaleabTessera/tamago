/*******************************************
 * Copyright (C) 2005, Frédéric Peschanski *
 *         >> DO NOT DISTRIBUTE <<         *
 *******************************************/

import tamago.*;

public class TestIntrospectReceiverMain extends TestIntrospect {
    public static void main(String[] args) {
       println("<<< Crer instance du composant Receiver >>>");
       Receiver r = new Receiver();
       
       println("<<< Introspection du composant >>>");
       introspectComponent(r, r.getComponentType());
       
       println("<<< Introspection des services requis >>>");
       ServiceType[] requis = r.getRequiredServiceTypes();
       for(int i=0;i<requis.length;i++)
           introspectRequiredService(r, requis[i]);
       
       println("<<< Introspection des services fournis >>>");
       ServiceType[] fournis = r.getProvidedServiceTypes();
       for(int i=0;i<fournis.length;i++)
           introspectProvidedService(r, fournis[i]);
    }
}
