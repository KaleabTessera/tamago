package tamagocc.generic.impl;

import java.util.ArrayList;

import tamagocc.api.CatType;
import tamagocc.exception.TamagoCCException;
import tamagocc.generic.TamagoCCGExpressionVisitor;
import tamagocc.generic.TamagoCCGVisitor;
import tamagocc.generic.api.GAtPre;
import tamagocc.generic.api.GBool;
import tamagocc.generic.api.GCall;
import tamagocc.generic.api.GCast;
import tamagocc.generic.api.GExpression;
import tamagocc.generic.api.GInLabel;
import tamagocc.generic.api.GInState;
import tamagocc.generic.api.GInteger;
import tamagocc.generic.api.GIsBound;
import tamagocc.generic.api.GLanguageExpr;
import tamagocc.generic.api.GNil;
import tamagocc.generic.api.GNoContract;
import tamagocc.generic.api.GNot;
import tamagocc.generic.api.GOperator;
import tamagocc.generic.api.GPreExpression;
import tamagocc.generic.api.GRead;
import tamagocc.generic.api.GReal;
import tamagocc.generic.api.GReturn;
import tamagocc.generic.api.GString;
import tamagocc.generic.api.GType;
import tamagocc.generic.api.GVariable;

public class GIAtPre extends GIExpression implements GAtPre {
	
	private static int created_value = 0;
	
	
	private static String getVariableName() {
		return "__tamagocc_pre_"+(created_value++);
	}
	
	class RemoveIndex implements TamagoCCGExpressionVisitor {

		@Override
		public Object visitBool(GBool bool) throws TamagoCCException {
			return bool;
		}

		@Override
		public Object visitCall(GCall call) throws TamagoCCException {
			return call;
		}

		@Override
		public Object visitCast(GCast cast) throws TamagoCCException {
			return new GICast(cast.getType(),(GExpression) cast.getExpression().visitExpression(this));
		}

		@Override
		public Object visitExpression(GExpression expression)
				throws TamagoCCException {
			throw new TamagoCCException("Atpre remove index: unsupported expression");
		}

		@Override
		public Object visitInLabel(GInLabel inlabel) throws TamagoCCException {
			return new GIInLabel((GExpression)inlabel.getTarget().visitExpression(this),(GExpression) inlabel.getSubExpression().visitExpression(this));
		}

		@Override
		public Object visitInteger(GInteger interger) throws TamagoCCException {
			return interger;
		}

		@Override
		public Object visitLanguageExpr(GLanguageExpr languageExpr)
				throws TamagoCCException {
			return languageExpr;
		}

		@Override
		public Object visitNil(GNil nil) throws TamagoCCException {
			return nil;
		}

		@Override
		public Object visitNoContract(GNoContract nocontract)
				throws TamagoCCException {
			return nocontract;
		}

		@Override
		public Object visitNot(GNot not) throws TamagoCCException {
			return new GINot((GExpression)not.getTerm().visitExpression(this));
		}

		@Override
		public Object visitOperator(GOperator operator)
				throws TamagoCCException {
			/*ArrayList<GExpression> exprs = new ArrayList<GExpression>();
			Iterator<GExpression> args = operator.getOperands();
			while(args.hasNext()) {
				exprs.add((GExpression)args.next().visitExpression(this));
			}
			return new GIOperator(operator.getOperator(),exprs.iterator());*/
			return operator;
		}

		@Override
		public Object visitPre(GAtPre atpre) throws TamagoCCException {
			return atpre;
		}

		@Override
		public Object visitQuantifierVariable(GIQuantifierVariable variable)
				throws TamagoCCException {
			return variable;
		}

		@Override
		public Object visitRead(GRead read) throws TamagoCCException {
			if(read.hasIndex()) {
				GIAtPre.this.variable = new GIVariable(GIAtPre.this.variable.getName(), variable.getType(), read.getIndex());
				return new GIRead(read.getName());
			}
			else
				return read;
		}

		@Override
		public Object visitReal(GReal real) throws TamagoCCException {
			return real;
		}

		@Override
		public Object visitReturn(GReturn ret) throws TamagoCCException {
			return ret;
		}

		@Override
		public Object visitString(GString string) throws TamagoCCException {
			return string;
		}

		@Override
		public Object visitVariable(GVariable variable)
				throws TamagoCCException {
			if(variable.hasIndex()) {
				GIAtPre.this.variable = new GIVariable(GIAtPre.this.variable.getName(), GIAtPre.this.variable.getType(), variable.getIndex());
				return new GIVariable(variable.getName(), variable.getType());
			}
			else {
				return variable;
			}
		}

		@Override
		public Object visitInState(GInState giInState) throws TamagoCCException {
			return giInState;
		}

		@Override
		public Object visitIsBound(GIsBound giIsBound) throws TamagoCCException {
			return giIsBound;
		}
		
	}
	
	// ---------------------------------------------------------
	
	private GVariable variable;
	private GIInitialisation initialisation;
	
	private GExpression rawterm;
	private GType rawtype;
	private String rawname;
	
	public GIAtPre(GExpression expr,GType type) throws TamagoCCException {
		this(getVariableName(),expr,type);
	}
	
	public GIAtPre(String name,GExpression expr,GType type) throws TamagoCCException {
		super();
		this.rawterm = expr;
		this.rawtype = type;
		this.rawname = name;
		variable = new GIVariable(name,type);
		if(type.isArray()) {
			GExpression withoutindex = removeIndex(expr);
			GIInLabel clone = new GIInLabel(withoutindex, new GICall("clone", new ArrayList<GExpression>()));
			// GICast cast = new GICast(type, clone);
			initialisation = new GIAtPreInitialisation(new GIVariable(name,type), clone);
		}
		else {
			if(type.catType() == CatType.OBJECT)
				initialisation = new GIAtPreInitialisation(variable, new GIInLabel(expr, new GICall("clone", new ArrayList<GExpression>())));
			else
				initialisation = new GIAtPreInitialisation(variable,expr);	
		}	
		preexprs.addAll(expr.getPreExpression());
		preexprs.add(initialisation);
	}

	public GExpression getRawExpr() {
		return rawterm;
	}
	
	public String getRawName() {
		return rawname;
	}
	
	public GType getRawType() {
		return rawtype;
	}
	

	private GExpression removeIndex(GExpression e) throws TamagoCCException {
		RemoveIndex ri = new RemoveIndex();
		return (GExpression)e.visitExpression(ri);
	}

	public GExpression getTerm() {
		return variable;
	}

	public GExprType getCategory() {
		return GExprType.ATPRE;
	}

		
	public void setExpression(GExpression expr) {
		initialisation.setInitValue(expr);
		preexprs = new ArrayList<GPreExpression>();
		preexprs.addAll(expr.getPreExpression());
		preexprs.add(initialisation);
	}
	public void setVariable(GVariable variable) {
		this.variable = variable;
		initialisation.setVariable(variable);
	}
	
	/**
	 * @see tamagocc.generic.api.GObject#visit(tamagocc.generic.TamagoCCGVisitor)
	 */
	public Object visit(TamagoCCGVisitor visitor) throws TamagoCCException {
		return visitor.visitPre(this);
	}

	/**
	 * @see tamagocc.generic.api.GExpression#visitExpression(tamagocc.generic.TamagoCCGExpressionVisitor)
	 */
	public Object visitExpression(TamagoCCGExpressionVisitor exprvisitor) throws TamagoCCException {
		return exprvisitor.visitPre(this);
	}

	public GExpression getInitialisation() {
		return initialisation.getInitValue();
	}
	
	public boolean equals(Object o) {
		if(o instanceof GAtPre) {
			return initialisation.equals(((GAtPre)o).getInitialisation());
		}
		else 
			return false;
	}
	
}
