
import tamago.*;

public interface RequireEffectorEngineService extends RequireService {
    void bind(EffectorEngineService direction) throws ServiceBindException;
}
