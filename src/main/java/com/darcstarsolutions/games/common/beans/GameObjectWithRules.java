package com.darcstarsolutions.games.common.beans;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.List;

public class GameObjectWithRules<GameObjectType extends GameObject> extends GameObject
        implements RuleContainer<GameObjectType> {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    @JsonIgnore
    private RuleContainer<GameObjectType> ruleContainer;

    protected GameObjectWithRules() {
        this("", "");
    }

    @SuppressWarnings("unchecked")
    protected GameObjectWithRules(String name, String description) {
        super(name, description);
        setRuleContainer(new StandardRuleContainer<GameObjectType>((GameObjectType) this));
    }

    protected GameObjectWithRules(String name, String description, RuleContainer<GameObjectType> ruleContainer) {
        super(name, description);
        setRuleContainer(ruleContainer);
    }

    public RuleContainer<GameObjectType> getRuleContainer() {
        return ruleContainer;
    }

    public void setRuleContainer(RuleContainer<GameObjectType> ruleContainer) {
        this.ruleContainer = ruleContainer;
    }

    @Override
    @JsonGetter
    public List<Rule<GameObjectType>> getRules() {
        return ruleContainer.getRules();
    }

    @Override
    public void setRules(List<Rule<GameObjectType>> rules) {
        ruleContainer.setRules(rules);

    }

    @Override
    public <T extends Rule<GameObjectType>> T getRule(int location) {
        return ruleContainer.getRule(location);
    }

    @Override
    public <T extends Rule<GameObjectType>> T setRule(int location, T rule) {
        return ruleContainer.setRule(location, rule);
    }

    @Override
    public <T extends Rule<GameObjectType>> T addRule(T rule) {
        return ruleContainer.addRule(rule);
    }

    @Override
    public <T extends Rule<GameObjectType>> T removeRule(int location) {
        return ruleContainer.removeRule(location);
    }

    @Override
    public <T extends Rule<GameObjectType>> boolean removeRule(T rule) {
        return ruleContainer.removeRule(rule);
    }

    @Override
    public <T extends Rule<GameObjectType>> void applyRule(int location) {
        ruleContainer.applyRule(location);
    }

    @Override
    public <T extends Rule<GameObjectType>> void applyRule(T rule) {
        ruleContainer.applyRule(rule);
    }

    @Override
    public <T extends Rule<GameObjectType>> T addAndApplyRule(T rule) {
        return ruleContainer.addAndApplyRule(rule);
    }

    @Override
    public void applyAllRules() {
        ruleContainer.applyAllRules();
    }

    @Override
    public String toString() {
        return "GameObjectWithRules{" + "id=" + getId() + ", name='" + getName() + '\''
                + ", description='" + getDescription() + '\'' +
                ", rules=" + getRules() + '}';
    }
}