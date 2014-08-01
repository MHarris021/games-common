package com.darcstarsolutions.games.common.beans;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by tetn on 12/23/13.
 */
@JsonTypeInfo(use = JsonTypeInfo.Id.CLASS, include = JsonTypeInfo.As.PROPERTY, property = "class")
public abstract class Game extends GameObjectWithRules<Game> implements
        RuleContainer<Game> {
    /**
     *
     */
    private static final long serialVersionUID = 1L;

    @JsonProperty
    private List<Player> players;

    @JsonProperty
    private List<Player> winningPlayers;

    public Game() {
        this("", "");
    }

    public Game(String name, String description) {
        this(name, description, new ArrayList<Player>());
    }


    public Game(String name, String description, List<Player> players) {
        super(name, description);
        this.setPlayers(players);
    }

    public List<Player> getPlayers() {
        return players;
    }

    public void setPlayers(List<Player> players) {
        this.players = players;
    }

    public List<Player> getWinningPlayers() {
        setWinningPlayers(determineWinningPlayers());
        return winningPlayers;
    }

    protected void setWinningPlayers(List<Player> winningPlayers) {
        this.winningPlayers = winningPlayers;
    }

    @SuppressWarnings("unchecked")
    public <T extends Player> T getPlayer(int location) {
        return (T) getPlayers().get(location);
    }

    public <T extends Player> void setPlayer(int location, T player) {
        List<Player> playerList = getPlayers();
        if (location < playerList.size()) {
            playerList.set(location, player);
        } else {
            throw new IndexOutOfBoundsException("Location: " + location);
        }
    }

    public <T extends Player> boolean addPlayer(T player) {
        List<Player> playerList = getPlayers();
        boolean result = playerList.add(player);
        return result;
    }

    @SuppressWarnings("unchecked")
    public <T extends Player> T removePlayer(int location) {
        List<Player> playerList = getPlayers();
        if (location < playerList.size()) {
            return (T) playerList.remove(location);
        } else {
            throw new IndexOutOfBoundsException("Location: " + location);
        }
    }

    public <T extends Player> boolean removePlayer(T player) {
        List<Player> playerList = getPlayers();
        return playerList.remove(player);
    }

    public <T extends Player> boolean containsPlayer(T player) {
        List<Player> playerList = getPlayers();
        return playerList.contains(player);
    }

    protected abstract List<Player> determineWinningPlayers();

}
