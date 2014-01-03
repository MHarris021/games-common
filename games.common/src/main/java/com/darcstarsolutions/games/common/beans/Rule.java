package com.darcstarsolutions.games.common.beans;

import java.lang.reflect.ParameterizedType;

/**
 * Created by tetn on 12/16/13.
 */
public abstract class Rule<Type extends GameObject> extends GameObject {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Type gameObject;
	private Class<Type> gameObjectClass;

	@SuppressWarnings("unchecked")
	protected Rule() {
		super();
		Class<?> aClass = ((Object) this).getClass();
		Class<Type> aClass1 = (Class<Type>) ((ParameterizedType) aClass.getGenericSuperclass())
				.getActualTypeArguments()[0];
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

	public Class<Type> getGameObjectClass() {
		return gameObjectClass;
	}

	protected void setGameObjectClass(Class<Type> gameObjectClass) {
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
		return "Rule{" + "name='" + getName() + "', description='"
				+ getDescription() + "', gameObjectClass='"
				+ gameObjectClass.getSimpleName() + "'}";
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
