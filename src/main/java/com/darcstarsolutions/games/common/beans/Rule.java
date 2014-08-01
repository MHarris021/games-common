package com.darcstarsolutions.games.common.beans;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

/**
 * Created by tetn on 12/16/13.
 */
@JsonTypeInfo(use = JsonTypeInfo.Id.CLASS, include = JsonTypeInfo.As.PROPERTY, property = "class")
public abstract class Rule<Type extends GameObject> extends GameObject {
    /**
     *
     */
    private static final long serialVersionUID = 1L;

    @JsonIgnore
    private Type gameObject;

    protected Rule() {
        super();
    }

    protected Rule(String name, String description) {
        super(name, description);
    }

    protected Type getGameObject() {
        return gameObject;
    }

    protected void setGameObject(Type gameObject) {
        this.gameObject = gameObject;
    }

    protected abstract void applyRule();

    @SuppressWarnings("unchecked")
    public <T extends Type> T apply(T gameObject) {
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
            int result = super.compareTo(another);
            return result;
        } else {
            throw new IllegalArgumentException();
        }
    }
}
