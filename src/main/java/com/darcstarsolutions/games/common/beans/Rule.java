package com.darcstarsolutions.games.common.beans;

/**
 * Created by tetn on 12/16/13.
 */
public abstract class Rule<Type extends GameObject> extends GameObject {
    /**
     *
     */
    private static final long serialVersionUID = 1L;

    private Type gameObject;

    protected Rule() {
        super();
    }

    protected Rule(String name, String description) {
        super(name, description);
    }

    public Type getGameObject() {
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
        return "Rule{" + "name='" + getName() + "', description='"
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
