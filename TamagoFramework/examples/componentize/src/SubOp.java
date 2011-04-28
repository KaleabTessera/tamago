
public class SubOp extends BinOp {

    public SubOp(Value left, Value right) {
	super(left,right);
    }

    public Value compute() {
	return new Value(left.getVal()-right.getVal());
    }

}
