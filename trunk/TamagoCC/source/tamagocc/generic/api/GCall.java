 package tamagocc.generic.api;

import java.util.Iterator;

public interface GCall extends GExpression {
	Iterator<GExpression> getArguments();
	
	int getArgCount();
	
	GExpression getArgument(int pos);
	
	String getName();
}
