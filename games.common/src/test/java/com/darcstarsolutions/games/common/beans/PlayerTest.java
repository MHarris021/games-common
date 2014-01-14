package com.darcstarsolutions.games.common.beans;

import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsNot.*;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.*;

import java.math.BigInteger;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class PlayerTest {
	private Player player;
	private Rule<Player> stubRule;

	@SuppressWarnings("serial")
	@Before
	public void setUp() throws Exception {
		player = new Player();
		stubRule = new Rule<Player>() {

			@Override
			protected void applyRule() {
				getGameObject().setScore(BigInteger.TEN);

			}
		};
	}

	@After
	public void tearDown() throws Exception {
		player = null;
	}

	@Test
	public void testHashCode() {
		Player player2 = new Player();
		assertThat(player.hashCode(), is(equalTo(player2.hashCode())));
	}

	@Test
	public void testToString() {
		assertThat(
				player.toString(),
				is(equalTo("Player{name='', description='', score=0, playerRules=[]}")));
		Player player3 = new Player("test", "test description", BigInteger.ONE);
		assertThat(player3.toString(), is(equalTo("Player{name='test',"
				+ " description='test description', score=1, playerRules=[]}")));

	}

	@Test
	public void testEqualsObject() {
		Player player2 = new Player();
		assertThat(player, is(equalTo(player2)));
		player2.setName("test");
		player2.setDescription("test description");
		player2.setScore(BigInteger.ONE);
		assertThat(player, is(not(equalTo(player2))));
		Player player3 = new Player("test", "test description", BigInteger.ONE);
		assertThat(player2, is(equalTo(player3)));
	}

	@Test
	public void testPlayer() {
		assertNotNull(player);
		assertThat(player.getName(), is(equalTo("")));
		assertThat(player.getDescription(), is(equalTo("")));
		assertThat(player.getScore(), is(equalTo(BigInteger.ZERO)));
	}

	@Test
	public void testPlayerStringString() {
		Player player2 = new Player("test", "test description");
		assertNotNull(player2);
		assertThat(player2.getName(), is(equalTo("test")));
		assertThat(player2.getDescription(), is(equalTo("test description")));
		assertThat(player2.getScore(), is(equalTo(BigInteger.ZERO)));
	}

	@Test
	public void testPlayerStringStringBigInteger() {
		Player player2 = new Player("test", "test description", BigInteger.ONE);
		assertNotNull(player2);
		assertThat(player2.getName(), is(equalTo("test")));
		assertThat(player2.getDescription(), is(equalTo("test description")));
		assertThat(player2.getScore(), is(equalTo(BigInteger.ONE)));
	}

	@Test
	public void testGetScore() {
		assertThat(player.getScore(), is(equalTo(BigInteger.ZERO)));
	}

	@Test
	public void testSetScore() {
		player.setScore(BigInteger.valueOf(100));
		assertThat(player.getScore(), is(equalTo(BigInteger.valueOf(100))));
	}
	
	@Test
	public void testSetScoreLong() {
		player.setScore(100);
		assertThat(player.getScore(), is(equalTo(BigInteger.valueOf(100))));
	}

	@Test
	public void testApplyRule() {
		player.applyRule(stubRule);
		assertThat(player.getScore(), is(equalTo(BigInteger.TEN)));
	}

}
