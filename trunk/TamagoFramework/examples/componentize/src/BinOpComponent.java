
import tamago.*;

public class BinOpComponent extends BinOp 
    implements Component,
	       BinOpService,
	       RequireConstantService {

    private BinOp delegate;
    private ConstantService left;
    private ConstantService right;
    private ConstantService dest;

    private BinOpComponent() {}

    public BinOpComponent(BinOp op) {
	delegate = op;
    }

    public void bind(RequireConstantService constant, String label) throws ServiceBindException {
	if(label.equals("left")) 
	    left = constant;
	else if(label.equals("right")) 
	    right = constant;
	else if(label.equals("dest"))
	    dest = constant;
	else
	    throw new ServiceBindException("Cannot bind ConstantService, label '"+label+"' unsupported");
    }

    public void compute() {
	delegate.setLeft(left.getValue());
	delegate.setRight(right.getValue());
	dest.setValue(delegate.compute());
    }

}
