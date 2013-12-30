package com.darcstarsolutions.games.common.beans;

import java.util.ArrayList;
import java.util.List;

import com.darcstarsolutions.games.common.beans.rules.GameRule;

/**
 * Created by tetn on 12/23/13.
 */
public abstract class Game extends GameObjectWithRules<Game> implements
		RuleContainer<Game> {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private List<Player> players;
	private List<GameRule> gameRules;

	private Player winningPlayer;

	public Game() {
		this(new ArrayList<Player>(), new ArrayList<GameRule>());
	}

	public Game(List<Player> players, List<GameRule> gameRules) {
		this.setPlayers(players);
		this.setGameRules(gameRules);
	}

	public List<Player> getPlayers() {
		return players;
	}

	public void setPlayers(List<Player> players) {
		this.players = players;
	}

	public List<GameRule> getGameRules() {
		return gameRules;
	}

	public void setGameRules(List<GameRule> gameRules) {
		this.gameRules = gameRules;
	}

	public Player getWinningPlayer() {
		setWinningPlayer(determineWinningPlayer());
		return winningPlayer;
	}

	protected void setWinningPlayer(Player winningPlayer) {
		this.winningPlayer = winningPlayer;
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

	protected abstract Player determineWinningPlayer();

}
