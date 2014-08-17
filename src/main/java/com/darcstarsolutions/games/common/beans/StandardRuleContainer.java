package com.darcstarsolutions.games.common.beans;

import java.util.ArrayList;
import java.util.List;

public class StandardRuleContainer<GameObjectType extends GameObject> extends GameObject
        implements RuleContainer<GameObjectType> {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    private GameObjectType gameObject;

    private List<Rule<GameObjectType>> rules = new ArrayList<Rule<GameObjectType>>();

    public StandardRuleContainer(GameObjectType gameObject) {
        this.gameObject = gameObject;
    }

    public GameObjectType getGameObject() {
        return gameObject;
    }

    public void setGameObject(GameObjectType gameObject) {
        this.gameObject = gameObject;
    }

    @Override
    public void applyAllRules() {
        for (Rule<GameObjectType> rule : getRules()) {
            rule.apply(getGameObject());
        }

    }

    @Override
    public List<Rule<GameObjectType>> getRules() {
        return this.rules;
    }

    @Override
    public void setRules(List<Rule<GameObjectType>> rules) {
        this.rules = rules;
    }

    @SuppressWarnings("unchecked")
    @Override
    public <T extends Rule<GameObjectType>> T getRule(int location) {
        return (T) rules.get(location);
    }

    @SuppressWarnings("unchecked")
    @Override
    public <T extends Rule<GameObjectType>> T setRule(int location, T rule) {
        return (T) rules.set(location, rule);
    }

    @Override
    public <T extends Rule<GameObjectType>> T addRule(T rule) {
        boolean result = rules.add(rule);
        if (result) {
            return rule;
        } else {
            return null;
        }
    }

    @SuppressWarnings("unchecked")
    @Override
    public <T extends Rule<GameObjectType>> T removeRule(int location) {
        return (T) rules.remove(location);
    }

    @Override
    public <T extends Rule<GameObjectType>> boolean removeRule(T rule) {
        return rules.remove(rule);
    }

    @SuppressWarnings("unchecked")
    @Override
    public <T extends Rule<GameObjectType>> void applyRule(int location) {
        T rule = (T) rules.get(location);
        rule.apply(getGameObject());
    }

    @Override
    public <T extends Rule<GameObjectType>> void applyRule(T rule) {
        rule.apply(getGameObject());
    }

    @Override
    public <T extends Rule<GameObjectType>> T addAndApplyRule(T rule) {
        boolean result = rules.add(rule);
        if (result) {
            rule.apply(getGameObject());
            return rule;
        } else {
            return null;
        }
    }
}