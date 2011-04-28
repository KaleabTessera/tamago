
import tamago.*;

public interface RequireEngineService extends RequireService {
    void bind(EngineService engine) throws ServiceBindException;
}
