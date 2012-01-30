package tamago.aca.term;

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
}
