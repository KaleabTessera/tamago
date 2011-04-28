package tamagocc.generic.api;


public interface GInitialisation extends GPreExpression {
	GVariable getVariable();
	GExpression getInitValue();
}
