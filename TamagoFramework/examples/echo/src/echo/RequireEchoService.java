package echo;

import tamago.*;

public interface RequireEchoService extends RequireService {
    public void bind(EchoService echo) throws ServiceBindException;
}

