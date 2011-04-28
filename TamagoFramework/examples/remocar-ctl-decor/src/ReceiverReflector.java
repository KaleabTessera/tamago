
import java.util.*;

import tamago.*;

public class ReceiverReflector extends Receiver
    implements ReflectionController {

    private ReceiverReflector() {	
    }

    public ReceiverReflector(Receiver r) {
	engine = r.engine;
	direction = r.direction;
    }
    
    // implantation de l'interface ReflectionController
    public Object[] getProviders(ServiceType service) {
        Object[] ret = null;
        ArrayList list = new ArrayList();
        if(service.isProvidedBy(engine))
            list.add(engine);
        else if(service.isProvidedBy(direction))
            list.add(direction);
        if(list.size()==0)
            return null;
        ret = new Object[list.size()];
        for(int i=0;i<list.size();i++) {
            ret[i] = list.get(i);
        }
        return ret;
    }
}
