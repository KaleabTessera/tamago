package tamago;

public class TamagoException extends RuntimeException{

	private static final long serialVersionUID = -442490590404513446L;

	public TamagoException(String message) {
        super("[TAMAGO] "+message);
    }
    
	public TamagoException() {
		super();
	}
	
    public TamagoException(Throwable cause) {
    	super(cause);
    }
}

