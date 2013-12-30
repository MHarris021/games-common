package com.darcstarsolutions.games.common.beans.rules;

import java.util.ArrayList;
import java.util.List;

import com.darcstarsolutions.games.common.beans.GameObject;
import com.darcstarsolutions.games.common.beans.Rule;

/**
 * Created by tetn on 12/17/13.
 */
public class CompositeRule<Type extends GameObject, RuleType extends Rule<Type>>
		extends Rule<Type> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private List<RuleType> rules;

	public CompositeRule() {
		List<RuleType> rules = new ArrayList<>();
		setRules(rules);
	}

	public CompositeRule(List<RuleType> rules) {
		setRules(rules);
	}

	public List<RuleType> getRules() {
		return rules;
	}

	public void setRules(List<RuleType> rules) {
		this.rules = rules;
	}

	public RuleType addRule(RuleType rule) {
		getRules().add(rule);
		return rule;
	}

	public boolean removeRule(RuleType rule) {
		return getRules().remove(rule);
	}

	public RuleType removeRule(int location) {
		RuleType rule = getRules().remove(location);
		return rule;
	}

	public RuleType getRule(int location) {
		return getRules().get(location);
	}

	@Override
	protected void applyRule() {
		Type gameObject = getGameObject();
		for (RuleType rule : getRules()) {
			rule.apply(gameObject);
		}
	}
}
