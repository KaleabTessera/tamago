
public class DivOp extends BinOp {

    public DivOp(Value left, Value right) {
	super(left,right);
    }

    public Value compute() {
	if(right.getVal()==0.0)
	    return Value.DIV_ZERO;
	return new Value(left.getVal()/right.getVal());
    }

}
