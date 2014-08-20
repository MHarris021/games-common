package com.darcstarsolutions.games.common.beans;

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

/**
 * Created by mharris021 on 8/19/14.
 */
public class RuleSerializationTest {
    private static final Logger LOGGER = LoggerFactory.getLogger(RuleSerializationTest.class);
    private ObjectMapper objectMapper;

    private Rule<GameObject> defaultGameObjectRule;
    private Rule<Player> defaultPlayerRule;

    @SuppressWarnings("serial")
    @Before
    public void setUp() throws Exception {
        objectMapper = new ObjectMapper();
        defaultGameObjectRule = new RuleTest.DefaultGameObjectRule();
        defaultPlayerRule = new RuleTest.DefaultPlayerRule();
    }

    @After
    public void tearDown() throws Exception {
        objectMapper = null;
        defaultGameObjectRule = null;
        defaultPlayerRule = null;
    }

    @Test
    public void testRuleJSONSerialization() throws Exception {
        assertTrue(objectMapper.canSerialize(RuleTest.DefaultGameObjectRule.class));
        assertTrue(objectMapper.canSerialize(RuleTest.DefaultPlayerRule.class));
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
        String jsonValue = "{\"class\":\"com.darcstarsolutions.games.common.beans.RuleTest$DefaultGameObjectRule\",\"id\":0,\"name\":\"\",\"description\":\"\"}";
        RuleTest.DefaultGameObjectRule defaultGameObjectRuleTest = objectMapper.readValue(jsonValue, RuleTest.DefaultGameObjectRule.class);
        assertNotNull(defaultGameObjectRuleTest);
        assertRule(defaultGameObjectRuleTest);
        LOGGER.info("DefaultGameObjectRule Test is: {}", defaultGameObjectRuleTest);

        jsonValue = "{\"class\":\"com.darcstarsolutions.games.common.beans.RuleTest$DefaultPlayerRule\",\"id\":0,\"name\":\"\",\"description\":\"\"}";
        RuleTest.DefaultPlayerRule defaultPlayerRuleTest = objectMapper.readValue(jsonValue, RuleTest.DefaultPlayerRule.class);
        assertRule(defaultPlayerRuleTest);
        LOGGER.info("DefaultPlayerRule Test is: {}", defaultPlayerRuleTest);
    }

    @Test
    public void testRuleFileJSONDeserialization() throws Exception {
        File file = new File("src/integrationTest/resources/defaultGameObjectRule.json");
        assertNotNull(file);

        RuleTest.DefaultGameObjectRule defaultGameObjectRuleTest = objectMapper.readValue(file, RuleTest.DefaultGameObjectRule.class);
        assertNotNull(defaultGameObjectRuleTest);
        assertRule(defaultGameObjectRuleTest);
        LOGGER.info("DefaultGameObjectRule Test is: {}", defaultGameObjectRuleTest);

        file = new File("src/integrationTest/resources/defaultPlayerRule.json");
        assertNotNull(file);
        RuleTest.DefaultPlayerRule defaultPlayerRuleTest = objectMapper.readValue(file, RuleTest.DefaultPlayerRule.class);
        assertRule(defaultPlayerRuleTest);
        LOGGER.info("DefaultPlayerRule Test is: {}", defaultPlayerRuleTest);

    }

    private void assertRule(Rule<?> testRule) {
        assertNotNull(testRule);
        assertThat(testRule.getId(), is(equalTo(BigInteger.ZERO)));
        assertThat(testRule.getName(), is(equalTo("")));
        assertThat(testRule.getDescription(), is(equalTo("")));
    }
}
