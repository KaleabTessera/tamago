package tamagocc.generic.api;

public interface GAtPre extends GExpression {
	GExpression getTerm();
	GExpression getInitialisation();
	// --------------------
	GExpression getRawExpr();
	
	String getRawName();
	
	GType getRawType();
}
