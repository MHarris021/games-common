package com.darcstarsolutions.games.common.beans.rules;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.*;

public class PlayerRuleTest {
    public static final String TEST = "test";
    public static final String TEST_DESCRIPTION = "test description";
    private static final Logger LOGGER = LoggerFactory.getLogger(PlayerRuleTest.class);
    private ObjectMapper objectMapper;

    private PlayerRule playerRule;

    private DefaultPlayerRule defaultPlayerRule;

    @Before
    public void setUp() throws Exception {
        objectMapper = new ObjectMapper();
        defaultPlayerRule = new DefaultPlayerRule();
    }

    @After
    public void tearDown() throws Exception {
        objectMapper = null;
        playerRule = null;
        defaultPlayerRule = null;
    }

    @SuppressWarnings("serial")
    @Test
    public void testPlayerRule() {
        playerRule = new DefaultPlayerRule();
        assertNotNull(playerRule);
        assertThat(playerRule.getName(), is(equalTo("")));
        assertThat(playerRule.getDescription(), is(equalTo("")));
    }

    @SuppressWarnings("serial")
    @Test
    public void testPlayerRuleStringString() {
        playerRule = new DefaultPlayerRule(TEST, TEST_DESCRIPTION);
        assertNotNull(playerRule);
        assertThat(playerRule.getName(), is(equalTo(TEST)));
        assertThat(playerRule.getDescription(), is(equalTo(TEST_DESCRIPTION)));
    }

    @Test
    public void testJSONSerializer() throws Exception {
        assertNotNull(objectMapper);
        assertTrue(objectMapper.canSerialize(PlayerRule.class));
        String jsonValue = objectMapper.writeValueAsString(defaultPlayerRule);
        assertNotNull(jsonValue);
        LOGGER.info("DefaultPlayerRule JSON Encoding: {}", jsonValue);
    }

    @Test
    public void testJSONDeserializer() throws Exception {
        assertNotNull(objectMapper);
        String jsonValue = "{\"class\":\"com.darcstarsolutions.games.common.beans.rules.PlayerRuleTest$DefaultPlayerRule\",\"id\":0,\"name\":\"\",\"description\":\"\"}";
        DefaultPlayerRule defaultPlayerRuleTest = objectMapper.readValue(jsonValue, DefaultPlayerRule.class);
        assertNotNull(defaultPlayerRuleTest);
        LOGGER.info("DefaultPlayerRule JSON Decoding: {}", defaultPlayerRuleTest);
    }

    public static class DefaultPlayerRule extends PlayerRule {

        public DefaultPlayerRule() {
        }

        public DefaultPlayerRule(String name, String description) {
            super(name, description);
        }

        @Override
        protected void applyRule() {

        }
    }
}
