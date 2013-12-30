package com.darcstarsolutions.games.common.beans;

import java.math.BigInteger;

/**
 * Created by tetn on 12/17/13.
 */
public class Player extends GameObjectWithRules<Player> implements
		RuleContainer<Player> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private BigInteger score = BigInteger.ZERO;

	public Player() {
		super();
	}

	public Player(String name, String description) {
		super(name, description);
	}

	public Player(String name, String description,
			RuleContainer<Player> ruleContainer, BigInteger score) {
		super(name, description);
		this.ruleContainer = ruleContainer;
		this.score = score;
	}

	public Player(RuleContainer<Player> ruleContainer, BigInteger score) {
		super();
		this.ruleContainer = ruleContainer;
		this.score = score;
	}

	public BigInteger getScore() {
		return score;
	}

	public void setScore(BigInteger score) {
		this.score = score;
	}

	@Override
	public String toString() {
		return "Player{" + "name=" + getName() + "score=" + score
				+ ", playerRules=" + getRules() + '}';
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (!(o instanceof Player))
			return false;
		if (!super.equals(o))
			return false;

		Player player = (Player) o;

		if (!getRules().equals(player.getRules()))
			return false;
		if (!score.equals(player.score))
			return false;

		return true;
	}

	@Override
	public int hashCode() {
		int result = super.hashCode();
		result = 31 * result + score.hashCode();
		result = 31 * result + getRules().hashCode();
		return result;
	}
}
