package tamago;

public class LCStateDescriptor implements LCState {
    private int id;
    private String repr;
    private String descr;

    public LCStateDescriptor(int id, String repr, String descr) {
	this.id = id;
	this.repr = repr;
	this.descr = descr;
    }

    public int getID() {
	return id;
    }

    public String getRepr() {
	return repr;
    }

    public String getDescription() {
	return descr;
    }
}
