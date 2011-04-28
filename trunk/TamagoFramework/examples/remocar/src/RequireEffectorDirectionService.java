
import tamago.*;

public interface RequireEffectorDirectionService extends RequireService {
    void bind(EffectorDirectionService direction) throws ServiceBindException;
}
