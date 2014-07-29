package com.darcstarsolutions.games.common.beans.rules;

import com.darcstarsolutions.games.common.beans.Game;
import com.darcstarsolutions.games.common.beans.Player;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.hamcrest.core.IsNot.not;
import static org.junit.Assert.assertThat;

public class ConstrainedRuleTest {
    private ConstrainedRule<Game, GameRule> constrainedRuleAlwaysPasses;
    private ConstrainedRule<Game, GameRule> constrainedRuleAlwaysFails;
    private Game gameStub;

    @SuppressWarnings("serial")
    @Before
    public void setUp() throws Exception {
        constrainedRuleAlwaysPasses = new ConstrainedRule<Game, GameRule>() {

            @Override
            protected boolean meetsConstraints() {

                return true;
            }

            @Override
            protected void applyConstrainedRule() {
                getGameObject().setName("test");
            }
        };
        constrainedRuleAlwaysFails = new ConstrainedRule<Game, GameRule>() {

            @Override
            protected boolean meetsConstraints() {
                return false;
            }

            @Override
            protected void applyConstrainedRule() {
                getGameObject().setName("test");

            }
        };
        gameStub = new Game() {

            @Override
            protected List<Player> determineWinningPlayers() {
                return null;
            }
        };
    }

    @After
    public void tearDown() throws Exception {
        constrainedRuleAlwaysPasses = null;
        constrainedRuleAlwaysFails = null;
    }

    @Test
    public void testApplyRuleAlwaysPasses() {
        assertThat(gameStub.getName(), is(equalTo("")));
        constrainedRuleAlwaysPasses.apply(gameStub);
        assertThat(gameStub.getName(), is(equalTo("test")));
    }

    @Test
    public void testApplyRuleAlwaysFails() {
        assertThat(gameStub.getName(), is(equalTo("")));
        constrainedRuleAlwaysFails.apply(gameStub);
        assertThat(gameStub.getName(), is(not(equalTo("test"))));
    }

}
