package tamagocc.compiler;

import java.util.ArrayList;
import java.util.Collection;

import tamagocc.api.TCondition;
import tamagocc.api.TExpression;
import tamagocc.api.TIncludeService;
import tamagocc.api.TInvariant;
import tamagocc.api.TMethod;
import tamagocc.api.TParameter;
import tamagocc.api.TRefineService;
import tamagocc.api.TTransition;
import tamagocc.api.TType;
import tamagocc.util.Triplet;

public class TamagoCDLEaseFactory {

	public static TRefineService refine(String n, String m) {
		// TODO Auto-generated method stub
		return null;
	}

	public static TIncludeService include(String n, String m) {
		// TODO Auto-generated method stub
		return null;
	}

	public static TMethod method(TType tType, String string,
			Collection<TParameter> collection, String string2,
			TCondition pre, TCondition post) {
		// TODO Auto-generated method stub
		return null;
	}

	public static TParameter param(TType t, String string) {
		// TODO Auto-generated method stub
		return null;
	}

	public static TTransition transition(String string, String string2,
			String string3, TExpression guard) {
		// TODO Auto-generated method stub
		return null;
	}

	public static TInvariant invariant(TExpression tExpression, String string) {
		// TODO Auto-generated method stub
		return null;
	}

	public static TCondition precond(TExpression tExpression, String string) {
		// TODO Auto-generated method stub
		return null;
	}

	public static TCondition postcond(TExpression tExpression, String string) {
		// TODO Auto-generated method stub
		return null;
	}

	public static TExpression quant(String quant, String var, TType type,
			String kind, Collection<Object> collection,
			TExpression body) {
		// TODO Auto-generated method stub
		return null;
	}

	public static TExpression operator(String op, ArrayList<TExpression> exprs) {
		// TODO Auto-generated method stub
		return null;
	}

	public static TExpression inlabelSelf(ArrayList sub,
			Triplet<TExpression, Collection<TExpression>, Boolean> d) {
		// TODO Auto-generated method stub
		return null;
	}

	public static TExpression inlabelReturn(
			Triplet<TExpression, Collection<TExpression>, Boolean> a) {
		// TODO Auto-generated method stub
		return null;
	}

	public static TExpression inlabelRead(String string, ArrayList<String> sub,
			Triplet<TExpression, Collection<TExpression>, Boolean> d) {
		// TODO Auto-generated method stub
		return null;
	}

	public static TExpression inlabelVar(String string, ArrayList<String> sub,
			Triplet<TExpression, Collection<TExpression>, Boolean> d) {
		// TODO Auto-generated method stub
		return null;
	}

	

}
