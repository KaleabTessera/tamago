
public class AddOp extends BinOp {

    public AddOp(Value left, Value right) {
	super(left,right);
    }

    public Value compute() {
	return new Value(left.getVal()+right.getVal());
    }

}
