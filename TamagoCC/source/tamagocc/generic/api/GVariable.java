package tamagocc.generic.api;


public interface GVariable extends GExpression {
	String getName();
	GType getType();
	
	boolean hasIndex();
	GExpression getIndex();
}
