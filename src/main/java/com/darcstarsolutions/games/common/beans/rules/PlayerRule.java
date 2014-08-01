package com.darcstarsolutions.games.common.beans.rules;

import com.darcstarsolutions.games.common.beans.Player;
import com.darcstarsolutions.games.common.beans.Rule;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

/**
 * Created by tetn on 12/17/13.
 */
@JsonTypeInfo(use = JsonTypeInfo.Id.CLASS, include = JsonTypeInfo.As.PROPERTY, property = "class")
public abstract class PlayerRule extends Rule<Player> {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    public PlayerRule() {
        super();
    }

    public PlayerRule(String name, String description) {
        super(name, description);
    }

}
