package tamago.aca.term;

import tamago.aca.visitor.ACAVisitor;

public class Info {
	private String modelName;
	private String modelModule;

	public Info(String modelName,String modelModule) {
		this.modelName = modelName;
		this.modelModule = modelModule;
	}

	public String getModelName() {
		return modelName;
	}

	public void setModelName(String modelName) {
		this.modelName = modelName;
	}

	public String getModelModule() {
		return modelModule;
	}

	public void setModelModule(String modelModule) {
		this.modelModule = modelModule;
	}
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder("model ");
		sb.append(modelName);
		sb.append(" in ");
		sb.append(modelModule);
		return sb.toString();
	}
	
	public <R,E extends Exception> R visitTerm(ACAVisitor<R,E> visitor) throws E {
		return visitor.visitInfo(this);
	}
}
