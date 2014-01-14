package com.darcstarsolutions.games.common.beans.rules;

import java.math.BigInteger;

public abstract class PlayerScoreRule extends PlayerRule {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	protected BigInteger score;

	public PlayerScoreRule() {
		this(BigInteger.ZERO);
	}

	public PlayerScoreRule(BigInteger score) {
		this("", "", score);
	}

	public PlayerScoreRule(String name, String description) {
		this(name, description, BigInteger.ZERO);
	}

	public PlayerScoreRule(String name, String description, BigInteger score) {
		super(name, description);
		setScore(score);
	}

	public BigInteger getScore() {
		return score;
	}

	public void setScore(BigInteger score) {
		this.score = score;
	}

	public void setScore(long score) {
		this.setScore(BigInteger.valueOf(score));
	}

}