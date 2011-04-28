
public abstract class BinOp {
    protected Value left;
    protected Value right;

    public BinOp(Value left, Value right) {
	this.left = left;
	this.right = right;
    }

    public abstract Value compute();

    public Value getLeft() {
	return left;
    }

    public void setLeft(Value left) {
	this.left = left;
    }

    public Value getRight() {
	return left;
    }

    public void setRight(Value left) {
	this.left = left;
    }

}


	
