
import tamago.*;

public interface RequireConstantService extends RequireLabelledService {
    void bind(ConstantService constant, String label) throws ServiceBindException;
}
