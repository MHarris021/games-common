package com.darcstarsolutions.games.common.beans;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.List;

public class GameObjectWithRules<G extends GameObject> extends GameObject
        implements RuleContainer<G> {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    @JsonIgnore
    private RuleContainer<G> ruleContainer;

    protected GameObjectWithRules() {
        this("", "");
    }

    @SuppressWarnings("unchecked")
    protected GameObjectWithRules(String name, String description) {
        super(name, description);
        setRuleContainer(new StandardRuleContainer<G>((G) this));
    }

    protected GameObjectWithRules(String name, String description, RuleContainer<G> ruleContainer) {
        super(name, description);
        setRuleContainer(ruleContainer);
    }

    public RuleContainer<G> getRuleContainer() {
        return ruleContainer;
    }

    public void setRuleContainer(RuleContainer<G> ruleContainer) {
        this.ruleContainer = ruleContainer;
    }

    @Override
    @JsonGetter
    public List<Rule<G>> getRules() {
        return ruleContainer.getRules();
    }

    @Override
    public void setRules(List<Rule<G>> rules) {
        ruleContainer.setRules(rules);

    }

    @Override
    public <T extends Rule<G>> T getRule(int location) {
        return ruleContainer.getRule(location);
    }

    @Override
    public <T extends Rule<G>> T setRule(int location, T rule) {
        return ruleContainer.setRule(location, rule);
    }

    @Override
    public <T extends Rule<G>> T addRule(T rule) {
        return ruleContainer.addRule(rule);
    }

    @Override
    public <T extends Rule<G>> T removeRule(int location) {
        return ruleContainer.removeRule(location);
    }

    @Override
    public <T extends Rule<G>> boolean removeRule(T rule) {
        return ruleContainer.removeRule(rule);
    }

    @Override
    public void applyRule(int location) {
        ruleContainer.applyRule(location);
    }

    @Override
    public <T extends Rule<G>> void applyRule(T rule) {
        ruleContainer.applyRule(rule);
    }

    @Override
    public <T extends Rule<G>> T addAndApplyRule(T rule) {
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