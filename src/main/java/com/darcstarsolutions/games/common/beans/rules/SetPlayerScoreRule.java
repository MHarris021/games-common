package com.darcstarsolutions.games.common.beans.rules;

import com.darcstarsolutions.games.common.beans.Player;

import java.math.BigInteger;

public class SetPlayerScoreRule extends PlayerScoreRule {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    public SetPlayerScoreRule() {
        super();
    }

    public SetPlayerScoreRule(BigInteger score) {
        super(score);
    }

    public SetPlayerScoreRule(String name, String description, BigInteger score) {
        super(name, description, score);
    }

    public SetPlayerScoreRule(String name, String description) {
        super(name, description);
    }

    @Override
    protected void applyRule() {
        Player player = getGameObject();
        BigInteger score = getScore();
        player.setScore(score);
    }

}
