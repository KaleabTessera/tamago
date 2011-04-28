import tamago.*;

public interface RequireReceiverService extends RequireService {
    void bind(ReceiverService receiver) throws ServiceBindException;
}

