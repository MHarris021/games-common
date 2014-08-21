package com.darcstarsolutions.games.common.beans;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

/**
 * Created by mharris021 on 8/20/14.
 */
public class GameObjectWithRulesSerializationTest {

    private static final Logger LOGGER = LoggerFactory.getLogger(GameObjectWithRulesSerializationTest.class);

    private ObjectMapper objectMapper;

    private GameObjectWithRules<GameObject> gameObjectWithRules;
    private RuleContainer<GameObject> mockRuleContainer;

    @Before
    public void setUp() throws Exception {
        objectMapper = new ObjectMapper();

        gameObjectWithRules = new GameObjectWithRules<GameObject>("", "");
    }

    @Test
    public void testJSONSerializer() throws Exception {
        assertNotNull(objectMapper);
        assertTrue(objectMapper.canSerialize(GameObjectWithRules.class));
        String jsonValue = objectMapper.writeValueAsString(gameObjectWithRules);
        assertNotNull(jsonValue);
        LOGGER.info("GameObjectWithRules JSON Encoding: {}", jsonValue);
    }

    @Test
    public void testJSONDeserializer() throws Exception {
        String jsonValue = "{\"id\":0,\"name\":\"\",\"description\":\"\",\"rules\":[]}";
        GameObjectWithRules<GameObject> gameObjectWithRulesTest = objectMapper.readValue(jsonValue, GameObjectWithRules.class);
        assertNotNull(gameObjectWithRulesTest);
        LOGGER.info("GameObjectWithRules JSON Decoding: {}", gameObjectWithRulesTest);
    }

    @Test
    public void testFileJSONDeserializer() throws Exception {
        GameObjectWithRules<GameObject> gameObjectWithRulesTest = objectMapper.readValue(new File("src/integrationTest/resources/emptyGameObjectWithRules.json"), GameObjectWithRules.class);
        assertNotNull(gameObjectWithRulesTest);
        LOGGER.info("GameObjectWithRules JSON Decoding: {}", gameObjectWithRulesTest);
    }

}
