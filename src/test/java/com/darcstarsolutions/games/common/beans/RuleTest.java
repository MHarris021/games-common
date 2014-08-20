package com.darcstarsolutions.games.common.beans;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.easymock.EasyMock;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.hamcrest.core.IsNot.not;
import static org.hamcrest.core.IsSame.sameInstance;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;

public class RuleTest {

    public static final String TEST = "test";
    public static final String TEST_DESCRIPTION = "test description";
    private static final Logger LOGGER = LoggerFactory.getLogger(RuleTest.class);
    private ObjectMapper objectMapper;

    private Rule<GameObject> defaultGameObjectRule;
    private Rule<Player> defaultPlayerRule;
    private GameObject mockGameObject;

    @SuppressWarnings("serial")
    @Before
    public void setUp() throws Exception {
        objectMapper = new ObjectMapper();
        defaultGameObjectRule = new DefaultGameObjectRule();
        defaultPlayerRule = new DefaultPlayerRule();
        mockGameObject = EasyMock.createMock(GameObject.class);
        defaultGameObjectRule.setGameObject(mockGameObject);
    }

    @After
    public void tearDown() throws Exception {
        objectMapper = null;
        defaultGameObjectRule = null;
        defaultPlayerRule = null;
    }

    @Test
    public void testToString() {
        assertNotNull(defaultGameObjectRule);
        assertThat(defaultGameObjectRule.toString(), is(equalTo("Rule{id=0, " + "name='', "
                + "description=''" + "}")));
        assertNotNull(defaultPlayerRule);
        assertThat(defaultPlayerRule.toString(), is(equalTo("Rule{id=0, "
                + "name='', " + "description=''" + "}")));
    }

    @SuppressWarnings("serial")
    @Test
    public void testCompareTo() {
        Rule<GameObject> another = new DefaultGameObjectRule(TEST, TEST_DESCRIPTION);
        assertThat(defaultGameObjectRule.compareTo(another), is(not(0)));
    }

    @Test
    public void testRule() {
        assertNotNull(defaultGameObjectRule);
        assertNotNull(defaultPlayerRule);

    }

    @SuppressWarnings("serial")
    @Test
    public void testRuleStringString() {
        Rule<GameObject> rule = new DefaultGameObjectRule(TEST, TEST_DESCRIPTION);
        assertNotNull(rule);
        assertThat(rule.getName(), is(equalTo(TEST)));
        assertThat(rule.getDescription(), is(equalTo(TEST_DESCRIPTION)));
    }

    @Test
    public void testGetGameObject() {
        assertNotNull(defaultGameObjectRule.getGameObject());
        assertThat(defaultGameObjectRule.getGameObject(),
                is(sameInstance(mockGameObject)));
    }

    @Test
    public void testSetGameObject() {
        Player mockPlayer = EasyMock.createMock(Player.class);
        defaultPlayerRule.setGameObject(mockPlayer);
        assertThat(defaultPlayerRule.getGameObject(),
                is(sameInstance(mockPlayer)));
    }

    @Test
    public void testApply() {
        Player mockPlayer = new Player();
        defaultPlayerRule.apply(mockPlayer);
        assertThat(mockPlayer.getName(), is(equalTo("Mock")));
    }


    public static class DefaultGameObjectRule extends Rule<GameObject> {

        public DefaultGameObjectRule() {
            super();
        }

        public DefaultGameObjectRule(String name, String description) {
            super(name, description);
        }

        @Override
        protected void applyRule() {

        }
    }

    public static class DefaultPlayerRule extends Rule<Player> {

        @Override
        protected void applyRule() {
            Player player = getGameObject();
            player.setName("Mock");
        }
    }
}
