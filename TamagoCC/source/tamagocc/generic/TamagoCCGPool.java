/**
 * 
 */
package tamagocc.generic;

import java.util.Hashtable;

import tamagocc.api.TTamagoEntity;
import tamagocc.exception.TamagoCCException;
import tamagocc.generic.api.GModule;
import tamagocc.generic.api.GTamagoEntity;
import tamagocc.util.TamagoCCPool;

/**
 * This pool contains the second intermediate language of contracts. In this second language normally 
 * TamagoCC has resolved problems of type and some other information.
 * 
 * @author Hakim Belhaouari
 */
public class TamagoCCGPool {

	private Hashtable<String,GTamagoEntity> tables;
	private TamagoCCPool pool;
	
	private static TamagoCCGPool vDefaultGPool = new TamagoCCGPool();
	public static TamagoCCGPool getDefaultTamagoCCGPool() {
		return vDefaultGPool;
	}
	
	public static TamagoCCGPool setDefaultTamagoCCGPool(TamagoCCGPool pool) {
		TamagoCCGPool old = vDefaultGPool;
		vDefaultGPool = pool;
		return old;
	}

	public TamagoCCGPool() {
		super();
		tables = new Hashtable<String,GTamagoEntity>();
		this.pool = TamagoCCPool.getDefaultPool();
	}

	
	/**
	 * 
	 */
	public TamagoCCGPool(TamagoCCPool pool) {
		super();
		tables = new Hashtable<String,GTamagoEntity>();
		this.pool = pool;
	}
	
	public GTamagoEntity getGTamagoEntity(String name,GModule module) throws TamagoCCException {
		return getGTamagoEntity(name,module.getFullModule());
	}
	
	public GTamagoEntity getGTamagoEntity(String name,String namepath) throws TamagoCCException {
		String key = namepath+"#"+name;
		if(tables.containsKey(key))
			return (GTamagoEntity)tables.get(key);
		
		TTamagoEntity entity = pool.getTreeAbstractSyntax(name,namepath);
		return getGTamagoEntity(entity);		
	}

	public GTamagoEntity getGTamagoEntity(TTamagoEntity entity) throws TamagoCCException {
		String key = entity.getName()+"#"+entity.getModule();
		if(tables.containsKey(key))
			return (GTamagoEntity)tables.get(key);
		TamagoCCConverter converter = new TamagoCCGenericConverter(entity);
		return converter.getGTamagoEntity();
	}
	
	public void registerGTamagoEntity(GTamagoEntity entity) throws TamagoCCException {
		String name = entity.getName();
		String fullname = entity.getModule().getFullModule();		
		String key1 = fullname+"#"+name;
		
		//if(tables.containsKey(key1))
		//	throw new TamagoCCException("TamagoCCGPool<register> : Duplicate container with the key : "+key1);
		tables.put(key1,entity);
	}
	
	public void remove(GTamagoEntity entity) throws TamagoCCException {
		remove(entity.getName(),entity.getModule().getFullModule());
	}
	
	public void remove(String name, String module) {
		String key1 = module+"#"+name;
		if(tables.containsKey(key1))
			tables.remove(key1);
	}
}
