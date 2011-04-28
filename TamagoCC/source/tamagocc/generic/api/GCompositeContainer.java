package tamagocc.generic.api;

import java.util.Iterator;

public interface GCompositeContainer extends GComponentContainer,GAssemblyContainer {
	Iterator<GExport> getExportations();
}
