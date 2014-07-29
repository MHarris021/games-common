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

    public Player(String name, String description, BigInteger score) {
        super(name, description);
        this.setScore(score);
    }

    public BigInteger getScore() {
        return score;
    }

    public void setScore(long score) {
        setScore(BigInteger.valueOf(score));
    }

    public void setScore(BigInteger score) {
        this.score = score;
    }

    @Override
    public String toString() {
        return "Player{" + "name='" + getName() + "', description='"
                + getDescription() + "', score=" + score + ", playerRules="
                + getRules() + '}';
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
        if (!score.equals(player.score))
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + score.hashCode();
        return result;
    }
}
