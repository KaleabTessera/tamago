package tamagocc.parser.cdlast;

import tamagocc.api.TExpression;
import tamagocc.impl.TIIsBound;

public class CDLIsBound extends CDLExpression {

	private String label; 
	
	public CDLIsBound(String label) {
		this.label = label;
	}

	@Override
	public String getDescription() {
		StringBuilder sb = new StringBuilder("@isBound(");
		sb.append(label);
		sb.append(")");
		return sb.toString();
	}

	@Override
	public TExpression toTExpression() {
		return new TIIsBound(label);
	}

}
