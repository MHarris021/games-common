package com.darcstarsolutions.games.common.beans;

import com.fasterxml.jackson.annotation.JsonGetter;

import java.util.List;

public class GameObjectWithRules<Type extends GameObject> extends GameObject
        implements RuleContainer<Type> {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    private RuleContainer<Type> ruleContainer;

    protected GameObjectWithRules() {
        this("", "");
    }

    @SuppressWarnings("unchecked")
    protected GameObjectWithRules(String name, String description) {
        super(name, description);
        setRuleContainer(new StandardRuleContainer<Type>((Type) this));
    }

    protected GameObjectWithRules(String name, String description, RuleContainer<Type> ruleContainer) {
        super(name, description);
        setRuleContainer(ruleContainer);
    }

    public RuleContainer<Type> getRuleContainer() {
        return ruleContainer;
    }

    public void setRuleContainer(RuleContainer<Type> ruleContainer) {
        this.ruleContainer = ruleContainer;
    }

    @Override
    @JsonGetter
    public List<Rule<Type>> getRules() {
        return ruleContainer.getRules();
    }

    @Override
    public void setRules(List<Rule<Type>> rules) {
        ruleContainer.setRules(rules);

    }

    @Override
    public <T extends Rule<Type>> T getRule(int location) {
        return ruleContainer.getRule(location);
    }

    @Override
    public <T extends Rule<Type>> T setRule(int location, T rule) {
        return ruleContainer.setRule(location, rule);
    }

    @Override
    public <T extends Rule<Type>> T addRule(T rule) {
        return ruleContainer.addRule(rule);
    }

    @Override
    public <T extends Rule<Type>> T removeRule(int location) {
        return ruleContainer.removeRule(location);
    }

    @Override
    public <T extends Rule<Type>> boolean removeRule(T rule) {
        return ruleContainer.removeRule(rule);
    }

    @Override
    public <T extends Rule<Type>> void applyRule(int location) {
        ruleContainer.applyRule(location);
    }

    @Override
    public <T extends Rule<Type>> void applyRule(T rule) {
        ruleContainer.applyRule(rule);
    }

    @Override
    public <T extends Rule<Type>> T addAndApplyRule(T rule) {
        return ruleContainer.addAndApplyRule(rule);
    }

    @Override
    public void applyAllRules() {
        ruleContainer.applyAllRules();
    }

}