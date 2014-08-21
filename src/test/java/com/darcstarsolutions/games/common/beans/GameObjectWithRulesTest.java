package com.darcstarsolutions.games.common.beans;

import org.easymock.EasyMock;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.hamcrest.core.IsSame.sameInstance;
import static org.junit.Assert.*;

public class GameObjectWithRulesTest {

    private static final Logger LOGGER = LoggerFactory.getLogger(GameObjectWithRulesTest.class);

    private GameObjectWithRules<GameObject> gameObjectWithRules;
    private RuleContainer<GameObject> mockRuleContainer;
    private Rule<GameObject> mockRule;
    private Rule<GameObject> mockRule2;
    private Rule<GameObject> stubRule;
    private Rule<GameObject> stubRule2;
    private List<Rule<GameObject>> mockRules;

    @SuppressWarnings({"unchecked", "serial"})
    @Before
    public void setUp() throws Exception {
        gameObjectWithRules = new GameObjectWithRules<GameObject>();
        mockRuleContainer = EasyMock.createMock(StandardRuleContainer.class);
        mockRule = EasyMock.createMock(Rule.class);
        mockRule2 = EasyMock.createMock(Rule.class);
        stubRule = new Rule<GameObject>() {

            @Override
            protected void applyRule() {
                getGameObject().setName("stub");

            }
        };
        stubRule2 = new Rule<GameObject>() {

            @Override
            protected void applyRule() {
                getGameObject().setDescription("stub description");

            }
        };
        mockRules = EasyMock.createMock(List.class);
        EasyMock.expect(mockRuleContainer.getRule(0)).andReturn(mockRule);
        EasyMock.expect(mockRuleContainer.setRule(0, mockRule2)).andReturn(
                mockRule2);
        EasyMock.expect(mockRuleContainer.getRule(0)).andReturn(mockRule2);
        EasyMock.expect(mockRuleContainer.addRule(mockRule2)).andReturn(
                mockRule2);
        EasyMock.expect(mockRuleContainer.getRule(1)).andReturn(mockRule2);
        EasyMock.expect(mockRuleContainer.removeRule(0)).andReturn(mockRule);
        EasyMock.expect(mockRuleContainer.removeRule(mockRule)).andReturn(true);
        EasyMock.expect(mockRule.getName()).andReturn("Mock");
        EasyMock.expect(mockRule2.getName()).andReturn("Mock2");
        EasyMock.expect(mockRuleContainer.getRules()).andReturn(mockRules);
        EasyMock.expect(mockRules.isEmpty()).andReturn(true);
    }

    @After
    public void tearDown() throws Exception {
        gameObjectWithRules = null;
        mockRule = null;
        mockRuleContainer = null;
    }

    @Test
    public void testGameObjectWithRules() {
        assertNotNull(gameObjectWithRules);
    }

    @Test
    public void testGameObjectWithRulesStringString() {
        GameObjectWithRules<GameObject> gameObjectWithRules2 = new GameObjectWithRules<GameObject>(
                "test", "test description");
        assertNotNull(gameObjectWithRules2);
        assertThat(gameObjectWithRules2.getName(), is(equalTo("test")));
        assertThat(gameObjectWithRules2.getDescription(),
                is(equalTo("test description")));
    }

    @Test
    public void testGetRuleContainer() {
        RuleContainer<GameObject> ruleContainer = gameObjectWithRules
                .getRuleContainer();
        assertNotNull(ruleContainer);
        assertThat(ruleContainer.getRules().isEmpty(), is(true));
    }

    @Test
    public void testSetRuleContainer() {
        gameObjectWithRules.setRuleContainer(mockRuleContainer);
        RuleContainer<GameObject> ruleContainer = gameObjectWithRules
                .getRuleContainer();
        assertNotNull(ruleContainer);

    }

    @Test
    public void testGetRules() {
        gameObjectWithRules.setRuleContainer(mockRuleContainer);
        RuleContainer<GameObject> ruleContainer = gameObjectWithRules
                .getRuleContainer();
        assertNotNull(ruleContainer);
        EasyMock.replay(mockRuleContainer);
        assertThat(gameObjectWithRules.getRules(), is(sameInstance(mockRules)));
    }

    @Test
    public void testSetRules() {
        assertNotNull(mockRules);
        gameObjectWithRules.setRules(mockRules);
        assertThat(gameObjectWithRules.getRules(), is(sameInstance(mockRules)));
    }

    @Test
    public void testGetRule() {
        gameObjectWithRules.setRuleContainer(mockRuleContainer);
        EasyMock.replay(mockRuleContainer, mockRule);
        Rule<GameObject> rule = gameObjectWithRules.getRule(0);
        assertThat(rule, is(sameInstance(mockRule)));
        assertThat(rule.getName(), is(equalTo("Mock")));
    }

    @Test
    public void testSetRule() {
        gameObjectWithRules.setRuleContainer(mockRuleContainer);
        EasyMock.replay(mockRuleContainer, mockRule, mockRule2);
        Rule<GameObject> rule = gameObjectWithRules.getRule(0);
        assertThat(rule, is(sameInstance(mockRule)));
        assertThat(rule.getName(), is(equalTo("Mock")));
        gameObjectWithRules.setRule(0, mockRule2);
        rule = gameObjectWithRules.getRule(0);
        assertThat(rule, is(sameInstance(mockRule2)));
        assertThat(rule.getName(), is(equalTo("Mock2")));

    }

    @Test
    public void testAddRule() {
        gameObjectWithRules.setRuleContainer(mockRuleContainer);
        EasyMock.replay(mockRuleContainer, mockRule, mockRule2);
        Rule<GameObject> rule = gameObjectWithRules.getRule(0);
        assertThat(rule, is(sameInstance(mockRule)));
        assertThat(rule.getName(), is(equalTo("Mock")));
        gameObjectWithRules.addRule(mockRule2);
        rule = gameObjectWithRules.getRule(1);
        assertThat(rule, is(sameInstance(mockRule2)));
        assertThat(rule.getName(), is(equalTo("Mock2")));
    }

    @Test
    public void testRemoveRuleInt() {
        gameObjectWithRules.setRuleContainer(mockRuleContainer);
        EasyMock.replay(mockRuleContainer, mockRule, mockRule2, mockRules);
        Rule<GameObject> rule = gameObjectWithRules.removeRule(0);
        assertThat(rule, is(sameInstance(mockRule)));
        assertThat(rule.getName(), is(equalTo("Mock")));
        assertThat(gameObjectWithRules.getRules().isEmpty(), is(true));
    }

    @Test
    public void testRemoveRuleT() {
        gameObjectWithRules.setRuleContainer(mockRuleContainer);
        EasyMock.replay(mockRuleContainer, mockRule, mockRule2, mockRules);
        boolean result = gameObjectWithRules.removeRule(mockRule);
        assertTrue(result);
        assertThat(gameObjectWithRules.getRules().isEmpty(), is(true));
    }

    @Test
    public void testApplyRuleInt() {
        gameObjectWithRules.addRule(stubRule);
        assertThat(gameObjectWithRules.getRule(0), is(sameInstance(stubRule)));
        gameObjectWithRules.applyRule(0);
        assertThat(gameObjectWithRules.getName(), is(equalTo("stub")));
    }

    @Test
    public void testApplyRuleT() {
        gameObjectWithRules.applyRule(stubRule);
        assertThat(gameObjectWithRules.getName(), is(equalTo("stub")));
    }

    @Test
    public void testAddAndApplyRule() {
        gameObjectWithRules.addAndApplyRule(stubRule);
        assertThat(gameObjectWithRules.getRule(0), is(sameInstance(stubRule)));
        assertThat(gameObjectWithRules.getName(), is(equalTo("stub")));
    }

    @Test
    public void testApplyAllRules() {
        gameObjectWithRules.addRule(stubRule);
        gameObjectWithRules.addRule(stubRule2);
        assertThat(gameObjectWithRules.getRule(0), is(sameInstance(stubRule)));
        assertThat(gameObjectWithRules.getRule(1), is(sameInstance(stubRule2)));
        gameObjectWithRules.applyAllRules();
        assertThat(gameObjectWithRules.getName(), is(equalTo("stub")));
        assertThat(gameObjectWithRules.getDescription(),
                is(equalTo("stub description")));
    }


}
