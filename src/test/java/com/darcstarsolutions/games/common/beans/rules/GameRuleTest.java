package com.darcstarsolutions.games.common.beans.rules;

import com.darcstarsolutions.games.common.beans.Game;
import com.darcstarsolutions.games.common.beans.Player;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;

public class GameRuleTest {
    private GameRule gameRule;

    @Before
    public void setUp() throws Exception {
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
        gameRule = null;
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

}
