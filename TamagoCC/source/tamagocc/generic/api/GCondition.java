package tamagocc.generic.api;

public interface GCondition extends GObject {
	GCategory getCategory();
	String getMessage();
	GExpression getExpression();
}
