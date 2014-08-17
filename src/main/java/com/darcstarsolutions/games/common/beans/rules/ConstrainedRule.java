package com.darcstarsolutions.games.common.beans.rules;

import com.darcstarsolutions.games.common.beans.GameObject;
import com.darcstarsolutions.games.common.beans.Rule;

/**
 * Created by tetn on 12/17/13.
 */
public abstract class ConstrainedRule<G extends GameObject, R extends Rule<G>>
        extends Rule<G> {

    /**
     *
     */
    private static final long serialVersionUID = 1L;


    protected abstract boolean meetsConstraints();

    protected abstract void applyConstrainedRule();

    @Override
    protected void applyRule() {
        if (meetsConstraints()) {
            applyConstrainedRule();
        }
    }
}
