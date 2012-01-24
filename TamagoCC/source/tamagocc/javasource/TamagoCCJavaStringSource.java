/**
 * 
 */
package tamagocc.javasource;
 
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Iterator;

import tamagocc.api.TOpeName;
import tamagocc.api.TOperator;
import tamagocc.ast.api.AAffectation;
import tamagocc.ast.api.ABool;
import tamagocc.ast.api.ACall;
import tamagocc.ast.api.ACatchBlock;
import tamagocc.ast.api.ACodeComment;
import tamagocc.ast.api.AConvertType;
import tamagocc.ast.api.ADocComment;
import tamagocc.ast.api.AEntity;
import tamagocc.ast.api.AExpression;
import tamagocc.ast.api.AFor;
import tamagocc.ast.api.AForeach;
import tamagocc.ast.api.AIdent;
import tamagocc.ast.api.AIfThenElse;
import tamagocc.ast.api.AImplements;
import tamagocc.ast.api.AInLabel;
import tamagocc.ast.api.AInState;
import tamagocc.ast.api.AInherit;
import tamagocc.ast.api.AInitialisation;
import tamagocc.ast.api.AInlineComment;
import tamagocc.ast.api.AInstExpression;
import tamagocc.ast.api.AInstruction;
import tamagocc.ast.api.AInteger;
import tamagocc.ast.api.AIsBound;
import tamagocc.ast.api.ALanguageExpr;
import tamagocc.ast.api.ALongComment;
import tamagocc.ast.api.AMemberVariable;
import tamagocc.ast.api.AMethod;
import tamagocc.ast.api.AModule;
import tamagocc.ast.api.ANamespace;
import tamagocc.ast.api.ANew;
import tamagocc.ast.api.ANewArray;
import tamagocc.ast.api.ANil;
import tamagocc.ast.api.ANoExpression;
import tamagocc.ast.api.ANoInstruction;
import tamagocc.ast.api.ANot;
import tamagocc.ast.api.AOperator;
import tamagocc.ast.api.AParameter;
import tamagocc.ast.api.AProperty;
import tamagocc.ast.api.ARead;
import tamagocc.ast.api.AReal;
import tamagocc.ast.api.AReturn;
import tamagocc.ast.api.ASelf;
import tamagocc.ast.api.ASequence;
import tamagocc.ast.api.ASet;
import tamagocc.ast.api.AString;
import tamagocc.ast.api.ATamagoNew;
import tamagocc.ast.api.AThrowException;
import tamagocc.ast.api.AThrowsException;
import tamagocc.ast.api.ATryCatchFinal;
import tamagocc.ast.api.AType;
import tamagocc.ast.api.AVariable;
import tamagocc.ast.api.AVisibility;
import tamagocc.ast.api.AWhile;
import tamagocc.ast.impl.AConstructor;
import tamagocc.ast.impl.AIAffectation;
import tamagocc.ast.impl.AICall;
import tamagocc.ast.impl.AIDocComment;
import tamagocc.ast.impl.AIIdent;
import tamagocc.ast.impl.AIInLabel;
import tamagocc.ast.impl.AIInitialisation;
import tamagocc.ast.impl.AIInlineComment;
import tamagocc.ast.impl.AIInstExpression;
import tamagocc.ast.impl.AIMethod;
import tamagocc.ast.impl.AINew;
import tamagocc.ast.impl.AINoInherit;
import tamagocc.ast.impl.AINot;
import tamagocc.ast.impl.AIOperator;
import tamagocc.ast.impl.AIParameter;
import tamagocc.ast.impl.AIProperty;
import tamagocc.ast.impl.AIRead;
import tamagocc.ast.impl.AIReturn;
import tamagocc.ast.impl.AISequence;
import tamagocc.ast.impl.AIString;
import tamagocc.ast.impl.AIType;
import tamagocc.ast.impl.AIVariable;
import tamagocc.ast.impl.AIVisibility;
import tamagocc.exception.TamagoCCException;
import tamagocc.generator.TamagoCCGeneratorSkel;
import tamagocc.generator.TamagoCCGeneratorTargetLanguage;
import tamagocc.logger.TamagoCCLogger;
import tamagocc.util.NilCollection;
import tamagocc.util.Pair;
import tamagocc.util.TamagoCCIndentator;
import tamagocc.util.TamagoFreshVar;

/**
 * @author Hakim Belhaouari
 *
 */
public class TamagoCCJavaStringSource extends TamagoCCGeneratorTargetLanguage {

	
	private static int num = 0;
	public static String genVariable() {
		return "__temp"+(num++);
	}
	
	public enum PrintResult { COMMENT,BLOCK,INSTRUCTION,EXPRESSION,NOINSTRUCTION }
	
	TamagoCCIndentator indent;
	
	private transient AMethod visit;

	private OutputStream fos; 
	/**
	 * @param entity
	 */
	public TamagoCCJavaStringSource(AEntity entity,OutputStream stream)
		throws TamagoCCException
	{
		super(entity);
	
		visit = null;
		indent = null;
		fos = stream;
		
		try {
			getIndent();
		}
		catch(TamagoCCException te) {
			throw te;
		}
		catch(Exception exc) {
			throw new TamagoCCException(exc);
		}
	}

	private String convertModuleToDirectory() {
		String module = this.target.getModule().getFullName();
		String path = module.replace('.',File.separatorChar);
		return path;
	}
	
	private String generateFilename() {
		String name = target.getName();
		name = (name.substring(0,1).toUpperCase()+name.substring(1));
		TamagoCCLogger.println(3, "////////////////////Sortie GENERER:"+name);
		return name;
	}
	
	/**
	 * @see tamagocc.generator.TamagoCCGeneratorTargetLanguage#getLanguage()
	 */
	public String getLanguage() {
		return "Java 1.5 (beta)";
	}

	/**
	 * @see tamagocc.generator.TamagoCCGeneratorTargetLanguage#getDescription()
	 */
	public String getDescription() {
		return "";
	}

	/**
	 * @see tamagocc.generator.TamagoCCGeneratorTargetLanguage#getAuthor()
	 */
	public String getAuthor() {
		return "Hakim Belhaouari";
	}

	/**
	 * @see tamagocc.generator.TamagoCCGeneratorTargetLanguage#getFinalDestination()
	 */
	public File getFinalDestination() throws TamagoCCException {
		return null;
		/*if(source == null) {
			File rep = new File(directory,convertModuleToDirectory());
			rep.mkdirs();
			source = new File(rep,generateFilename()+".java"); 
			try {
				if(source.createNewFile()) {
					TamagoCCLogger.println(3,"Creation ok of the file : "+source.getAbsolutePath());
				}
				else {
					TamagoCCLogger.println(3,"Already existed of the file : "+source.getAbsolutePath());					
				}
			} catch(IOException ioe) { 
				TamagoCCLogger.println(3,"Exception during the creation of the file : "+source.getAbsolutePath());
				TamagoCCLogger.println(3,"\t Message : "+ioe.getMessage());
				throw new TamagoCCException(ioe);
			}
		}
		return source;*/
	}

	/**
	 * @see tamagocc.generator.TamagoCCGeneratorTargetLanguage#getFinalDestinationStream()
	 */
	public OutputStream getFinalDestinationStream() throws TamagoCCException,IOException{
		if(fos == null) {
			fos = new ByteArrayOutputStream();
		}
		return fos;
	}

	
	private TamagoCCIndentator getIndent()throws TamagoCCException,IOException {
		if(indent == null) {
			indent = new TamagoCCIndentator(getFinalDestinationStream());
		}
		return indent;
	}
	
	/**
	 * @see tamagocc.ast.TamagoCCASTVisitor#visitCodeComment(tamagocc.ast.api.ACodeComment)
	 */
	public Object visitCodeComment(ACodeComment codecomment)
			throws TamagoCCException {
		
		return PrintResult.COMMENT;
	}

	/**
	 * @see tamagocc.ast.TamagoCCASTVisitor#visitDocComment(tamagocc.ast.api.ADocComment)
	 */
	public Object visitDocComment(ADocComment doccomment)
			throws TamagoCCException {
		if(doccomment.getComment().length() > 0) {
			indent.println("/**");
			indent.shiftright();
			indent.println(doccomment.getComment());
			indent.shiftleft();
			indent.println("*/");
		}
		return PrintResult.COMMENT;
	}

	/**
	 * @see tamagocc.ast.TamagoCCASTVisitor#visitEntity(tamagocc.ast.api.AEntity)
	 */
	public Object visitEntity(AEntity entity) throws TamagoCCException {
		if(entity != this.target) {
			throw new TamagoCCException("Integrity of the Generator fail : I can't translate another entity");
		}
		
		
		if(entity.getModule().getFullName().length() > 0) {
			indent.print("package");
			indent.space();
			entity.getModule().visit(this);
			indent.println(";");
			indent.newline();
		}
		
		// Importation(s)
		
		Iterator<ANamespace> ns = entity.getUsedNamespaces();
		while(ns.hasNext()) {
			ANamespace n = ns.next();
			n.visit(this);
		}		
		
		// Declaration
		entity.getComment().visit(this);
		indent.print("public");
		indent.space();
		if(entity.isInterface())
			indent.print("interface");
		else
			indent.print("class");
		indent.space();
		
		indent.print(entity.getName());
		
		if(entity.isParametric()) {
			TamagoCCLogger.println(3, "Parametric Entity detected");
			indent.print("<");
			AType[] types = entity.getParametrizedTypes();
			for(int i = 0; i < types.length; i++) {
				visitType(types[i]);
				if(i < (types.length-1)) indent.print(",");
			}
			indent.print(">");
		}
		else
			TamagoCCLogger.println(3, "Parametric Entity no detected");
		
		// Heritage 
		
		if(!(entity.getInherit() instanceof AINoInherit)) {
			indent.newline();
			indent.shiftright();
			indent.space();
			indent.print("extends");
			indent.space();
			entity.getInherit().visit(this);
			indent.shiftleft();
		}		
		
		// end heritage
		
		// Implementation
		indent.shiftright();
		Iterator<AImplements> impls =entity.getImplements();
		boolean isfirst = true;
		while(impls.hasNext()) {
			AImplements impl = impls.next();
			if(isfirst) {
				isfirst = false;
				indent.newline();
				if(entity.isInterface()) {
					indent.print("extends"); indent.space();
				}else { 
					indent.print("implements"); indent.space();
				}
			}
			impl.visit(this);
			if(impls.hasNext()) {
				indent.print(","); indent.space();
			}
		}
		indent.shiftleft();
		// end implementation
		
		
		indent.newline();
		indent.print("{");
		indent.shiftright();
		// --
		indent.newline();
		indent.print("// Members Variables");
		indent.newline();
		Iterator<AMemberVariable> mvs = entity.getVariablesMembers();
		while(mvs.hasNext()) {
			AMemberVariable amv = mvs.next();
			amv.visit(this);
			indent.newline();
		}
		// --
		indent.forceNewline();
		indent.forceNewline();
		indent.print("// Properties");
		indent.newline();
		for (AProperty property : entity.properties()) {
			property.visit(this);
		}
		// --
		indent.forceNewline();
		indent.forceNewline();
		indent.print("// Methods");
		indent.newline();
		Iterator<AMethod> methods = entity.getMethods();
		while(methods.hasNext()) {
			AMethod method = methods.next();
			method.visit(this);
			if(methods.hasNext()) {
				indent.forceNewline();
				indent.forceNewline();
				indent.forceNewline();
			}
		}
		// --		
		indent.shiftleft();
		indent.println("}");
		
		return PrintResult.BLOCK;
	}

	/**
	 * @see tamagocc.ast.TamagoCCASTVisitor#visitIdent(tamagocc.ast.api.AIdent)
	 */
	public Object visitIdent(AIdent ident) throws TamagoCCException {
		indent.print(ident.getName());
		return PrintResult.EXPRESSION;
	}

	/**
	 * @see tamagocc.ast.TamagoCCASTVisitor#visitInlineComment(tamagocc.ast.api.AInlineComment)
	 */
	public Object visitInlineComment(AInlineComment inlinecomment)
			throws TamagoCCException {
		if(inlinecomment.getComment().length() > 0) {
			indent.print("// ");
			indent.println(inlinecomment.getComment());
		}
		return PrintResult.COMMENT;
	}

	/**
	 * @see tamagocc.ast.TamagoCCASTVisitor#visitLongComment(tamagocc.ast.api.ALongComment)
	 */
	public Object visitLongComment(ALongComment longcomment)
			throws TamagoCCException {
		if(longcomment.getComment().length() > 0) {
			indent.println("/*");
			indent.shiftright();
			indent.print(longcomment.getComment());
			indent.shiftleft();
			indent.newline();
			indent.println("*/");
		}
		return PrintResult.COMMENT;
	}

	/**
	 * @see tamagocc.ast.TamagoCCASTVisitor#visitMemberVariable(tamagocc.ast.api.AMemberVariable)
	 */
	public Object visitMemberVariable(AMemberVariable varmem)
			throws TamagoCCException {
		
		
		varmem.getComment().visit(this);
		varmem.getVisibility().visit(this);
		indent.space();
		varmem.getType().visit(this);
		indent.space();
		varmem.getIdent().visit(this);
		indent.println(";");
		return PrintResult.INSTRUCTION;
	}

	/**
	 * @see tamagocc.ast.TamagoCCASTVisitor#visitMethod(tamagocc.ast.api.AMethod)
	 */
	public Object visitMethod(AMethod method) throws TamagoCCException {
		AMethod save = visit;
		try {
			visit = method;
			method.getComment().visit(this);
			method.getVisibility().visit(this);
			indent.space();
			method.getType().visit(this);
			indent.space();
			method.getIdent().visit(this);

			indent.print("(");
			Iterator<AParameter> parameters = method.getParameters();
			while(parameters.hasNext()) {
				AParameter parameter = (AParameter)parameters.next();
				parameter.visit(this);
				if(parameters.hasNext()) {
					indent.print(", ");
				}
			}
			indent.print(")");


			Iterator<AThrowsException> throwsexception = method.getThrowsException();
			boolean isfirst = true;
			while(throwsexception.hasNext()) {
				if(isfirst) {
					isfirst = false;
					indent.space();
					indent.print("throws");
					indent.space();
				}
				AThrowsException ate = (AThrowsException) throwsexception.next();
				ate.visit(this);

				if(throwsexception.hasNext()) {
					indent.print(", ");
				}
			}

			if(target.isInterface()) {
				indent.println(";");
			}
			else {
				indent.print("{");
				indent.shiftright();
				indent.newline();

				finishInstruction(method.getBody().visit(this));

				indent.shiftleft();
				indent.newline();
				indent.println("}");
			}

			indent.println("");
		}
		finally {
			visit = save;
		}
		return null;
	}

	/**
	 * @see tamagocc.ast.TamagoCCASTVisitor#visitModule(tamagocc.ast.api.AModule)
	 */
	public Object visitModule(AModule module) throws TamagoCCException {
		indent.print(module.getFullName());
		return null;
	}

	/**
	 * @see tamagocc.ast.TamagoCCASTVisitor#visitType(tamagocc.ast.api.AType)
	 */
	public Object visitType(AType type) throws TamagoCCException {
		switch(type.getCategoryType()) {
		case BOOL: indent.print("boolean"); break;
		case INTEGER: indent.print("int"); break;
		case REAL: indent.print("double"); break;
		case VOID: indent.print("void"); break;
		case STRING: indent.print("String"); break;
		case OBJECT: indent.print(type.getType()); break;
		case ARRAY: visitType(type.getArrayType()); indent.print("[]"); break;
		default:
			throw new TamagoCCException("Unknow type with code : "+type.getCategoryType());
		}
		return null;
	}

	/**
	 * @see tamagocc.ast.TamagoCCASTVisitor#visitVisibility(tamagocc.ast.api.AVisibility)
	 */
	public Object visitVisibility(AVisibility visibility)
			throws TamagoCCException {
		switch(visibility.getVisibility()) {
		case AVisibility.PUBLIC:
			indent.print("public");
			break;
		case AVisibility.PRIVATE:
			indent.print("private");
			break;
		case AVisibility.LIMITED:
			indent.print("");
			break;
		case AVisibility.PROTECTED:
			indent.print("protected");
			break;
		}
		return null;
	}

	/**
	 * @see tamagocc.ast.TamagoCCASTVisitor#visitAffectation(tamagocc.ast.api.AAffectation)
	 */
	public Object visitAffectation(AAffectation affectation)
			throws TamagoCCException {
		
		affectation.getVariable().visit(this);
		indent.print(" = ");
		affectation.getInitValeur().visit(this);
		//indent.println(";");
		return PrintResult.INSTRUCTION;
	}

	/**
	 * @see tamagocc.ast.TamagoCCASTVisitor#visitBool(tamagocc.ast.api.ABool)
	 */
	public Object visitBool(ABool bool) throws TamagoCCException {
		if(bool.getValue())
			indent.print("true");
		else
			indent.print("false");
		return PrintResult.EXPRESSION;
	}

	/**
	 * @see tamagocc.ast.TamagoCCASTVisitor#visitCall(tamagocc.ast.api.ACall)
	 */
	public Object visitCall(ACall call) throws TamagoCCException {
		call.getIdent().visit(this);
		indent.print("(");
		Iterator<AExpression> args = call.getArguments();
		while(args.hasNext()) {
			AExpression expr = args.next();
			expr.visit(this);
			if(args.hasNext()) {
				indent.print(", ");
			}
		}
		indent.print(")");
		return PrintResult.EXPRESSION;
	}

	/**
	 * @see tamagocc.ast.TamagoCCASTVisitor#visitIfThenElse(tamagocc.ast.api.AIfThenElse)
	 */
	public Object visitIfThenElse(AIfThenElse ifthenelse)
			throws TamagoCCException {
		Pair<AExpression, AInstruction> f = prepareCutLongExpressionRec(AIType.typeBOOLEAN, ifthenelse.getCondition());
		AExpression condition;
		if(f == null)
			condition = ifthenelse.getCondition();
		else {
			f.r().visit(this);
			condition = f.l();
		}
		
		
		indent.print("if(");
		//ifthenelse.getCondition().visit(this);
		condition.visit(this);
		indent.print(") {");
		indent.shiftright();
		indent.newline();
		finishInstruction(ifthenelse.getCons().visit(this));
		indent.shiftleft();
		indent.newline();
		indent.println("}");
		if(ifthenelse.getAlt().getInstructionType() != AInstruction.NOINSTRUCTION) {
			indent.print("else {");
			indent.shiftright();
			indent.newline();
			finishInstruction(ifthenelse.getAlt().visit(this));
			indent.shiftleft();
			indent.newline();
			indent.println("}");
		}
		
		indent.println("");
		
		return PrintResult.BLOCK;
	}

	/**
	 * @see tamagocc.ast.TamagoCCASTVisitor#visitInLabel(tamagocc.ast.api.AInLabel)
	 */
	public Object visitInLabel(AInLabel inlabel) throws TamagoCCException {
		inlabel.getScope().visit(this);
		indent.print(".");
		inlabel.getSubExpression().visit(this);				
		return PrintResult.EXPRESSION;
	}

	/**
	 * @see tamagocc.ast.TamagoCCASTVisitor#visitInstExpression(tamagocc.ast.api.AInstExpression)
	 */
	public Object visitInstExpression(AInstExpression instexpr)
			throws TamagoCCException {
		instexpr.getExpression().visit(this);
		//indent.println(";");		
		return PrintResult.INSTRUCTION;
	}

	/**
	 * @see tamagocc.ast.TamagoCCASTVisitor#visitInteger(tamagocc.ast.api.AInteger)
	 */
	public Object visitInteger(AInteger integer) throws TamagoCCException {
		indent.print(""+integer.getValue());
		return PrintResult.EXPRESSION;
	}

	/**
	 * @see tamagocc.ast.TamagoCCASTVisitor#visitNoExpression(tamagocc.ast.api.ANoExpression)
	 */
	public Object visitNoExpression(ANoExpression noexpr)
			throws TamagoCCException {
		indent.print("");
		return PrintResult.NOINSTRUCTION;
	}

	/**
	 * @see tamagocc.ast.TamagoCCASTVisitor#visitNot(tamagocc.ast.api.ANot)
	 */
	public Object visitNot(ANot not) throws TamagoCCException {
		indent.print("!");
		if(not.getSubExpression() instanceof TOperator)
			indent.print("(");
		not.getSubExpression().visit(this);
		if(not.getSubExpression() instanceof TOperator)
			indent.print(")");
		return PrintResult.EXPRESSION;
	}

	
	private AExpression equalOper(boolean eq,AExpression g, AExpression d) throws TamagoCCException {
		// premier cas particulier si l'expression null est l'un des deux
		if(g.getExpressionType() == AExpression.NIL || d.getExpressionType() == AExpression.NIL) {
			TamagoCCLogger.infoln(5,"\tOne operands is null expression");
			ArrayList<AExpression> operands = new ArrayList<AExpression>();
			operands.add(g);
			operands.add(d);
			if(eq)
				return new AIOperator(TOpeName.opEg,operands);
			else
				return new AIOperator(TOpeName.opNe,operands);
		}
		
		if(isSimpleType(g) && isSimpleType(d)) {
			TamagoCCLogger.infoln(5,"\t2 simple types");
			ArrayList<AExpression> operands = new ArrayList<AExpression>();
			operands.add(g);
			operands.add(d);
			if(eq)
				return new AIOperator(TOpeName.opEg,operands);
			else
				return new AIOperator(TOpeName.opNe,operands);
		}
		else if(isSimpleType(g) && !isSimpleType(d)) {
			TamagoCCLogger.infoln(5,"\tsimple type on left, and not on right");
			AICall call = new AICall(ident("equals"));
			call.addArgument(g);
			AInLabel inlabel = new AIInLabel(d,call);
			
			if(eq)
				return inlabel;
			else
				return new AINot(inlabel);
		}
		else {
			TamagoCCLogger.infoln(5,"\tother cases");
			AICall call = new AICall(ident("equals"));
			call.addArgument(d);
			AInLabel inlabel = new AIInLabel(g,call);
			
			if(eq)
				return inlabel;
			else
				return new AINot(inlabel);
		}
	}

	private AIdent ident(String string) {
		return new AIIdent(string);
	}

	private boolean isSimpleType(AExpression d) {
		switch(d.getExpressionType()) {
		case AExpression.INSTATE:
		case AExpression.BOOL:
		case AExpression.INT:
		case AExpression.NOT:
		case AExpression.REAL:
		case AExpression.NIL:// cas particulier du null
			TamagoCCLogger.infoln(5,"\tSimple expression detected");
			return true;
		case AExpression.CALL:
			TamagoCCLogger.infoln(5,"\tCall expression detected");
			ACall call = (ACall)d;
			for (AMethod method : (target).getMetodsByName(call.getIdent().getName())) {
				if(method.getParameterNumber() == call.getArgCount()) {
					TamagoCCLogger.println(5,"\tmethod found");
					return isSimpleType(method.getType());
				}
			}
			TamagoCCLogger.println(5,"\tmethod unfound: "+call.getIdent().getName());
			break;
		case AExpression.OPERATOR: {
			AOperator ope = (AOperator)d;
			boolean res = false;
			for(int i=0;i < ope.getArity();i++) {
				res = res || isSimpleType(ope.getOperand(i));
			}
			TamagoCCLogger.println(5,"*Warning* expression not fully implemented (0x25d689)");
			return res;
		}
		case AExpression.READ:
			TamagoCCLogger.infoln(5,"\tProperty expression detected in "+target.getName()+": "+((ARead)d).getName());
			AProperty prop = (target).searchProperty(((ARead)d).getName());
			if(((ARead)d).hasIndex()) {
				AType type = prop.getType();
				if(type.isArray())
					return isSimpleType(type.getArrayType());
				else {
					TamagoCCLogger.println(5,"*Warning* read property on an array not fully tested");
					return true;
				}
					
			}
			else
				return isSimpleType(prop.getType());
		case AExpression.VARIABLE:
			AVariable var = (AVariable)d;
			if(visit == null) {
				TamagoCCLogger.infoln(5,"\tUntyped variable detected: "+var.getIdent().getName());
				TamagoCCLogger.infoln(5,"\t  I deal as a primitive type");
				return true;
			}
			Iterator<AParameter> parameters = visit.getParameters();
			while(parameters.hasNext()) {
				AParameter param = parameters.next();
				if(var.getIdent().equals(param.getIdent())) {
					if(var.hasIndex()) {
						AType type = param.getType();
						if(type.isArray())
							return isSimpleType(type.getArrayType());
						else {
							TamagoCCLogger.println(5,"*Warning* variable on an array not fully tested");
							return true;
						}
							
					}
					else
						return isSimpleType(param.getType());
				}
			}
			TamagoCCLogger.infoln(5,"\tUnfind variable: "+var.getIdent().getName());
			TamagoCCLogger.infoln(5,"\t  I deal as a primitive type");
			return true;
		case AExpression.INLABEL:
			
			// TODO verifier les scopes de toutes les variables.
			//return isSimpleType(((GInLabel)d).getSubExpression());
			return true; // au pire on inverse l'expression
		}
		return false;
	}

	private boolean isSimpleType(AType type) {
		switch(type.getCategoryType()) {
		case BOOL:
		case INTEGER:
		case REAL:
			return true;
		default:
			return false;
		}
	}
	
	
	/**
	 * @see tamagocc.ast.TamagoCCASTVisitor#visitOperator(tamagocc.ast.api.AOperator)
	 */
	public Object visitOperator(AOperator operator) throws TamagoCCException {
		if(operator.getArity() == 2) {
			AExpression g = operator.getOperand(0);
			AExpression d = operator.getOperand(1);
			TamagoCCLogger.println(5, "Binary equality operator between "+g+" and "+d);
			AExpression expr = null;
			if((operator.getOperator().getID() == TOpeName.IMPLY)) {
				AINot not = new AINot(g);
				AIOperator ope = new AIOperator(TOpeName.opOr);
				ope.addOperand(not);
				ope.addOperand(d);
				ope.visit(this);
				return ope;
			}
			
			if((operator.getOperator().getID() == TOpeName.EQUIV)) {
				AIOperator ope = new AIOperator(TOpeName.opAnd);
				AIOperator ltor = new AIOperator(TOpeName.opImply);
				ltor.addOperand(g);
				ltor.addOperand(d);
				
				AIOperator rtol = new AIOperator(TOpeName.opImply);
				rtol.addOperand(d);
				rtol.addOperand(g);
				
				ope.addOperand(ltor);
				ope.addOperand(rtol);
				ope.visit(this);
				return ope;
			}
			
			if ((operator.getOperator().getID() == TOpeName.EG)) 
				expr = equalOper(true, g, d);
			else if ((operator.getOperator().getID() == TOpeName.NE)) 
				expr = equalOper(false, g, d);
			if(!(expr instanceof AOperator) && expr != null)
				return expr.visit(this);
		}
		
		Iterator<AExpression> operands = operator.getOperands();
		if(operator.getArity() > 1)
			indent.print("(");
		while(operands.hasNext()) {
			AExpression expr = operands.next();
			expr.visit(this);
			if(operands.hasNext()) {
				//indent.print(")");
				indent.space();
				indent.print(operator(operator.getOperator()));
				indent.space();
				//indent.print("(");
			}
		}
		if(operator.getArity() > 1)
			indent.print(")");
		return PrintResult.EXPRESSION;
	}

	
	private String operator(TOpeName op) throws TamagoCCException {
		switch(op.getID()) {
		case TOpeName.EG: return("==");
        case TOpeName.NE: return( "!=");
        case TOpeName.INF: return( "<");
        case TOpeName.INFEG: return( "<=");
        case TOpeName.SUP: return( ">");
        case TOpeName.SUPEG: return( ">=");
        case TOpeName.PLUS: return( "+");
        case TOpeName.MINUS: return( "-");
        case TOpeName.TIMES: return( "*");
        case TOpeName.QUO: return( "/");
        case TOpeName.MOD: return ("%");
        case TOpeName.AND: return( "&&");
        case TOpeName.OR: return( "||");
        case TOpeName.XOR: return( "^");
        default:
            throw new TamagoCCException("GenJavaSource : unknow operator<"+op.getID()+">");
		}
	}

	/**
	 * @see tamagocc.ast.TamagoCCASTVisitor#visitReal(tamagocc.ast.api.AReal)
	 */
	public Object visitReal(AReal real) throws TamagoCCException {
		indent.print(""+real.getValue());
		return PrintResult.EXPRESSION;
	}
	
	public void finishInstruction(Object r) throws TamagoCCException {
		PrintResult pr = (PrintResult)r;
		switch(pr) {
		case BLOCK:
			break;
		case EXPRESSION:
			TamagoCCLogger.println(5, "The parsed element is an expression in position of an instruction");
		case INSTRUCTION:
			indent.println(";");
			break;
		case NOINSTRUCTION:
			TamagoCCLogger.println(5, "The parsed element is unknown");
			break;
		}
	}

	/**
	 * @see tamagocc.ast.TamagoCCASTVisitor#visitSequence(tamagocc.ast.api.ASequence)
	 */
	public Object visitSequence(ASequence sequence) throws TamagoCCException {
		Iterator<AInstruction> seqs = sequence.getInstructions();
		while(seqs.hasNext()) {
			AInstruction inst = (AInstruction)seqs.next();
			finishInstruction(inst.visit(this));			
		}
		return PrintResult.BLOCK;
	}

	/**
	 * @see tamagocc.ast.TamagoCCASTVisitor#visitString(tamagocc.ast.api.AString)
	 */
	public Object visitString(AString string) throws TamagoCCException {
		indent.print("\""+string.getValue()+"\"");
		return PrintResult.EXPRESSION;
	}

	/**
	 * @see tamagocc.ast.TamagoCCASTVisitor#visitVariable(tamagocc.ast.api.AVariable)
	 */
	public Object visitVariable(AVariable variable) throws TamagoCCException {
		variable.getIdent().visit(this);
		if(variable.hasIndex()) {
			indent.print("[");
			variable.getIndex().visit(this);
			indent.print("]");
		}			
		return PrintResult.EXPRESSION;
	}
	
	
	private ASequence convertInitToAffectAfterPreparationCutLongExpression(ASequence s) {
		AISequence seq = new AISequence();
		Iterator<AInstruction> inits = s.getInstructions();
		while(inits.hasNext()) {
			AInitialisation init = (AInitialisation) inits.next();
			seq.addInstruction(new AIAffectation(new AIVariable(init.getIdent()), init.getInitial()));
		}
		return seq;
	}

	/**
	 * @see tamagocc.ast.TamagoCCASTVisitor#visitWhile(tamagocc.ast.api.AWhile)
	 */
	public Object visitWhile(AWhile w) throws TamagoCCException {
		Pair<AExpression, AInstruction> f = prepareCutLongExpressionRec(AIType.typeBOOLEAN, w.getCondition());
		AExpression condition;
		if(f == null)
			condition = w.getCondition();
		else {
			f.r().visit(this);
			condition = f.l();
		}
		
		indent.print("while(");
		//w.getCondition().visit(this);
		condition.visit(this);
		indent.print(") {");
		indent.shiftright();
		indent.newline();
		finishInstruction(w.getBody().visit(this));
		if(f != null) {
			convertInitToAffectAfterPreparationCutLongExpression((ASequence)f.r()).visit(this);
		}
		indent.shiftleft();
		indent.newline();
		indent.print("}");
		return PrintResult.BLOCK;
	}

	/**
	 * @see tamagocc.ast.TamagoCCASTVisitor#visitNoInstruction(tamagocc.ast.api.ANoInstruction)
	 */
	public Object visitNoInstruction(ANoInstruction noinst)
			throws TamagoCCException {
		noinst.getComment().visit(this);
		return PrintResult.NOINSTRUCTION;
	}

	/**
	 * @see tamagocc.ast.TamagoCCASTVisitor#visitParameter(tamagocc.ast.api.AParameter)
	 */
	public Object visitParameter(AParameter parameter) throws TamagoCCException {
		parameter.getType().visit(this);
		indent.space();
		parameter.getIdent().visit(this);
		return null;
	}

	/**
	 * @see tamagocc.ast.TamagoCCASTVisitor#visitTryCatchFinal(tamagocc.ast.api.ATryCatchFinal)
	 */
	public Object visitTryCatchFinal(ATryCatchFinal trycatchfinal)
			throws TamagoCCException {
		indent.print("try {");
		indent.shiftright();indent.newline();
		finishInstruction(trycatchfinal.getTryBody().visit(this));
		indent.shiftleft();indent.newline();
		indent.println("}");
		Iterator<ACatchBlock> blocks = trycatchfinal.getCatchBlock().iterator();
		while(blocks.hasNext()) {
			ACatchBlock block = blocks.next();
			block.visit(this);
		}
		
		if(trycatchfinal.getFinalBody().getInstructionType() != AInstruction.NOINSTRUCTION) {
			indent.print("finally {");
			indent.shiftright();indent.newline();
			finishInstruction(trycatchfinal.getFinalBody().visit(this));
			indent.shiftleft();indent.newline();
			indent.print("}");
		}
		
		return PrintResult.BLOCK;
	}

	/**
	 * @see tamagocc.ast.TamagoCCASTVisitor#visitThrowException(tamagocc.ast.api.AThrowException)
	 */
	public Object visitThrowException(AThrowException throwexception)
			throws TamagoCCException {
		indent.print("throw new ");
		throwexception.getType().visit(this);
		indent.print("(");
		Iterator<AExpression> args = throwexception.getArguments();
		while(args.hasNext()) {
			AExpression expr = args.next();
			expr.visit(this);
			if(args.hasNext()) {
				indent.print(", ");
			}
		}
		indent.print(")");
		//indent.print(";");
		return PrintResult.INSTRUCTION;
	}

	/**
	 * @see tamagocc.ast.TamagoCCASTVisitor#visitCatchBlock(tamagocc.ast.api.ACatchBlock)
	 */
	public Object visitCatchBlock(ACatchBlock block)
			throws TamagoCCException {
		indent.print("catch(");
		block.getExceptionType().visit(this);
		indent.space();
		block.getIdent().visit(this);
		indent.print(") {");
		indent.shiftright(); indent.newline();
		finishInstruction(block.getBody().visit(this));
		indent.shiftleft();indent.newline();
		indent.println("}");
		return PrintResult.BLOCK;
	}

	public Object visitInherit(AInherit inherit) throws TamagoCCException {
		inherit.getModule().visit(this);
		indent.print(".");
		indent.print(inherit.getName());		
		return null;
	}

	public Object visitImplement(AImplements impl) throws TamagoCCException {
		impl.getModule().visit(this);
		indent.print(".");
		indent.print(impl.getName());
		return null;
	}

	public Object visitThrowsException(AThrowsException throwsexception) throws TamagoCCException {
		throwsexception.getType().visit(this);
		return null;
	}

	public Object visitNamespace(ANamespace namespace) throws TamagoCCException {
		indent.print("import ");
		if(namespace.isStatic())
			indent.print("static ");
		indent.print(namespace.getNamespace());
		if(namespace.isPackage()) {
			indent.print(".*");
		}
		else {
			indent.print(".");
			indent.print(namespace.getType());
		}
		indent.println(";");
		return null;
	}

	public Object visitReturn(AReturn r) throws TamagoCCException {
		
		r.getComment().visit(this);
		indent.print("return ");
		r.getExpression().visit(this);
		//indent.print(";");
		return PrintResult.EXPRESSION;
	}

	public Object visitConstructor(AConstructor constructor) throws TamagoCCException {
		constructor.getComment().visit(this);
		constructor.getVisibility().visit(this);
		indent.space();
		indent.print(target.getName());
		indent.space();		
		indent.print("(");
		Iterator<AParameter> parameters = constructor.getParameters();
		while(parameters.hasNext()) {
			AParameter parameter = parameters.next();
			parameter.visit(this);
			if(parameters.hasNext()) {
				indent.print(", ");
			}
		}
		indent.print(")");
		
		
		Iterator<AThrowsException> throwsexception = constructor.getThrowsException();
		boolean isfirst = true;
		while(throwsexception.hasNext()) {
			if(isfirst) {
				isfirst = false;
				indent.space();
				indent.print("throws");
				indent.space();
			}
			AThrowsException ate = (AThrowsException) throwsexception.next();
			ate.visit(this);
			
			if(throwsexception.hasNext()) {
				indent.print(", ");
			}
		}
		
		if(target.isInterface()) {
			throw new TamagoCCException("Impossible to define a constructor in an interface!");
		}
		else {
			indent.newline();
			indent.print("{");
			indent.shiftright();
			indent.newline();
			
			constructor.getBody().visit(this);
			
			indent.shiftleft();
			indent.newline();
			indent.println("}");
		}
		
		
		return null;
		
	}

	public Object visitNew(ANew newobjet) throws TamagoCCException {
		indent.print("new ");
		newobjet.getType().visit(this);
		indent.print("(");
		Iterator<AExpression> arguments = newobjet.getArguments();
		while(arguments.hasNext()) {
			AExpression argument= arguments.next();
			argument.visit(this);
			if(arguments.hasNext())
				indent.print(", ");
		}
		indent.print(")");
		return PrintResult.EXPRESSION;
	}

	public Object visitTamagoNew(ATamagoNew tamagonew) throws TamagoCCException {
		indent.print("(");
		indent.print(tamagonew.getModule().getFullName());
		indent.print(".");
		tamagonew.getType().visit(this);
		indent.print(") ");
		
		indent.print("TamagoCC.");
		switch(tamagonew.getEntityType()) {
		case ATamagoNew.COMPONENT:
			indent.print("LookUp");
			break;
		case ATamagoNew.COMPOSITE:
			indent.print("LookUp");
			break;
		case ATamagoNew.ASSEMBLY:
			indent.print("LookUpAssembly");
			break;
		}
			
		indent.print("(\"");
		
		
		indent.print(tamagonew.getModule().getFullName());
		if(tamagonew.getType().getType().length() > 0) {
			indent.print(".");
			tamagonew.getType().visit(this);
		}
		
		
		if(!tamagonew.useDefaultPercolator()) {
			indent.print("\",\"");
			indent.print(tamagonew.getPercolatorName());
		}
		indent.print("\")");
		return PrintResult.EXPRESSION;
	}

	public Object visitSelf(ASelf self) throws TamagoCCException {
		indent.print("this");
		return PrintResult.EXPRESSION;
	}

	private static final int CUT_LONG_LINE = 50;

	private Pair<AExpression, AInstruction> prepareCutLongExpression(AType type, AExpression e) throws TamagoCCException {
		if((e instanceof AOperator) && (e != null)) {
			AOperator op = (AOperator)e;
			AISequence seq = new AISequence();
			AIOperator res = new AIOperator(op.getOperator());
			if(((AOperator)e).getArity() > CUT_LONG_LINE) {
				AIInlineComment comment = new AIInlineComment("Line too long and cut by Tamago-CC");
				AIOperator tmp = null;
				for(int i = 0; i < op.getArity(); i++) {
					if(i % CUT_LONG_LINE == 0) {
						tmp = new AIOperator(op.getOperator());
						AIdent vartmp = ident(TamagoFreshVar.Default.getName("__tamago_cutline"));
						AIInitialisation init = new AIInitialisation(vartmp, type,tmp); 
						if(seq.size() == 0) 
							init.setComment(comment);
						seq.addInstruction(init);
						res.addOperand(new AIVariable(vartmp));
					}

					Pair<AExpression, AInstruction> rec = prepareCutLongExpressionRec(type, op.getOperand(i));
					if(rec == null)
						tmp.addOperand(op.getOperand(i));
					else {
						seq.addInstruction(rec.r());
						tmp.addOperand(rec.l());
					}
				}
				return new Pair<AExpression, AInstruction>(res, seq);
			}
			else {
				boolean modif = false;
				for(int i = 0; i < op.getArity(); i++) {
					Pair<AExpression, AInstruction> rec = prepareCutLongExpressionRec(type, op.getOperand(i));
					if(rec == null)
						res.addOperand(op.getOperand(i));
					else {
						seq.addInstruction(rec.r());
						res.addOperand(rec.l());
						modif = true;
					}
				}
				if(modif)
					return new Pair<AExpression, AInstruction>(res, seq);
				else
					return null;
			}
		}
		else if((e instanceof ANot) && (e != null)) {
			ANot not = (ANot)e;
			Pair<AExpression, AInstruction> cut = prepareCutLongExpression(AIType.typeBOOLEAN, not.getSubExpression());
			if(cut == null) {
				return null;
			}
			else {
				return new Pair<AExpression, AInstruction>(new AINot(cut.getL()), cut.getR());
			}
		}
		else
			return null;
			
			
		//throw new TamagoCCException("not implemented");
	}
	
	private Pair<AExpression, AInstruction> prepareCutLongExpressionRec(AType type, AExpression e) throws TamagoCCException {
		AISequence seq = new AISequence();
		AExpression last = null;
		Pair<AExpression, AInstruction> pair = null;
		pair = prepareCutLongExpression(type, e);
		while(pair != null) {
			seq.addSequence((AISequence) pair.r());
			last = pair.l();
			pair = prepareCutLongExpression(type, last);
		}
		pair = new Pair<AExpression, AInstruction>(last, seq);
		if(last == null)
			return null;
		else
			return pair;
	}
	
	
	public Object visitInitialisation(AInitialisation init) throws TamagoCCException {
		Pair<AExpression, AInstruction> res = prepareCutLongExpressionRec(init.getType(),init.getInitial());
		
		if(res == null) {
			init.getComment().visit(this);
			init.getType().visit(this);
			indent.space();
			init.getIdent().visit(this);

			if(!(init.getInitial() instanceof ANoExpression)) {
				indent.print(" = ");
				init.getInitial().visit(this);
			}
		}
		else {
			res.r().visit(this);
			init.getComment().visit(this);
			init.getType().visit(this);
			indent.space();
			init.getIdent().visit(this);
			indent.print(" = ");
			res.l().visit(this);
		}
		//indent.println(";");
		return PrintResult.INSTRUCTION;
	}

	public Object visitConvertType(AConvertType ct) throws TamagoCCException {
		indent.print("(");indent.print("(");
		ct.getNewType().visit(this);
		indent.print(")");
		indent.space();
		ct.getExprToConvert().visit(this);
		indent.print(")");
		
		return PrintResult.NOINSTRUCTION;
	}

	public Object visitNewArray(ANewArray newarray) throws TamagoCCException {
		indent.print("new ");
		newarray.getType().visit(this);
		indent.print("[");
		indent.print(""+newarray.getTaille());
		indent.print("]");
		return PrintResult.EXPRESSION;
	}

	public Object visitNil(ANil nil) throws TamagoCCException {
		indent.print("null");
		return PrintResult.EXPRESSION;
	}

	public Object visitFor(AFor f) throws TamagoCCException {
		indent.print("for(");
		f.getAffectation().visit(this);
		indent.print(";");
		f.getCondition().visit(this);
		indent.print(";");
		f.getIncrement().visit(this);
		indent.print(") {");
		indent.shiftright();
		indent.newline();
		finishInstruction(f.getBody().visit(this));
		indent.shiftleft();
		indent.newline();
		indent.println("}");
		return PrintResult.BLOCK;
	}

	public Object visitForeach(AForeach f) throws TamagoCCException {
		/* TODO : passer en java 1.5
		 * 
		 * ArrayList __set0 = new ArrayList();
		 * __set0.add("toto");
		 * __set0.add("titi");
		 * 
		 * int i;
		 * for(i = 0;i < __set0.size();i++) {
		 * 	Type var = (Type)__set0.get(i); 
		 *  BODY
		 * } 		
		 */
		/*
		AIVariable set = new AIVariable(new AIIdent(f.getSet().getName()));
		// Preparation du set
		finishInstruction(f.getSet().visit(this));
		
		
		AIIdent ident = new AIIdent(genVariable());
		AIVariable var = new AIVariable(ident);
		AIInitialisation init = new AIInitialisation(ident,AIType.typeINTEGER);
		
		AICall call = new AICall(new AIIdent("size"));
		AIInLabel inset = new AIInLabel(set,call);
		AIAffectation first = new AIAffectation(var, new AIInteger(0));
		
		AIOperator cond = new AIOperator(TOpeName.opInf);
		cond.addOperand(var);
		cond.addOperand(inset);
		
		AIOperator incr = new AIOperator(TOpeName.opPlus);
		incr.addOperand(var);
		incr.addOperand(new  AIInteger(1));
		AIAffectation affectincr = new AIAffectation(var,incr);
		
		// la fonction get necessite un traitement pour l'outboxing
		AICall callget = new AICall(new AIIdent("get"));
		callget.addArgument(var);
		AIInLabel inset2 = new AIInLabel(set,callget);
		
		AIConvertType ct;		
		AIInitialisation iterablevar = null;
		
		switch(f.getVariableType().getCategoryType()) {
		case OBJECT: // on fait rien c le role du compilateur
		case STRING:
			ct = new AIConvertType(f.getVariableType(),inset2);
			iterablevar = new AIInitialisation(f.getVariable().getIdent(),f.getVariableType(),ct);
			break;
		case BOOL: 
			{// outboxing
				ct = new AIConvertType(new AIType(CatType.OBJECT,"Boolean"),inset2);
				AICall callvalue = new AICall(new AIIdent("booleanValue"));
				AIInLabel inset3 = new AIInLabel(ct,callvalue);
				iterablevar = new AIInitialisation(f.getVariable().getIdent(),f.getVariableType(),inset3);
				break;
			}
		case INTEGER: 
			{
				ct = new AIConvertType(new AIType(CatType.OBJECT,"Integer"),inset2);
				AICall callvalue = new AICall(new AIIdent("intValue"));
				AIInLabel inset3 = new AIInLabel(ct,callvalue);
				iterablevar = new AIInitialisation(f.getVariable().getIdent(),f.getVariableType(),inset3);
				break;
			}
		case REAL: 
			{
				ct = new AIConvertType(new AIType(CatType.OBJECT,"Double"),inset2);
				AICall callvalue = new AICall(new AIIdent("doubleValue"));
				AIInLabel inset3 = new AIInLabel(ct,callvalue);
				iterablevar = new AIInitialisation(f.getVariable().getIdent(),f.getVariableType(),inset3);
				break;
			}
		case VOID:
			throw new TamagoCCException("Void type detected in foreach instruction");	
		}
		
		
		AISequence body = new AISequence();
		
		body.addInstruction(iterablevar);
		body.addInstruction(f.getBody());
		
		AIFor unfor = new AIFor(first,cond,affectincr,body);
		
		AISequence renvoie = new AISequence();
		renvoie.addInstruction(init);
		renvoie.addInstruction(unfor);		
		
		return renvoie.visit(this);
		
		*/
		// version V2
		/* 
		 * for(Type var : __set0) {
		 * 	BODY
		 * } 		
		 */
		indent.print("for(");
		f.getVariableType().visit(this);
		indent.space();
		f.getVariable().visit(this);
		indent.print(" : ");
		f.getCollection().visit(this);
		indent.print(") {");
		indent.shiftright();
		indent.newline();
		finishInstruction(f.getBody().visit(this));
		indent.shiftleft();
		indent.newline();
		indent.println("}");
		return PrintResult.BLOCK;
	}
	
	
	public Object visitSet(ASet set) throws TamagoCCException {
		AIType type = (AIType) AIType.generateType("java.util.ArrayList");
		AIIdent ident = new AIIdent(set.getName()); 
		AIVariable varset = new AIVariable(ident);
		AINew newobj = new AINew(type);
		AIInitialisation initset = new AIInitialisation(ident,type,newobj);
		AISequence renvoie = new AISequence();
		renvoie.addInstruction(initset);
				
		for (AExpression e : set.getExpressionCollection()) {
			AICall addcall = new AICall(new AIIdent("add"));
			addcall.addArgument(e);
			AIInLabel inarray = new AIInLabel(varset,addcall);
			renvoie.addInstruction(new AIInstExpression(inarray));
		}
		return renvoie.visit(this);
	}

	public Object visitLanguageExpr(ALanguageExpr languageExpr)
			throws TamagoCCException {
		indent.print(languageExpr.getExpression());
		return PrintResult.EXPRESSION;
	}

	public Object visitProperty(AIProperty property) throws TamagoCCException {
		if(property.emptyBody()) {
			String part = propertyToMethod(property.getName());
			AType type = property.getType();
			AVisibility visibility = AIVisibility.PUBLIC_VISIBILITY;
			
			if(property.canRead()) {
				AIdent name = ident("get"+part);
				ADocComment comment = new AIDocComment("Getter of the property "+ property.getName());
				AIMethod getter = new AIMethod(AMethod.IMPLEMENTED,name,type,visibility);
				getter.setComment(comment);
				getter.setBody(TamagoCCGeneratorSkel.emptyBody(getter));
				getter.visit(this);
			}
			
			if(property.canWrite()) {
				AIdent name = ident("set"+part);
				AType vtype = (AIType) AIType.generateType("void");
				ADocComment comment = new AIDocComment("Setter of the property "+ property.getName());
				AIMethod setter = new AIMethod(AMethod.IMPLEMENTED,name,vtype,visibility);
				AIParameter parameter = new AIParameter(ident(property.getName()),type);
				setter.addParameters(parameter);
				
				setter.setComment(comment);
				setter.setBody(TamagoCCGeneratorSkel.emptyBody(setter));
				setter.visit(this);
			}
		}
		else {
			String part = propertyToMethod(property.getName());
			AType type = property.getType();
			AVisibility visibility = AIVisibility.PUBLIC_VISIBILITY;
			AMemberVariable att = target.searchAttribute("tamago_decorated_component");
			
			if(property.canRead()) {
				AIdent name = ident("get"+part);
				ADocComment comment = new AIDocComment("Getter of the property "+ property.getName());
				AIMethod getter = new AIMethod(AMethod.IMPLEMENTED,name,type,visibility);
				
				// body of the method
				AICall callsubmeth = new AICall(name);
				AIInLabel insubcomponent = new AIInLabel(att.getCallMe(),callsubmeth);
				AIReturn ret = new AIReturn(insubcomponent);
				getter.setBody(ret);
				// end of body
				
				
				getter.setComment(comment);
				getter.visit(this);
			}
			
			if(property.canWrite()) {
				AIdent name = ident("set"+part);
				AType vtype = (AIType) AIType.generateType("void");
				ADocComment comment = new AIDocComment("Setter of the property "+ property.getName());
				AIMethod setter = new AIMethod(AMethod.IMPLEMENTED,name,vtype,visibility);
				AIParameter parameter = new AIParameter(ident(property.getName()),type);
				setter.addParameters(parameter);
				
				// set the property
				AICall setprop = new AICall(name);
				setprop.addArgument(new AIVariable(parameter.getIdent()));
				AIInLabel insub = new AIInLabel(att.getCallMe(),setprop);
				setter.setBody(new AIInstExpression(insub));
				
				// end of the body
				
				setter.setComment(comment);
				setter.visit(this);
			}
		}
		return null;
	}

	protected static String propertyToMethod(String name) {
		return (name.substring(0,1).toUpperCase()+name.substring(1));
	}
	
	public Object visitRead(AIRead read) throws TamagoCCException {
		AICall variable = new AICall(ident("get"+propertyToMethod(read.getName())),new NilCollection<AExpression>());
		variable.visit(this);
		if(read.hasIndex()) {
			indent.print("[");
			read.getIndex().visit(this);
			indent.print("]");
		}	
		return PrintResult.EXPRESSION; 
	}

	@Override
	public Object visitInState(AInState instate) throws TamagoCCException {
		AICall callInState = new AICall(ident("isInState"));
		for (String state : instate.getStates()) {
			callInState.addArgument(new AIString(state));
		}
		return callInState.visit(this);
	}

	@Override
	public Object visitIsBound(AIsBound aiIsBound) throws TamagoCCException {
		AICall callIsBound = new AICall(ident("isBound"));
		callIsBound.addArgument(new AIString(aiIsBound.getLabel()));
		return callIsBound.visit(this);
	}
}
