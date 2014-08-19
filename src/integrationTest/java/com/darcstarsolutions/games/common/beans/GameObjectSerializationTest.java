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
 * Created by mharris021 on 8/18/14.
 */
public class GameObjectSerializationTest {

    private static final Logger LOGGER = LoggerFactory.getLogger(GameObjectSerializationTest.class);
    private GameObject gameObject;
    private ObjectMapper objectMapper;

    @Before
    public void setUp() throws Exception {
        gameObject = new GameObject();
        objectMapper = new ObjectMapper();
    }

    @After
    public void tearDown() throws Exception {
        gameObject = null;
        objectMapper = null;
    }

    @Test
    public void testGameObjectJSONSerialization() throws Exception {
        assertTrue(objectMapper.canSerialize(GameObject.class));
        String jsonValue = objectMapper.writeValueAsString(gameObject);
        assertNotNull(jsonValue);
        LOGGER.info("GameObject JSON Encoding is: {}", jsonValue);
    }

    @Test
    public void testGameObjectJSONDeserialization() throws Exception {
        String jsonValue = "{\"id\":0,\"name\":\"\",\"description\":\"\"}";
        GameObject gameObjectTest = objectMapper.readValue(jsonValue, GameObject.class);
        assertNotNull(gameObjectTest);
        assertThat(gameObjectTest.getId(), is(equalTo(BigInteger.ZERO)));
        assertThat(gameObjectTest.getName(), is(equalTo("")));
        assertThat(gameObjectTest.getDescription(), is(equalTo("")));
        LOGGER.info("GameObjectTest is: {}", gameObjectTest);
    }

    @Test
    public void testGameObjectFileJSONDeserialization() throws Exception {
        GameObject gameObjectTest = objectMapper.readValue(new File("src/integrationTest/resources/defaultGameObject.json"), GameObject.class);
        assertNotNull(gameObjectTest);
        assertThat(gameObjectTest.getId(), is(equalTo(BigInteger.ZERO)));
        assertThat(gameObjectTest.getName(), is(equalTo("")));
        assertThat(gameObjectTest.getDescription(), is(equalTo("")));
        LOGGER.info("GameObjectTest is: {}", gameObjectTest);
    }
}
