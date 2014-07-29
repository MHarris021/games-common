package com.darcstarsolutions.games.common.beans;

import org.easymock.EasyMock;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.hamcrest.core.IsNot.not;
import static org.hamcrest.core.IsSame.sameInstance;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;

public class GameTest {
	private Game game;
	private Player mockPlayer;
	private List<Player> mockPlayers;

	@SuppressWarnings("serial")
	@Before
	public void setUp() throws Exception {
		game = new Game() {

			@Override
			protected List<Player> determineWinningPlayers() {
				return getPlayers().subList(0, 1);
			}
		};
		mockPlayer = EasyMock.createMock(Player.class);
        mockPlayers = new ArrayList<Player>();
        mockPlayers.add(mockPlayer);
	}

	@After
	public void tearDown() throws Exception {
		game = null;
		mockPlayer = null;
	}

	@Test
	public void testGame() {
		assertNotNull(game);
		assertThat(game.getName(), is(equalTo("")));
		assertThat(game.getDescription(), is(equalTo("")));
	}

	@SuppressWarnings("serial")
	@Test
	public void testGameStringString() {
		Game game2 = new Game("test", "test description") {

			@Override
			protected List<Player> determineWinningPlayers() {
				return null;
			}
		};
		assertNotNull(game2);
		assertThat(game2.getName(), is(equalTo("test")));
		assertThat(game2.getDescription(), is(equalTo("test description")));
	}

	@SuppressWarnings("serial")
	@Test
	public void testGameStringStringListOfPlayer() {
		List<Player> players2 = new ArrayList<Player>();
		Game game2 = new Game("test", "test description", players2) {

			@Override
			protected List<Player> determineWinningPlayers() {
				return null;
			}
		};
		assertNotNull(game2);
		assertThat(game2.getName(), is(equalTo("test")));
		assertThat(game2.getDescription(), is(equalTo("test description")));
		assertThat(game2.getPlayers(), is(sameInstance(players2)));
	}

	@Test
	public void testGetPlayers() {
		assertNotNull(game);
		List<Player> players = game.getPlayers();
		assertNotNull(players);
		assertThat(players.size(), is(equalTo(0)));
	}

	@Test
	public void testSetPlayers() {
		List<Player> players = new ArrayList<Player>();
		assertThat(game.getPlayers(), is(not(sameInstance(players))));
		game.setPlayers(players);
		assertThat(game.getPlayers(), is(sameInstance(players)));
	}

	@Test
	public void testGetWinningPlayers() {
		game.addPlayer(mockPlayer);
		assertThat(game.getPlayer(0), is(sameInstance(mockPlayer)));
		List<Player> players = game.getWinningPlayers();
		assertThat(players.contains(mockPlayer), is(true));
		assertThat(players.get(0), is(sameInstance(mockPlayer)));
	}

	@Test
	public void testSetWinningPlayers() {
		List<Player> players = new ArrayList<Player>();
		players.add(mockPlayer);
		game.setPlayers(players);
		assertThat(game.getWinningPlayers(), is(equalTo(players)));
	}

	@Test
	public void testGetPlayer() {
		game.setPlayers(mockPlayers);
		assertThat(game.getPlayer(0), is(sameInstance(mockPlayer)));
	}

	@Test
	public void testSetPlayer() {
		Player player = new Player();
		game.setPlayers(mockPlayers);
		game.setPlayer(0, player);
		assertThat(game.getPlayer(0), is(sameInstance(player)));
	}

	@Test
	public void testAddPlayer() {
		assertNotNull(mockPlayer);
		game.addPlayer(mockPlayer);
		assertThat(game.getPlayer(0), is(sameInstance(mockPlayer)));
	}

	@Test
	public void testRemovePlayerInt() {
		game.addPlayer(mockPlayer);
		assertThat(game.getPlayer(0), is(sameInstance(mockPlayer)));
		Player player = game.removePlayer(0);
		assertThat(player, is(sameInstance(mockPlayer)));
		assertThat(game.getPlayers().isEmpty(), is(true));
	}

	@Test
	public void testRemovePlayerT() {
		game.addPlayer(mockPlayer);
		assertThat(game.getPlayer(0), is(sameInstance(mockPlayer)));
		boolean result = game.removePlayer(mockPlayer);
		assertThat(result, is(true));
		assertThat(game.getPlayers().isEmpty(), is(true));
	}

	@Test
	public void testContainsPlayer() {
		game.addPlayer(mockPlayer);
		assertThat(game.containsPlayer(mockPlayer), is(true));
	}

}
