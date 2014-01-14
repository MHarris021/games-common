package com.darcstarsolutions.games.common.beans.rules;

import static org.hamcrest.core.Is.*;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.*;

import java.math.BigInteger;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.darcstarsolutions.games.common.beans.Player;

public class SetPlayerScoreRuleTest {
	private Player player;
	private SetPlayerScoreRule defaultSetPlayerScoreRule;
	private SetPlayerScoreRule setPlayerScoreRule;

	@Before
	public void setUp() throws Exception {
		player = new Player();
		player.setScore(10);
		defaultSetPlayerScoreRule = new SetPlayerScoreRule();
	}

	@After
	public void tearDown() throws Exception {
		player = null;
		defaultSetPlayerScoreRule = null;
		setPlayerScoreRule = null;
	}

	@Test
	public void testApplyRule() {
		assertNotNull(defaultSetPlayerScoreRule);
		assertNotNull(player);
		assertThat(player.getScore(), is(equalTo(BigInteger.valueOf(10))));
		assertThat(defaultSetPlayerScoreRule.getScore(),
				is(equalTo(BigInteger.ZERO)));
		defaultSetPlayerScoreRule.apply(player);
		assertThat(player.getScore(), is(equalTo(BigInteger.ZERO)));

	}

	@Test
	public void testSetPlayerScoreRule() {
		assertNotNull(defaultSetPlayerScoreRule);
		assertThat(defaultSetPlayerScoreRule.getName(), is(equalTo("")));
		assertThat(defaultSetPlayerScoreRule.getDescription(), is(equalTo("")));
		assertThat(defaultSetPlayerScoreRule.getScore(),
				is(equalTo(BigInteger.ZERO)));
	}

	@Test
	public void testSetPlayerScoreRuleBigInteger() {
		setPlayerScoreRule = new SetPlayerScoreRule(BigInteger.ONE);
		assertNotNull(setPlayerScoreRule);
		assertThat(setPlayerScoreRule.getScore(), is(equalTo(BigInteger.ONE)));
		assertThat(player.getScore(), is(equalTo(BigInteger.valueOf(10))));
		setPlayerScoreRule.apply(player);
		assertThat(player.getScore(), is(equalTo(BigInteger.ONE)));
	}

	@Test
	public void testSetPlayerScoreRuleStringStringBigInteger() {
		setPlayerScoreRule = new SetPlayerScoreRule("test", "test description",
				BigInteger.ONE);
		assertNotNull(setPlayerScoreRule);
		assertNotSame(defaultSetPlayerScoreRule, setPlayerScoreRule);
		assertThat(setPlayerScoreRule.getName(), is(equalTo("test")));
		assertThat(setPlayerScoreRule.getDescription(),
				is(equalTo("test description")));
		assertThat(setPlayerScoreRule.getScore(), is(equalTo(BigInteger.ONE)));

	}

	@Test
	public void testSetPlayerScoreRuleStringString() {
		setPlayerScoreRule = new SetPlayerScoreRule("test", "test description");
		assertNotNull(setPlayerScoreRule);
		assertNotSame(defaultSetPlayerScoreRule, setPlayerScoreRule);
		assertThat(setPlayerScoreRule.getName(), is(equalTo("test")));
		assertThat(setPlayerScoreRule.getDescription(),
				is(equalTo("test description")));
		assertThat(setPlayerScoreRule.getScore(), is(equalTo(BigInteger.ZERO)));

	}

}
