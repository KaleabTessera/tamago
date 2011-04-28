package tamago.ext.tamagocc;

import java.lang.reflect.Constructor;

import tamago.ServiceBindException;
import tamago.Tamago;
import tamago.TamagoException;
/**
 * @author Hakim Belhaouari
 */
public class TamagoCC extends Tamago {

	/**
	 * 
	 */
	public TamagoCC() {
		super();

	}

	public static TamagoCCComponent LookUp(String c) throws TamagoException {
		return LookUp(c, "plugin",CreateBusinessComponent(c));
	}
	
	public static TamagoCCComponent LookUp(String c,TamagoCCComponent business) throws TamagoException {
		return LookUp(c,"plugin",business);
	}

	@SuppressWarnings("unchecked")
	private static TamagoCCComponent CreateBusinessComponent(String c) throws TamagoException {
		try {
			String container = c+"Stub";
			Class compclass = ClassLoader.getSystemClassLoader().loadClass(container);

			TamagoCCComponent r = (TamagoCCComponent)compclass.newInstance();
			return r;
		}
		catch(Exception e) {
			try {
				String container = c+"Business";
				Class compclass = ClassLoader.getSystemClassLoader().loadClass(container);

				TamagoCCComponent r = (TamagoCCComponent)compclass.newInstance();
				return r;
			}
			catch(Exception ex) {
				throw new TamagoException(e.getClass().getName()+":"+e.getMessage());
			}
		}
	}

	public static TamagoCCComponent LookUp(String c,String percolatorname) throws TamagoException {
		return LookUp(c,percolatorname,CreateBusinessComponent(c));
	}
	
	@SuppressWarnings("unchecked")
	public static TamagoCCComponent LookUp(String c,String percolatorname,TamagoCCComponent business) throws TamagoException {
		try {
			String container = c+"ContainerImpl_"+percolatorname;
			Class<?> compclass =  ClassLoader.getSystemClassLoader().loadClass(container);
			// On suppose qu'il existe un constructeur par defaut.

			Class subcompclass =  ClassLoader.getSystemClassLoader().loadClass(c);
			Constructor constructor = compclass.getConstructor(new Class[] { subcompclass });
			TamagoCCComponent r = (TamagoCCComponent) constructor.newInstance(new Object[] { business });
			return r;
		}
		catch(Exception e) {
			try {
				String container = c+"ContainerSkel_"+percolatorname;
				Class<?> compclass =  ClassLoader.getSystemClassLoader().loadClass(container);
				// On suppose qu'il existe un constructeur par defaut.

				Class subcompclass =  ClassLoader.getSystemClassLoader().loadClass(c);
				Constructor constructor = compclass.getConstructor(new Class[] { subcompclass });
				TamagoCCComponent r = (TamagoCCComponent) constructor.newInstance(new Object[] { business });
				return r;
			}
			catch(Exception ex) {

				throw new TamagoException(e.getClass().getName()+":"+e.getMessage());
			}
			
		}
	}


	@SuppressWarnings("unchecked")
	public static TamagoCCAssembly LookUpAssembly(String c) throws TamagoException {
		try {
			Class compclass =  ClassLoader.getSystemClassLoader().loadClass(c);
			// On suppose qu'il existe un constructeur par defaut.
			TamagoCCAssembly r = (TamagoCCAssembly) compclass.newInstance();
			return r;
		}
		catch(Exception e) {			
			throw new TamagoException(e.getClass().getName()+" : "+e.getMessage());
		}
	}
	
	public static TamagoCCComponent HotSwapping(TamagoCCComponent _old,TamagoCCComponent _new) throws TamagoException,TamagoCCHotSwappingException {
		((TamagoCCContainer)_old).tamago_hotswapping(_new);
		return _old;
	}



	/**
	 * Cette methode permet de faire le binding nomm&eacute;e entre un composant et un service requis 
	 * 
	 */
	public static void Bind(RequireServiceNaming requirer,String label,TamagoCCService service) throws ServiceBindException {
		requirer.bind(label,service);
	}

	/**
	 * Cette version correspond a sa jummelle mais correspond a simplification des lignes de programmation.
	 */
	public static void Bind(Object requirer,String label,Object service) throws ServiceBindException {
		((RequireServiceNaming)requirer).bind(label,(TamagoCCService)service);
	}

	@SuppressWarnings("unchecked")
	public static TamagoCCType getTamagoCCType(String name,String module)
	throws TamagoException,ClassNotFoundException
	{
		try {
			Class type = Class.forName(module+"."+name+"TamagoCCType");
			return (TamagoCCType)type.newInstance();
		}
		catch(Exception exception) {
			throw new TamagoException(exception);
		}
	}

	@SuppressWarnings("unchecked")
	public static TamagoCCType getTamagoCCType(String fullnameclass) throws TamagoException {
		try {
			String c = fullnameclass+"TamagoCCType";
			Class<TamagoCCType> compclass =  (Class<TamagoCCType>)ClassLoader.getSystemClassLoader().loadClass(c);
			TamagoCCType r = (TamagoCCType) compclass.newInstance();
			return r;
		}
		catch(Exception e) {	
			throw new TamagoException(e.getClass().getName()+":"+e.getMessage());
		}
	}

	public static TamagoCCPercolator[] getAvailablePercolator(String fullnamecomponent) 
	throws TamagoException,ClassNotFoundException
	{
		return getTamagoCCType(fullnamecomponent).getAvailablePercolator();
	}    
}
