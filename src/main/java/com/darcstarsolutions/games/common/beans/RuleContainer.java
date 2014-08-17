package com.darcstarsolutions.games.common.beans;

import java.util.List;

public interface RuleContainer<GameObjectType extends GameObject> {

    public abstract List<Rule<GameObjectType>> getRules();

    public abstract void setRules(List<Rule<GameObjectType>> rules);

    public abstract <T extends Rule<GameObjectType>> T getRule(
            int location);

    public abstract <T extends Rule<GameObjectType>> T setRule(
            int location, T rule);

    public abstract <T extends Rule<GameObjectType>> T addRule(T rule);

    public abstract <T extends Rule<GameObjectType>> T removeRule(
            int location);

    public abstract <T extends Rule<GameObjectType>> boolean removeRule(
            T rule);

    public abstract <T extends Rule<GameObjectType>> void applyRule(
            int location);

    public abstract <T extends Rule<GameObjectType>> void applyRule(T rule);

    public abstract <T extends Rule<GameObjectType>> T addAndApplyRule(
            T rule);

    public abstract void applyAllRules();

}