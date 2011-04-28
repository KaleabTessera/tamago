
import tamago.*;

public class ComponentMain {

    public static void main(String[] args) {
	
	ConstantComponent tempF = new ConstantComponent();
	tempF.setValue(new Value(Double.parseDouble(args[0])));
	ConstantComponent c32 = new ConstantComponent();
	c32.setValue(new Value(32));
	ConstantComponent c9 = new ConstantComponent();
	c9.setValue(new Value(9));
	ConstantComponent c5 = new ConstantComponent();
	c5.setValue(new Value(5));

	ConstantComponent res1 = new ConstantComponent();
	ConstantComponent res2 = new ConstantComponent();
	ConstantComponent res = new ConstantComponent();

	BinOpComponent sub32 = new BinOpComponent(new SubOp(null,null));
        BinOpComponent div9 = new BinOpComponent(new DivOp(null,null));
	BinOpComponent mult5 = new BinOpComponent(new MultOp(null,null));

	sub32.bind( (ConstantService) tempF , "left");
	sub32.bind( (ConstantService) c32 , "right");
	sub32.bind( (ConstantService) res1 , "dest");

	div9.bind( (ConstantService) res1, "left");
	div9.bind( (ConstantService) c9, "right");
	div9.bind( (ConstantService) res2, "dest");

	mult5.bind( (ConstantService) res2, "left");
	mult5.bind( (ConstantService) c5, "right");
	mult5.bind( (ConstantService) res, "dest");

	System.out.println(args[0] + "°F ==> " + res5.getValue() + "°C");
    }

}
