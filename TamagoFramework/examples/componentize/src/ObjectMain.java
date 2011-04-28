
public class ObjectMain {

    public static void main(String[] args) {
	Value tempF = new Value(Double.parseDouble(args[0]));
	Value c32 = new Value(32);
	SubOp sub32 = new SubOp(tempF,c32);
	Value temp2 = sub32.compute();
	Value c9 = new Value(9);
	DivOp div9 = new DivOp(temp2,c9);
	Value temp3 = div9.compute();
	Value c5 = new Value(5);
	MultOp mult5 = new MultOp(temp3,c5);
	System.out.println(args[0] + "°F ==> " + mult5.compute().toString() + "°C");
    }

}
