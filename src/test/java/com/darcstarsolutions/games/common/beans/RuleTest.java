package com.darcstarsolutions.games.common.beans;

import org.easymock.EasyMock;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.hamcrest.core.IsNot.not;
import static org.hamcrest.core.IsSame.sameInstance;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;

public class RuleTest {

    private Rule<GameObject> defaultRule;
    private Rule<Player> defaultPlayerRule;
    private GameObject mockGameObject;

    @SuppressWarnings("serial")
    @Before
    public void setUp() throws Exception {
        defaultRule = new Rule<GameObject>() {

            @Override
            protected void applyRule() {

            }
        };
        defaultPlayerRule = new Rule<Player>() {

            @Override
            protected void applyRule() {
                Player player = getGameObject();
                player.setName("Mock");
            }
        };
        mockGameObject = EasyMock.createMock(GameObject.class);
        defaultRule.setGameObject(mockGameObject);
    }

    @After
    public void tearDown() throws Exception {
        defaultRule = null;
        defaultPlayerRule = null;
    }

    @Test
    public void testToString() {
        assertNotNull(defaultRule);
        assertThat(defaultRule.toString(), is(equalTo("Rule{" + "name='', "
                + "description=''" + "}")));
        assertNotNull(defaultPlayerRule);
        assertThat(defaultPlayerRule.toString(), is(equalTo("Rule{"
                + "name='', " + "description=''" + "}")));
    }

    @SuppressWarnings("serial")
    @Test
    public void testCompareTo() {
        Rule<GameObject> another = new Rule<GameObject>("test",
                "test description") {

            @Override
            protected void applyRule() {

            }
        };
        assertThat(defaultRule.compareTo(another), is(not(0)));
    }

    @Test
    public void testRule() {
        assertNotNull(defaultRule);
        assertNotNull(defaultPlayerRule);

    }

    @SuppressWarnings("serial")
    @Test
    public void testRuleStringString() {
        Rule<GameObject> rule = new Rule<GameObject>("test", "test description") {

            @Override
            protected void applyRule() {

            }
        };
        assertNotNull(rule);
        assertThat(rule.getName(), is(equalTo("test")));
        assertThat(rule.getDescription(), is(equalTo("test description")));
    }

    @Test
    public void testGetGameObject() {
        assertNotNull(defaultRule.getGameObject());
        assertThat(defaultRule.getGameObject(),
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

}
