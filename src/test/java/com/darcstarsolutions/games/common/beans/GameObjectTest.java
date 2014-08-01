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
import static org.hamcrest.core.IsNot.not;
import static org.hamcrest.core.IsSame.sameInstance;
import static org.junit.Assert.*;

public class GameObjectTest {
    private static final Logger LOGGER = LoggerFactory.getLogger(GameObjectTest.class);
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
    public void testHashCode() {
        assertNotNull(gameObject);
        assertEquals(0, gameObject.hashCode());
    }

    @Test
    public void testGameObject() {
        assertNotNull(gameObject);
        assertThat(gameObject.getId(), is(equalTo(BigInteger.ZERO)));
        assertThat(gameObject.getName(), is(equalTo("")));
        assertThat(gameObject.getDescription(), is(equalTo("")));
    }

    @Test
    public void testGameObjectStringString() {
        GameObject gameObject2 = new GameObject("test", "test description");
        assertNotNull(gameObject2);
        assertThat(gameObject2.getId(), is(equalTo(BigInteger.ZERO)));
        assertThat(gameObject2.getName(), is(equalTo("test")));
        assertThat(gameObject2.getDescription(),
                is(equalTo("test description")));
    }

    @Test
    public void testGetId() {
        assertNotNull(gameObject);
        assertThat(gameObject.getId(), is(equalTo(BigInteger.ZERO)));
    }

    @Test
    public void testSetId() {
        assertNotNull(gameObject);
        assertThat(gameObject.getId(), is(equalTo(BigInteger.ZERO)));
        gameObject.setId(new BigInteger("100"));
        assertThat(gameObject.getId(), is(equalTo(BigInteger.valueOf(100))));
    }

    @Test
    public void testGetName() {
        assertNotNull(gameObject);
        assertThat(gameObject.getName(), is(equalTo("")));
    }

    @Test
    public void testSetName() {
        assertNotNull(gameObject);
        assertThat(gameObject.getName(), is(equalTo("")));
        gameObject.setName("test");
        assertThat(gameObject.getName(), is(equalTo("test")));
    }

    @Test
    public void testGetDescription() {
        assertNotNull(gameObject);
        assertThat(gameObject.getDescription(), is(equalTo("")));
    }

    @Test
    public void testSetDescription() {
        assertNotNull(gameObject);
        assertThat(gameObject.getDescription(), is(equalTo("")));
        gameObject.setDescription("test description");
        assertThat(gameObject.getDescription(), is(equalTo("test description")));

    }

    @Test
    public void testToString() {
        assertNotNull(gameObject);
        assertThat(gameObject.toString(),
                is(equalTo("GameObject{id=0, name='', description=''}")));
    }

    @Test
    public void testCompareTo() {
        GameObject gameObject2 = new GameObject("test", "test description");
        gameObject2.setId(BigInteger.ONE);
        assertThat(gameObject.compareTo(gameObject2), is(not(0)));
    }

    @Test
    public void testEqualsObject() {
        GameObject gameObject2 = new GameObject("", "");
        gameObject2.setId(BigInteger.ZERO);
        assertThat(gameObject.equals(gameObject2), is(true));
        assertThat(gameObject, is(not(sameInstance(gameObject2))));
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
        GameObject gameObjectTest = objectMapper.readValue(new File("src/test/resources/defaultGameObject.json"), GameObject.class);
        assertNotNull(gameObjectTest);
        assertThat(gameObjectTest.getId(), is(equalTo(BigInteger.ZERO)));
        assertThat(gameObjectTest.getName(), is(equalTo("")));
        assertThat(gameObjectTest.getDescription(), is(equalTo("")));
        LOGGER.info("GameObjectTest is: {}", gameObjectTest);
    }
}
