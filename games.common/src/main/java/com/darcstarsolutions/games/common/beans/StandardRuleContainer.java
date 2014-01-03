package com.darcstarsolutions.games.common.beans;

import java.util.ArrayList;
import java.util.List;

public class StandardRuleContainer<Type extends GameObject> extends GameObject
		implements RuleContainer<Type> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Type gameObject;
	
	private List<Rule<Type>> rules = new ArrayList<>();

	public StandardRuleContainer(Type gameObject) {
		this.gameObject = gameObject;
	}

	public Type getGameObject() {
		return gameObject;
	}

	public void setGameObject(Type gameObject) {
		this.gameObject = gameObject;
	}

	@Override
	public void applyAllRules() {
		for(Rule<Type> rule : getRules()){
			rule.apply(getGameObject());
		}

	}

	@Override
	public List<Rule<Type>> getRules() {
		return this.rules;
	}

	@Override
	public void setRules(List<Rule<Type>> rules) {
		this.rules = rules;
	}

	@SuppressWarnings("unchecked")
	@Override
	public <T extends Rule<Type>> T getRule(int location) {
		return (T) rules.get(location);
	}

	@SuppressWarnings("unchecked")
	@Override
	public <T extends Rule<Type>> T setRule(int location, T rule) {
		return (T) rules.set(location, rule);
	}

	@Override
	public <T extends Rule<Type>> T addRule(T rule) {
		boolean result = rules.add(rule);
		if (result) {
			return rule;
		} else {
			return null;
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public <T extends Rule<Type>> T removeRule(int location) {
		return (T) rules.remove(location);
	}

	@Override
	public <T extends Rule<Type>> boolean removeRule(T rule) {
		return rules.remove(rule);
	}

	@SuppressWarnings("unchecked")
	@Override
	public <T extends Rule<Type>> void applyRule(int location) {
		T rule = (T) rules.get(location);
		rule.apply(getGameObject());
	}

	@Override
	public <T extends Rule<Type>> void applyRule(T rule) {
		rule.apply(getGameObject());
	}

	@Override
	public <T extends Rule<Type>> T addAndApplyRule(T rule) {
		boolean result = rules.add(rule);
		if (result) {
			rule.apply(getGameObject());
			return rule;
		} else {
			return null;
		}
	}
}