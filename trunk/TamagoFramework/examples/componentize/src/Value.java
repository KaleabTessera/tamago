
public class Value {
    double val;

    public static final Value DIV_ZERO = new Value(0);

    public Value(double val) {
	this.val = val;
    }

    public double getVal() {
	return val;
    }

    public void setVal(double val) {
	this.val = val;
    }

    public String toString() {
	return Double.toString(val);
    }

}
