package com.darcstarsolutions.games.common.beans.rules;

import com.darcstarsolutions.games.common.beans.GameObject;
import com.darcstarsolutions.games.common.beans.Rule;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by tetn on 12/17/13.
 */
public class CompositeRule<G extends GameObject, R extends Rule<G>>
        extends Rule<G> {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    private List<R> rules;

    public CompositeRule() {
        this("", "");
    }

    public CompositeRule(String name, String description) {
        this(name, description, new ArrayList<R>());
    }

    public CompositeRule(String name, String description, List<R> rules) {
        super(name, description);
        setRules(rules);
    }

    public CompositeRule(List<R> rules) {
        this("", "", rules);
    }

    public List<R> getRules() {
        return rules;
    }

    public void setRules(List<R> rules) {
        this.rules = rules;
    }

    public <T extends R> T addRule(T rule) {
        T result = null;
        if (getRules().add(rule)) {
            result = rule;
        }
        return result;
    }

    public <T extends R> boolean removeRule(T rule) {
        return getRules().remove(rule);
    }

    @SuppressWarnings("unchecked")
    public <T extends R> T removeRule(int location) {
        return (T) getRules().remove(location);
    }

    @SuppressWarnings("unchecked")
    public <T extends R> T getRule(int location) {
        return (T) getRules().get(location);
    }


    @SuppressWarnings("unchecked")
    public <T extends R> T setRule(int location, T rule) {
        return (T) getRules().set(location, rule);
    }

    @Override
    protected void applyRule() {
        G gameObject = getGameObject();
        for (R rule : getRules()) {
            rule.apply(gameObject);
        }
    }
}
