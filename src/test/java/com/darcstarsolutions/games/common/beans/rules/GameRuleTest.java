package com.darcstarsolutions.games.common.beans.rules;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.math.BigInteger;

import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.*;

public class GameRuleTest {

    public static final String TEST = "test";
    public static final String TEST_DESCRIPTION = "test description";
    private static final Logger LOGGER = LoggerFactory.getLogger(GameRuleTest.class);
    private ObjectMapper objectMapper;

    private GameRule gameRule;

    private GameRule defaultGameRule;

    @Before
    public void setUp() throws Exception {
        objectMapper = new ObjectMapper();
        defaultGameRule = new DefaultGameRule();
    }

    @After
    public void tearDown() throws Exception {
        objectMapper = null;
        gameRule = null;
        defaultGameRule = null;
    }

    @SuppressWarnings("serial")
    @Test
    public void testGameRule() {
        gameRule = new DefaultGameRule();
        assertNotNull(gameRule);
        assertThat(gameRule.getName(), is(equalTo("")));
        assertThat(gameRule.getDescription(), is(equalTo("")));
    }

    @SuppressWarnings("serial")
    @Test
    public void testGameRuleStringString() {
        gameRule = new DefaultGameRule(TEST, TEST_DESCRIPTION);
        assertNotNull(gameRule);
        assertThat(gameRule.getName(), is(equalTo(TEST)));
        assertThat(gameRule.getDescription(), is(equalTo(TEST_DESCRIPTION)));
    }

    @Test
    public void testGameRuleJSONSerialization() throws Exception {
        assertTrue(objectMapper.canSerialize(GameRule.class));
        assertNotNull(defaultGameRule);
        String jsonValue = objectMapper.writeValueAsString(defaultGameRule);
        assertNotNull(jsonValue);
        LOGGER.info("DefaultGameRule JSON Encoding is: {}", jsonValue);
    }

    @Test
    public void testGameRuleJSONDeserialization() throws Exception {
        String jsonValue = "{\"class\":\"com.darcstarsolutions.games.common.beans.rules.GameRuleTest$DefaultGameRule\",\"id\":0,\"name\":\"\",\"description\":\"\"}";
        DefaultGameRule defaultGameRuleTest = objectMapper.readValue(jsonValue, DefaultGameRule.class);
        assertNotNull(defaultGameRuleTest);
        assertThat(defaultGameRuleTest.getId(), is(equalTo(BigInteger.ZERO)));
        assertThat(defaultGameRuleTest.getName(), is(equalTo("")));
        assertThat(defaultGameRuleTest.getDescription(), is(equalTo("")));
        LOGGER.info("DefaultGameRule Test is: {}", defaultGameRuleTest);
    }

    @Test
    public void testGameRuleFileJSONDeserialization() throws Exception {
        DefaultGameRule defaultGameRuleTest = objectMapper.readValue(new File("src/test/resources/rules/defaultGameRule.json"), DefaultGameRule.class);
        assertNotNull(defaultGameRuleTest);
        assertThat(defaultGameRuleTest.getId(), is(equalTo(BigInteger.ZERO)));
        assertThat(defaultGameRuleTest.getName(), is(equalTo("")));
        assertThat(defaultGameRuleTest.getDescription(), is(equalTo("")));
        LOGGER.info("DefaultGameRule Test is: {}", defaultGameRuleTest);
    }

    public static class DefaultGameRule extends GameRule {

        public DefaultGameRule() {
        }

        public DefaultGameRule(String name, String description) {
            super(name, description);
        }

        @Override
        protected void applyRule() {

        }
    }

}
