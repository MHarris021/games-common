package com.darcstarsolutions.games.common.beans;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonSetter;

import java.math.BigInteger;

/**
 * Created by MHarris021 on 12/17/13.
 */
public class Player extends GameObjectWithRules<Player> implements
        RuleContainer<Player> {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    @JsonProperty
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

    @JsonSetter
    public void setScore(BigInteger score) {
        this.score = score;
    }

    public void setScore(long score) {
        setScore(BigInteger.valueOf(score));
    }

    @Override
    public String toString() {
        return "Player{" + "id=" + getId() + ", name='" + getName() + "', description='"
                + getDescription() + "', score=" + score + ", rules="
                + getRules() + '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Player) || (!super.equals(o))) {
            return false;
        }
        return score.equals(((Player) o).getScore());

    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + score.hashCode();
        return result;
    }
}
