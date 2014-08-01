package com.darcstarsolutions.games.common.beans.rules;

import com.darcstarsolutions.games.common.beans.Game;
import com.darcstarsolutions.games.common.beans.Player;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.math.BigInteger;
import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.*;

public class GameRuleTest {

    private static final Logger LOGGER = LoggerFactory.getLogger(GameRuleTest.class);

    private ObjectMapper objectMapper;

    private GameRule gameRule;

    private GameRule defaultGameRule;

    @Before
    public void setUp() throws Exception {
        objectMapper = new ObjectMapper();
        defaultGameRule = new DefaultGameRule();
        new Game() {

            /**
             *
             */
            private static final long serialVersionUID = 1L;

            @Override
            protected List<Player> determineWinningPlayers() {
                return null;
            }
        };
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
        gameRule = new GameRule() {

            @Override
            protected void applyRule() {

            }
        };
        assertNotNull(gameRule);
        assertThat(gameRule.getName(), is(equalTo("")));
        assertThat(gameRule.getDescription(), is(equalTo("")));
    }

    @SuppressWarnings("serial")
    @Test
    public void testGameRuleStringString() {
        gameRule = new GameRule("test", "test description") {

            @Override
            protected void applyRule() {

            }
        };
        assertNotNull(gameRule);
        assertThat(gameRule.getName(), is(equalTo("test")));
        assertThat(gameRule.getDescription(), is(equalTo("test description")));
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

    private static class DefaultGameRule extends GameRule {
        @Override
        protected void applyRule() {

        }
    }

}
