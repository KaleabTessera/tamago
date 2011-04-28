/**
 * 
 */
package tamagocc.util.ast;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Hashtable;
import java.util.Stack;
import java.util.Map.Entry;

import tamagocc.ast.api.AVariable;
import tamagocc.util.Pair;

/**
 * @author Hakim Belhaouari
 *
 */
public class ASTMemory {

	private static final long serialVersionUID = 2017202830021527924L;

	private Stack<Collection<Pair<String, ASTValue>>> frames;
	private Hashtable<String, ASTValue> mem;
	
	
	/**
	 * 
	 */
	public ASTMemory() {
		frames = new Stack<Collection<Pair<String,ASTValue>>>();
		mem = new Hashtable<String, ASTValue>();
		beginFrame();
	}

	/**
	 * @param initialCapacity
	 */
	public ASTMemory(int initialCapacity) {
		mem = new Hashtable<String, ASTValue>(initialCapacity);
		frames = new Stack<Collection<Pair<String,ASTValue>>>();
		beginFrame();
	}

	/**
	 * @param initialCapacity
	 * @param loadFactor
	 */
	public ASTMemory(int initialCapacity, float loadFactor) {
		mem = new Hashtable<String, ASTValue>(initialCapacity, loadFactor);
		frames = new Stack<Collection<Pair<String,ASTValue>>>();
		beginFrame();
	}
	
	public ASTValue values(String var) throws ASTInterpreterException {
		if(mem.containsKey(var)) {
			return mem.get(var);
		}
		else
			throw new ASTInterpreterException("Unkown variable: "+var);
	}
	
	public ASTValue values(AVariable var) throws ASTInterpreterException {
		return values(var.getIdent().getName());
	}
	
	public void initialize(String var, ASTValue val) throws ASTInterpreterException {
		mem.put(var,val);
	}
	
	public void initialize(AVariable var, ASTValue val) throws ASTInterpreterException {
		initialize(var.getIdent().getName(),val);
	}
	
	public void affect(String var, ASTValue val) throws ASTInterpreterException {
		if(mem.containsKey(var)) {
			mem.put(var, val);
		}
		else
			throw new ASTInterpreterException("Affectation on unknown variable: "+var);
	}
	
	public void affect(AVariable var, ASTValue val) throws ASTInterpreterException {
		affect(var.getIdent().getName(), val);
	}

	public void beginFrame() {
		ArrayList<Pair<String,ASTValue>> list = new ArrayList<Pair<String,ASTValue>>();
		
		for (Entry<String, ASTValue> object : mem.entrySet()) {
			Pair<String, ASTValue> pair = new Pair<String, ASTValue>(object.getKey(),object.getValue());
			list.add(pair);
		}
		frames.push(list);
	}
	
	public void removeLastFrame() {
		Collection<Pair<String,ASTValue>> list = frames.pop();
		mem.clear();
		for (Pair<String, ASTValue> pair : list) {
			mem.put(pair.l(), pair.r());
		}
	}
}
