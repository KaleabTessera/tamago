/*******************************************
 * Copyright (C) 2005, Frederic Peschanski *
 *         >> DO NOT DISTRIBUTE <<         *
 *******************************************/

import tamago.*;

public class Car extends BasicComposite
       implements CarService,
		  ExportReceiverService {

    // sub components
    private Receiver receiver;
    private Engine engine;
    private Direction direction;
    private Effector effector;

    // Interface fonctionnelle
    public Car() throws ServiceBindException {
	receiver = new Receiver();
        engine = new Engine();
	direction = new Direction();
	effector = new Effector();
	receiver.bind(engine);
	receiver.bind(direction);
	engine.bind(effector);
	direction.bind(effector);
    }

    public void on() {
	// ne rien faire
    }

    public void off() {
	// ne rien faire
    }

    // Exportation des services
    public ReceiverService exportReceiverService() {
	return (ReceiverService) receiver;
    }

    // Laison des fournisseurs
    // aucun fournisseur

}
