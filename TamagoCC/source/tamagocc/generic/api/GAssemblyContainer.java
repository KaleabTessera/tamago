package tamagocc.generic.api;

import java.util.Iterator;

public interface GAssemblyContainer extends GTamagoEntity {
	Iterator<GBind> getBinds();
	Iterator<GInstantiateComponent> getInstatiateComponent();
}
