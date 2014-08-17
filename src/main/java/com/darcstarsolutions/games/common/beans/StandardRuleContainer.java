package com.darcstarsolutions.games.common.beans;

import java.util.ArrayList;
import java.util.List;

public class StandardRuleContainer<G extends GameObject> extends GameObject
        implements RuleContainer<G> {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    private G gameObject;

    private List<Rule<G>> rules = new ArrayList<Rule<G>>();

    public StandardRuleContainer(G gameObject) {
        this.gameObject = gameObject;
    }

    public G getGameObject() {
        return gameObject;
    }

    public void setGameObject(G gameObject) {
        this.gameObject = gameObject;
    }

    @Override
    public void applyAllRules() {
        for (Rule<G> rule : getRules()) {
            rule.apply(getGameObject());
        }

    }

    @Override
    public List<Rule<G>> getRules() {
        return this.rules;
    }

    @Override
    public void setRules(List<Rule<G>> rules) {
        this.rules = rules;
    }

    @SuppressWarnings("unchecked")
    @Override
    public <T extends Rule<G>> T getRule(int location) {
        return (T) rules.get(location);
    }

    @SuppressWarnings("unchecked")
    @Override
    public <T extends Rule<G>> T setRule(int location, T rule) {
        return (T) rules.set(location, rule);
    }

    @Override
    public <T extends Rule<G>> T addRule(T rule) {
        boolean result = rules.add(rule);
        if (result) {
            return rule;
        } else {
            return null;
        }
    }

    @SuppressWarnings("unchecked")
    @Override
    public <T extends Rule<G>> T removeRule(int location) {
        return (T) rules.remove(location);
    }

    @Override
    public <T extends Rule<G>> boolean removeRule(T rule) {
        return rules.remove(rule);
    }

    @SuppressWarnings("unchecked")
    @Override
    public <T extends Rule<G>> void applyRule(int location) {
        T rule = (T) rules.get(location);
        rule.apply(getGameObject());
    }

    @Override
    public <T extends Rule<G>> void applyRule(T rule) {
        rule.apply(getGameObject());
    }

    @Override
    public <T extends Rule<G>> T addAndApplyRule(T rule) {
        boolean result = rules.add(rule);
        if (result) {
            rule.apply(getGameObject());
            return rule;
        } else {
            return null;
        }
    }
}