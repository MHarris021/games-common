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
    private Class<?> gameObjectClass;

    protected Rule() {
        super();
        Class<?> aClass = ((Object) this).getClass();
        Class<?> aClass1 = aClass.getSuperclass().getTypeParameters()[0].getClass();
        setGameObjectClass(aClass1);
    }

    protected Rule(String name, String description) {
        this();
        setName(name);
        setDescription(description);
    }

    public Type getGameObject() {
        return gameObject;
    }

    protected void setGameObject(Type gameObject) {
        this.gameObject = gameObject;
    }

    public Class<?> getGameObjectClass() {
        return gameObjectClass;
    }

    protected void setGameObjectClass(Class<?> gameObjectClass) {
        this.gameObjectClass = gameObjectClass;
    }

    protected abstract void applyRule();

    public <T extends Type> Type apply(Type gameObject) {
        setGameObject((Type) gameObject);
        applyRule();
        Type modifiedObject = getGameObject();
        setGameObject(null);
        return modifiedObject;
    }

    @Override
    public String toString() {
        return "Rule{" +
                "name=" + getName() +
                "description" + getDescription() +
                "gameObjectClass=" + gameObjectClass +
                '}';
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
