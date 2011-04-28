package tamagocc.generic.api;


public interface GTransition extends GObject {
	GState getOrigin();
	GState getFinal();
	String getMethodID();
	GExpression getCondition();
	
	//GTamagoEntity getParent();
}
