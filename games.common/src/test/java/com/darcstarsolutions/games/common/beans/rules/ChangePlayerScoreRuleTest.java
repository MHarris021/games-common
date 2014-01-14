package com.darcstarsolutions.games.common.beans.rules;

import static org.hamcrest.core.Is.*;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.*;

import java.math.BigInteger;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.darcstarsolutions.games.common.beans.Player;

public class ChangePlayerScoreRuleTest {
	private Player player;
	private ChangePlayerScoreRule defaultChangePlayerScoreRule;
	private ChangePlayerScoreRule changePlayerScoreRule;

	@Before
	public void setUp() throws Exception {
		player = new Player();
		player.setScore(10);
		defaultChangePlayerScoreRule = new ChangePlayerScoreRule();
		defaultChangePlayerScoreRule.setScore(1);
	}

	@After
	public void tearDown() throws Exception {
		player = null;
		defaultChangePlayerScoreRule = null;
	}

	@Test
	public void testApplyRule() {
		assertNotNull(player);
		assertThat(player.getScore(), is(equalTo(BigInteger.TEN)));
		assertNotNull(defaultChangePlayerScoreRule);
		assertThat(defaultChangePlayerScoreRule.getScore(),
				is(equalTo(BigInteger.ONE)));
		defaultChangePlayerScoreRule.apply(player);
		assertThat(player.getScore(), is(equalTo(BigInteger.valueOf(11))));
	}

	@Test
	public void testChangePlayerScoreRule() {
		assertNotNull(defaultChangePlayerScoreRule);
		assertThat(defaultChangePlayerScoreRule.getName(), is(equalTo("")));
		assertThat(defaultChangePlayerScoreRule.getDescription(),
				is(equalTo("")));
		assertThat(defaultChangePlayerScoreRule.getScore(),
				is(equalTo(BigInteger.ONE)));
	}

	@Test
	public void testChangePlayerScoreRuleBigInteger() {
		changePlayerScoreRule = new ChangePlayerScoreRule(BigInteger.TEN);
		assertNotNull(changePlayerScoreRule);
		assertThat(changePlayerScoreRule.getName(), is(equalTo("")));
		assertThat(changePlayerScoreRule.getDescription(), is(equalTo("")));
		assertThat(changePlayerScoreRule.getScore(),
				is(equalTo(BigInteger.TEN)));
	}

	@Test
	public void testChangePlayerScoreRuleStringStringBigInteger() {
		changePlayerScoreRule = new ChangePlayerScoreRule("test",
				"test description", BigInteger.TEN);
		assertNotNull(changePlayerScoreRule);
		assertThat(changePlayerScoreRule.getName(), is(equalTo("test")));
		assertThat(changePlayerScoreRule.getDescription(),
				is(equalTo("test description")));
		assertThat(changePlayerScoreRule.getScore(),
				is(equalTo(BigInteger.TEN)));
	}

	@Test
	public void testChangePlayerScoreRuleStringString() {
		changePlayerScoreRule = new ChangePlayerScoreRule("test",
				"test description");
		assertNotNull(changePlayerScoreRule);
		assertThat(changePlayerScoreRule.getName(), is(equalTo("test")));
		assertThat(changePlayerScoreRule.getDescription(),
				is(equalTo("test description")));
		assertThat(changePlayerScoreRule.getScore(),
				is(equalTo(BigInteger.ZERO)));
	}

}
