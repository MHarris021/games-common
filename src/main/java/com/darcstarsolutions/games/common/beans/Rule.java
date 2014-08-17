package com.darcstarsolutions.games.common.beans;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

/**
 * Created by tetn on 12/16/13.
 */
@JsonTypeInfo(use = JsonTypeInfo.Id.CLASS, include = JsonTypeInfo.As.PROPERTY, property = "class")
public abstract class Rule<GameObjectType extends GameObject> extends GameObject {
    /**
     *
     */
    private static final long serialVersionUID = 1L;

    @JsonIgnore
    private GameObjectType gameObject;

    protected Rule() {
        super();
    }

    protected Rule(String name, String description) {
        super(name, description);
    }

    protected GameObjectType getGameObject() {
        return gameObject;
    }

    protected void setGameObject(GameObjectType gameObject) {
        this.gameObject = gameObject;
    }

    protected abstract void applyRule();

    @SuppressWarnings("unchecked")
    public <T extends GameObjectType> T apply(T gameObject) {
        setGameObject(gameObject);
        applyRule();
        T modifiedObject = (T) getGameObject();
        setGameObject(null);
        return modifiedObject;
    }

    @Override
    public String toString() {
        return "Rule{" + "id=" + getId() + ", name='" + getName() + "', description='"
                + getDescription() + "'}";
    }

    @Override
    public int compareTo(GameObject another) {
        if (another instanceof Rule<?>) {
            return super.compareTo(another);
        } else {
            throw new IllegalArgumentException();
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Rule)) {
            return false;
        }
        if (!super.equals(o)) {
            return false;
        }

        return true;
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }
}
