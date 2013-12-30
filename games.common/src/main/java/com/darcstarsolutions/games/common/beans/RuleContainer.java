package com.darcstarsolutions.games.common.beans;

import java.util.List;

public interface RuleContainer<Type extends GameObject> {

	public abstract List<Rule<Type>> getRules();

	public abstract void setRules(List<Rule<Type>> rules);

	public abstract <T extends Rule<Type>> T getRule(
			int location);

	public abstract <T extends Rule<Type>> T setRule(
			int location, T rule);

	public abstract <T extends Rule<Type>> T addRule(T rule);

	public abstract <T extends Rule<Type>> T removeRule(
			int location);

	public abstract <T extends Rule<Type>> boolean removeRule(
			T rule);

	public abstract <T extends Rule<Type>> void applyRule(
			int location);

	public abstract <T extends Rule<Type>> void applyRule(T rule);

	public abstract <T extends Rule<Type>> T addAndApplyRule(
			T rule);

	public abstract void applyAllRules();

}