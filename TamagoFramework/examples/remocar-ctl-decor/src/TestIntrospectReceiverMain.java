/*******************************************
 * Copyright (C) 2005, Fr.b��d��ric Peschanski *
 *         >> DO NOT DISTRIBUTE <<         *
 *******************************************/

import tamago.*;

public class TestIntrospectReceiverMain extends TestIntrospect {
    public static void main(String[] args) {
       println("<<< Cr��er instance du composant Receiver >>>");
       Receiver r = new Receiver();

       println("<<< Cr��er instance de l'introspecteur ReceiverIntrospector >>>");
       ReceiverIntrospector ri = new ReceiverIntrospector();
       
       println("<<< Introspection du composant >>>");
       introspectComponent(r, ri.getComponentType());
       
       println("<<< Introspection des services requis >>>");
       ServiceType[] requis = ri.getRequiredServiceTypes();
       for(int i=0;i<requis.length;i++)
           introspectRequiredService(r, requis[i]);
       
       println("<<< Introspection des services fournis >>>");
       ServiceType[] fournis = ri.getProvidedServiceTypes();
       for(int i=0;i<fournis.length;i++)
           introspectProvidedService(r, fournis[i]);
    }
}
