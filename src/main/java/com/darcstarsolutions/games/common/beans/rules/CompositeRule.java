package com.darcstarsolutions.games.common.beans.rules;

import com.darcstarsolutions.games.common.beans.GameObject;
import com.darcstarsolutions.games.common.beans.Rule;

import java.util.ArrayList;
import java.util.List;

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
        this("", "");
    }

    public CompositeRule(String name, String description) {
        this(name, description, new ArrayList<RuleType>());
    }

    public CompositeRule(String name, String description, List<RuleType> rules) {
        super(name, description);
        setRules(rules);
    }

    public CompositeRule(List<RuleType> rules) {
        this("", "", rules);
    }

    public List<RuleType> getRules() {
        return rules;
    }

    public void setRules(List<RuleType> rules) {
        this.rules = rules;
    }

    public <T extends RuleType> T addRule(T rule) {
        T result = null;
        if (getRules().add(rule)) {
            result = rule;
        }
        return result;
    }

    public <T extends RuleType> boolean removeRule(T rule) {
        return getRules().remove(rule);
    }

    @SuppressWarnings("unchecked")
    public <T extends RuleType> T removeRule(int location) {
        T rule = (T) getRules().remove(location);
        return rule;
    }

    @SuppressWarnings("unchecked")
    public <T extends RuleType> T getRule(int location) {
        return (T) getRules().get(location);
    }


    @SuppressWarnings("unchecked")
    public <T extends RuleType> T setRule(int location, T rule) {
        T result = (T) getRules().set(location, rule);
        return result;
    }

    @Override
    protected void applyRule() {
        Type gameObject = getGameObject();
        for (RuleType rule : getRules()) {
            rule.apply(gameObject);
        }
    }
}
