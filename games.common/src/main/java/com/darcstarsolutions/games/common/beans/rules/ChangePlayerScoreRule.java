package com.darcstarsolutions.games.common.beans.rules;

import java.math.BigInteger;

import com.darcstarsolutions.games.common.beans.Player;

/**
 * Created by tetn on 12/17/13.
 */
public class ChangePlayerScoreRule extends PlayerRule {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	private BigInteger change;

    public ChangePlayerScoreRule() {
        this(BigInteger.ZERO);
    }

    public ChangePlayerScoreRule(BigInteger change) {
        this.setChange(change);
    }

    public BigInteger getChange() {
        return change;
    }

    public void setChange(BigInteger change) {
        this.change = change;
    }

    public void setChange(long change) {
        this.setChange(BigInteger.valueOf(change));
    }

    public void setChange(int change) {
        this.setChange(BigInteger.valueOf(Integer.valueOf(change)));
    }

    @Override
    protected void applyRule() {
        Player player = getGameObject();
        BigInteger score = player.getScore();
        score = score.add(change);
    }
}
