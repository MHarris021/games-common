package com.darcstarsolutions.games.common.beans.rules;

import static org.hamcrest.core.Is.*;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class PlayerRuleTest {
	private PlayerRule playerRule;

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
		playerRule = null;
	}

	@SuppressWarnings("serial")
	@Test
	public void testPlayerRule() {
		playerRule = new PlayerRule() {
			
			@Override
			protected void applyRule() {
				
			}
		};
		assertNotNull(playerRule);
		assertThat(playerRule.getName(), is(equalTo("")));
		assertThat(playerRule.getDescription(), is(equalTo("")));
	}

	@SuppressWarnings("serial")
	@Test
	public void testPlayerRuleStringString() {
		playerRule = new PlayerRule("test", "test description") {
			
			@Override
			protected void applyRule() {
				
			}
		};
		assertNotNull(playerRule);
		assertThat(playerRule.getName(), is(equalTo("test")));
		assertThat(playerRule.getDescription(), is(equalTo("test description")));
	}

}
