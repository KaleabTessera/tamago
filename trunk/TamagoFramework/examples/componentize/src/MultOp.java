
public class MultOp extends BinOp {

    public MultOp(Value left, Value right) {
	super(left,right);
    }

    public Value compute() {
	return new Value(left.getVal()*right.getVal());
    }

}
