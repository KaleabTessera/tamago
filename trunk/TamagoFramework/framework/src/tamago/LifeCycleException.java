package tamago;

public class LifeCycleException extends TamagoException {
    public LifeCycleException(String message) {
        super("Life Cycle fault : " + message);
    }
}
