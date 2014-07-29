package com.darcstarsolutions.games.common.beans.rules;

import com.darcstarsolutions.games.common.beans.Player;

import java.math.BigInteger;

/**
 * Created by tetn on 12/17/13.
 */
public class ChangePlayerScoreRule extends PlayerScoreRule {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    public ChangePlayerScoreRule() {
        super();
    }

    public ChangePlayerScoreRule(BigInteger score) {
        super(score);
    }

    public ChangePlayerScoreRule(String name, String description,
                                 BigInteger score) {
        super(name, description, score);
    }

    public ChangePlayerScoreRule(String name, String description) {
        super(name, description);
    }

    @Override
    protected void applyRule() {
        Player player = getGameObject();
        BigInteger score = player.getScore();
        BigInteger change = getScore();
        score = score.add(change);
        player.setScore(score);
    }
}
