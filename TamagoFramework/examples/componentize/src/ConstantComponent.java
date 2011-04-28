
import tamago.*;

public class ConstantComponent extends BasicComponent 
    implements ConstantService {
    
    private Value val;

    public ConstantComponent() {
	val = new Value(0);
    }

    public Value getValue() {
	return val;
    }

    public void setValue(Value val) {
	this.val = val;
    }
}

