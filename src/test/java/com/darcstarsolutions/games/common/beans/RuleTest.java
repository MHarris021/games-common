package com.darcstarsolutions.games.common.beans;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.easymock.EasyMock;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.math.BigInteger;

import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.hamcrest.core.IsNot.not;
import static org.hamcrest.core.IsSame.sameInstance;
import static org.junit.Assert.*;

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

    @Test
    public void testRuleJSONSerialization() throws Exception {
        assertTrue(objectMapper.canSerialize(DefaultGameObjectRule.class));
        assertTrue(objectMapper.canSerialize(DefaultPlayerRule.class));
        assertNotNull(defaultGameObjectRule);
        assertNotNull(defaultPlayerRule);
        String jsonValue = objectMapper.writeValueAsString(defaultGameObjectRule);
        assertNotNull(jsonValue);
        LOGGER.info("DefaultGameObjectRule JSON Encoding is: {}", jsonValue);
        jsonValue = objectMapper.writeValueAsString(defaultPlayerRule);
        assertNotNull(jsonValue);
        LOGGER.info("DefaultPlayerRule JSON Encoding is: {}", jsonValue);
    }

    @Test
    public void testRuleJSONDeserialization() throws Exception {
        String jsonValue = "";
        jsonValue = "{\"class\":\"com.darcstarsolutions.games.common.beans.RuleTest$DefaultGameObjectRule\",\"id\":0,\"name\":\"\",\"description\":\"\"}";
        DefaultGameObjectRule defaultGameObjectRuleTest = objectMapper.readValue(jsonValue, DefaultGameObjectRule.class);
        assertNotNull(defaultGameObjectRuleTest);
        assertRule(defaultGameObjectRuleTest);
        LOGGER.info("DefaultGameObjectRule Test is: {}", defaultGameObjectRuleTest);

        jsonValue = "{\"class\":\"com.darcstarsolutions.games.common.beans.RuleTest$DefaultPlayerRule\",\"id\":0,\"name\":\"\",\"description\":\"\"}";
        DefaultPlayerRule defaultPlayerRuleTest = objectMapper.readValue(jsonValue, DefaultPlayerRule.class);
        assertRule(defaultPlayerRuleTest);
        LOGGER.info("DefaultPlayerRule Test is: {}", defaultPlayerRuleTest);
    }

    @Test
    public void testRuleFileJSONDeserialization() throws Exception {
        File file = new File("src/test/resources/defaultGameObjectRule.json");
        assertNotNull(file);

        DefaultGameObjectRule defaultGameObjectRuleTest = objectMapper.readValue(file, DefaultGameObjectRule.class);
        assertNotNull(defaultGameObjectRuleTest);
        assertRule(defaultGameObjectRuleTest);
        LOGGER.info("DefaultGameObjectRule Test is: {}", defaultGameObjectRuleTest);

        file = new File("src/test/resources/defaultPlayerRule.json");
        assertNotNull(file);
        DefaultPlayerRule defaultPlayerRuleTest = objectMapper.readValue(file, DefaultPlayerRule.class);
        assertRule(defaultPlayerRuleTest);
        LOGGER.info("DefaultPlayerRule Test is: {}", defaultPlayerRuleTest);

    }

    private void assertRule(Rule<?> testRule) {
        assertNotNull(testRule);
        assertThat(testRule.getId(), is(equalTo(BigInteger.ZERO)));
        assertThat(testRule.getName(), is(equalTo("")));
        assertThat(testRule.getDescription(), is(equalTo("")));
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
