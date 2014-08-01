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
import static org.junit.Assert.*;

public class PlayerTest {

    private static final Logger LOGGER = LoggerFactory.getLogger(PlayerTest.class);

    private ObjectMapper objectMapper;

    private Player player;
    private Player defaultPlayer;
    private Rule<Player> stubRule;

    @SuppressWarnings("serial")
    @Before
    public void setUp() throws Exception {
        objectMapper = new ObjectMapper();
        player = new Player();
        stubRule = new StubPlayerRule();
        defaultPlayer = new Player();
        defaultPlayer.addRule(stubRule);
    }

    @After
    public void tearDown() throws Exception {
        objectMapper = null;
        player = null;
        defaultPlayer = null;
        stubRule = null;
    }

    @Test
    public void testHashCode() {
        Player player2 = new Player();
        assertThat(player.hashCode(), is(equalTo(player2.hashCode())));
    }

    @Test
    public void testToString() {
        assertThat(
                player.toString(),
                is(equalTo("Player{id=0, name='', description='', score=0, rules=[]}")));
        Player player3 = new Player("test", "test description", BigInteger.ONE);
        assertThat(player3.toString(), is(equalTo("Player{id=0, name='test',"
                + " description='test description', score=1, rules=[]}")));

    }

    @Test
    public void testEqualsObject() {
        Player player2 = new Player();
        assertThat(player, is(equalTo(player2)));
        player2.setName("test");
        player2.setDescription("test description");
        player2.setScore(BigInteger.ONE);
        assertThat(player, is(not(equalTo(player2))));
        Player player3 = new Player("test", "test description", BigInteger.ONE);
        assertThat(player2, is(equalTo(player3)));
    }

    @Test
    public void testPlayer() {
        assertNotNull(player);
        assertThat(player.getName(), is(equalTo("")));
        assertThat(player.getDescription(), is(equalTo("")));
        assertThat(player.getScore(), is(equalTo(BigInteger.ZERO)));
    }

    @Test
    public void testPlayerStringString() {
        Player player2 = new Player("test", "test description");
        assertNotNull(player2);
        assertThat(player2.getName(), is(equalTo("test")));
        assertThat(player2.getDescription(), is(equalTo("test description")));
        assertThat(player2.getScore(), is(equalTo(BigInteger.ZERO)));
    }

    @Test
    public void testPlayerStringStringBigInteger() {
        Player player2 = new Player("test", "test description", BigInteger.ONE);
        assertNotNull(player2);
        assertThat(player2.getName(), is(equalTo("test")));
        assertThat(player2.getDescription(), is(equalTo("test description")));
        assertThat(player2.getScore(), is(equalTo(BigInteger.ONE)));
    }

    @Test
    public void testGetScore() {
        assertThat(player.getScore(), is(equalTo(BigInteger.ZERO)));
    }

    @Test
    public void testSetScore() {
        player.setScore(BigInteger.valueOf(100));
        assertThat(player.getScore(), is(equalTo(BigInteger.valueOf(100))));
    }

    @Test
    public void testSetScoreLong() {
        player.setScore(100);
        assertThat(player.getScore(), is(equalTo(BigInteger.valueOf(100))));
    }

    @Test
    public void testApplyRule() {
        player.applyRule(stubRule);
        assertThat(player.getScore(), is(equalTo(BigInteger.TEN)));
    }

    @Test
    public void testJSONSerializer() throws Exception {
        assertNotNull(objectMapper);
        assertTrue(objectMapper.canSerialize(Player.class));
        String jsonValue = objectMapper.writeValueAsString(defaultPlayer);
        assertNotNull(jsonValue);
        LOGGER.info("Player JSON Encoding: {}", jsonValue);
    }

    @Test
    public void testJSONDeserializer() throws Exception {
        assertNotNull(objectMapper);
        String jsonValue = "{\"id\":0,\"name\":\"\",\"description\":\"\",\"score\":0,\"rules\":[{\"class\":\"com.darcstarsolutions.games.common.beans.PlayerTest$StubPlayerRule\",\"id\":0,\"name\":\"\",\"description\":\"\"}]}";
        Player playerTest = objectMapper.readValue(jsonValue, Player.class);
        assertNotNull(playerTest);
        Rule<Player> rule = playerTest.getRule(0);
        assertNotNull(rule);
        assertEquals(StubPlayerRule.class, rule.getClass());
        LOGGER.info("Player JSON Decoding: {}", playerTest);
    }

    @Test
    public void testFileJSONDeserializer() throws Exception {
        assertNotNull(objectMapper);
        Player playerTest = objectMapper.readValue(new File("src/test/resources/player.json"), Player.class);
        assertNotNull(playerTest);
        Rule<Player> rule = playerTest.getRule(0);
        assertNotNull(rule);
        assertEquals(StubPlayerRule.class, rule.getClass());
        LOGGER.info("Player JSON Decoding: {}", playerTest);
    }

    public static class StubPlayerRule extends Rule<Player> {

        @Override
        protected void applyRule() {
            getGameObject().setScore(BigInteger.TEN);
        }
    }
}
