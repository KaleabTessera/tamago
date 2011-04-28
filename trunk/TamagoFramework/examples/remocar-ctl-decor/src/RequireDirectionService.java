/*******************************************
 * Copyright (C) 2005, Frederic Peschanski *
 *         >> DO NOT DISTRIBUTE <<         *
 *******************************************/

import tamago.*;

public interface RequireDirectionService extends RequireService {
    void bind(DirectionService direction) throws ServiceBindException;
}

