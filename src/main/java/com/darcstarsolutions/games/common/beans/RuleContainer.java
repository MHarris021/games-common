package com.darcstarsolutions.games.common.beans;

import java.util.List;

public interface RuleContainer<G extends GameObject> {

    public abstract List<Rule<G>> getRules();

    public abstract void setRules(List<Rule<G>> rules);

    public abstract <T extends Rule<G>> T getRule(
            int location);

    public abstract <T extends Rule<G>> T setRule(
            int location, T rule);

    public abstract <T extends Rule<G>> T addRule(T rule);

    public abstract <T extends Rule<G>> T removeRule(
            int location);

    public abstract <T extends Rule<G>> boolean removeRule(
            T rule);

    public abstract <T extends Rule<G>> void applyRule(
            int location);

    public abstract <T extends Rule<G>> void applyRule(T rule);

    public abstract <T extends Rule<G>> T addAndApplyRule(
            T rule);

    public abstract void applyAllRules();

}