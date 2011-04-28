package echo;

import tamago.*;

public interface RequireConsoleService extends RequireService {
    public void bind(ConsoleService console) throws ServiceBindException;
}

