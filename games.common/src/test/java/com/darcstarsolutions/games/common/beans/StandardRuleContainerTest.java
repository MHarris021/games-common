package com.darcstarsolutions.games.common.beans;

import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.hamcrest.core.IsSame.sameInstance;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.easymock.EasyMock;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.darcstarsolutions.games.common.beans.Player;
import com.darcstarsolutions.games.common.beans.Rule;
import com.darcstarsolutions.games.common.beans.RuleContainer;
import com.darcstarsolutions.games.common.beans.StandardRuleContainer;
import com.darcstarsolutions.games.common.beans.rules.PlayerRule;

public class StandardRuleContainerTest {
	private Player mockPlayer;
	private RuleContainer<Player> ruleContainer;
	private PlayerRule mockPlayerRule;
	private PlayerRule unexpectedMockPlayerRule;

	@Before
	public void setUp() throws Exception {
		mockPlayer = EasyMock.createMock(Player.class);
		ruleContainer = new StandardRuleContainer<Player>(mockPlayer);
		mockPlayerRule = EasyMock.createMock(PlayerRule.class);
		EasyMock.expect(mockPlayerRule.getName()).andReturn("Mock");
		unexpectedMockPlayerRule = EasyMock.createMock(PlayerRule.class);
		EasyMock.expect(unexpectedMockPlayerRule.getName()).andReturn(
				"Unexpected");

	}

	@After
	public void tearDown() throws Exception {
		ruleContainer = null;
		mockPlayerRule = null;
		unexpectedMockPlayerRule = null;
	}

	@Test
	public void testStandardRuleContainer() {
		assertNotNull(ruleContainer);
	}

	@Test
	public void testGetRules() {
		assertNotNull(ruleContainer.getRules());
	}

	@Test
	public void testSetRules() {
		ruleContainer.setRules(new ArrayList<Rule<Player>>());
		assertNotNull(ruleContainer.getRules());
	}

	@Test
	public void testGetRule() {
		List<Rule<Player>> rules = new ArrayList<>();
		EasyMock.replay(mockPlayerRule);
		rules.add(mockPlayerRule);
		ruleContainer.setRules(rules);
		assertNotNull(ruleContainer.getRule(0));
		assertThat(ruleContainer.getRule(0).getName(), is(equalTo("Mock")));
	}

	@Test
	public void testSetRule() {
		List<Rule<Player>> rules = new ArrayList<>(2);
		ruleContainer.setRules(rules);
		EasyMock.replay(mockPlayerRule, unexpectedMockPlayerRule);
		ruleContainer.addRule(unexpectedMockPlayerRule);
		PlayerRule rule = ruleContainer.setRule(0, mockPlayerRule);
		assertNotNull(rule);
		assertThat(rule, is(sameInstance(unexpectedMockPlayerRule)));
		assertNotNull(ruleContainer.getRule(0));
		assertThat(ruleContainer.getRule(0).getName(), is(equalTo("Mock")));
	}

	@Test
	public void testAddRule() {
		EasyMock.replay(mockPlayerRule);
		ruleContainer.addRule(mockPlayerRule);
		assertNotNull(ruleContainer.getRule(0));
		assertThat((PlayerRule) ruleContainer.getRule(0),
				is(sameInstance(mockPlayerRule)));
	}

	@Test
	public void testRemoveRuleInt() {
		EasyMock.replay(mockPlayerRule);
		ruleContainer.addRule(mockPlayerRule);
		PlayerRule playerRule = ruleContainer.removeRule(0);
		assertNotNull(playerRule);
		assertTrue(ruleContainer.getRules().isEmpty());
	}

	@Test
	public void testRemoveRuleT() {
		EasyMock.replay(mockPlayerRule);
		ruleContainer.addRule(mockPlayerRule);
		ruleContainer.removeRule(mockPlayerRule);
		assertTrue(ruleContainer.getRules().isEmpty());
	}

}
