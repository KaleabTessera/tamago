package tamagocc.api;

import tamagocc.exception.TamagoCCException;
import tamagocc.util.TamagoCCVisitor;

/**
 * 
 * This class represents operators in the CDL language.
 * 
 * @author Hakim BELHAOUARI
 */
public class TOpeName implements TObject{    
    
    public static final int INF = 1;
    public static final int INFEG = 2;
    public static final int EG = 3;
    public static final int NE = 4;
    public static final int SUPEG = 5;
    public static final int SUP = 6;
    
    public static final int AND = 7;
    public static final int OR = 8;
    public static final int XOR = 9;
    public static final int IMPLY = 15;
    public static final int EQUIV = 16;
    
    public static final int PLUS = 10;
    public static final int MINUS = 11;
    public static final int TIMES = 12;
    public static final int QUO = 13;
    public static final int MOD = 14;
        
    public static final TOpeName opInf = new TOpeName(INF,"lt");
    public static final TOpeName opInfEg = new TOpeName(INFEG,"le");
    public static final TOpeName opEg = new TOpeName(EG,"eg");
    public static final TOpeName opNe = new TOpeName(NE,"ne");
    public static final TOpeName opSupEg = new TOpeName(SUPEG,"ge");
    public static final TOpeName opSup = new TOpeName(SUP,"gt");
    
    public static final TOpeName opPlus = new TOpeName(PLUS,"plus");
    public static final TOpeName opMinus = new TOpeName(MINUS,"minus");
    public static final TOpeName opTimes = new TOpeName(TIMES,"times");
    public static final TOpeName opQuo = new TOpeName(QUO,"quo");
    public static final TOpeName opMod = new TOpeName(MOD,"mod");
    
    public static final TOpeName opAnd = new TOpeName(AND,"and");
    public static final TOpeName opOr = new TOpeName(OR,"or");
    public static final TOpeName opXor = new TOpeName(XOR,"xor");
    public static final TOpeName opImply = new TOpeName(IMPLY, "impl");
    public static final TOpeName opEquiv = new TOpeName(EQUIV,"equiv");
    
    private final int id;
    private final String str;
    
    private TOpeName(int id,String str) {
        this.id = id;
        this.str = str;
    }
    
    /**
     * 
     * @return Return the ID of the operator
     */
    public int getID() {
        return id;
    }
    
    public boolean equals(Object o) {
    	if (o instanceof TOpeName) {
			TOpeName p = (TOpeName) o;
			return (p.getID() == getID());
		}
    	return false;
    }
    
    /**
     * 
     * @return Return the XML name of this operator
     */
    public String getOperator() {
        return str;
    }
    
    /**
     * @return Return the textual representation of this operator to be reading
     * by an human.
     */
    public String toString() {
        switch(id) {
        case EG: return "==";
        case NE: return "!=";
        case INF: return "<";
        case INFEG: return "<=";
        case SUP: return ">";
        case SUPEG: return ">=";
        case PLUS: return "+";
        case MINUS: return "-";
        case TIMES: return "*";
        case QUO: return "/";
        case MOD: return "%";
        case AND: return "and";
        case OR: return "or";
        case XOR: return "^";
        case IMPLY: return "=>";
        case EQUIV: return "<=>";
        default:
            return "Unknow Ope"; // cas impossible
        }
    }

    public Object visit(TamagoCCVisitor visitor) throws TamagoCCException {
		return visitor.visitOpeName(this);
	}
    /*      
    public static final TOpeName opInf = new TOpeName(INF,"lt");
    public static final TOpeName opInfEg = new TOpeName(INFEG,"le");
    public static final TOpeName opEg = new TOpeName(EG,"eg");
    public static final TOpeName opNe = new TOpeName(NE,"ne");
    public static final TOpeName opSupEg = new TOpeName(SUPEG,"ge");
    public static final TOpeName opSup = new TOpeName(SUP,"gt");
    
    public static final TOpeName opPlus = new TOpeName(PLUS,"plus");
    public static final TOpeName opMinus = new TOpeName(MINUS,"minus");
    public static final TOpeName opTimes = new TOpeName(TIMES,"times");
    public static final TOpeName opQuo = new TOpeName(QUO,"quo");
    public static final TOpeName opMod = new TOpeName(MOD,"mod");
    
    public static final TOpeName opAnd = new TOpeName(AND,"and");
    public static final TOpeName opOr = new TOpeName(OR,"or");
    public static final TOpeName opXor = new TOpeName(AND,"xor");
    */

	public static TOpeName generate(String o) throws TamagoCCException {
		if("lt".equals(o))
			return opInf;
		else if("le".equals(o))
			return opInfEg;
		else if("eg".equals(o))
			return opEg;
		else if("ne".equals(o))
			return opNe;
		else if("ge".equals(o))
			return opSupEg;
		else if("gt".equals(o))
			return opSup;
		else if("plus".equals(o))
			return opPlus;
		else if("minus".equals(o))
			return opMinus;
		else if("times".equals(o))
			return opTimes;
		else if("quo".equals(o))
			return opQuo;
		else if("mod".equals(o))
			return opMod;
		else if("and".equals(o))
			return opAnd;
		else if("or".equals(o))
			return opOr;
		else if("xor".equals(o))
			return opXor;
		else if("impl".equals(o))
			return opImply;
		else if("equiv".equals(o))
			return opEquiv;
		else
			throw new TamagoCCException("Unknown operator: "+o);
	}   
    
}