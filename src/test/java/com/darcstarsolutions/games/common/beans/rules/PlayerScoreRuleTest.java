package com.darcstarsolutions.games.common.beans.rules;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.math.BigInteger;

import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;

public class PlayerScoreRuleTest {
    private PlayerScoreRule defaultPlayerScoreRule;
    private PlayerScoreRule playerScoreRule;

    @SuppressWarnings("serial")
    @Before
    public void setUp() throws Exception {
        defaultPlayerScoreRule = new PlayerScoreRule() {

            @Override
            protected void applyRule() {

            }
        };
    }

    @After
    public void tearDown() throws Exception {
        playerScoreRule = null;
        defaultPlayerScoreRule = null;
    }

    @SuppressWarnings("serial")
    @Test
    public void testPlayerScoreRule() {
        playerScoreRule = new PlayerScoreRule() {

            @Override
            protected void applyRule() {

            }
        };
        assertNotNull(playerScoreRule);
        assertThat(playerScoreRule.getName(), is(equalTo("")));
        assertThat(playerScoreRule.getDescription(), is(equalTo("")));
        assertThat(playerScoreRule.getScore(), is(equalTo(BigInteger.ZERO)));
    }

    @SuppressWarnings("serial")
    @Test
    public void testPlayerScoreRuleBigInteger() {
        playerScoreRule = new PlayerScoreRule(BigInteger.valueOf(10)) {

            @Override
            protected void applyRule() {

            }
        };
        assertNotNull(playerScoreRule);
        assertThat(playerScoreRule.getName(), is(equalTo("")));
        assertThat(playerScoreRule.getDescription(), is(equalTo("")));
        assertThat(playerScoreRule.getScore(),
                is(equalTo(BigInteger.valueOf(10))));
    }

    @SuppressWarnings("serial")
    @Test
    public void testPlayerScoreRuleStringString() {
        playerScoreRule = new PlayerScoreRule("test", "test description") {

            @Override
            protected void applyRule() {

            }
        };
        assertNotNull(playerScoreRule);
        assertThat(playerScoreRule.getName(), is(equalTo("test")));
        assertThat(playerScoreRule.getDescription(),
                is(equalTo("test description")));
        assertThat(playerScoreRule.getScore(), is(equalTo(BigInteger.ZERO)));
    }

    @SuppressWarnings("serial")
    @Test
    public void testPlayerScoreRuleStringStringBigInteger() {
        playerScoreRule = new PlayerScoreRule("test", "test description",
                BigInteger.valueOf(10)) {

            @Override
            protected void applyRule() {

            }
        };
        assertNotNull(playerScoreRule);
        assertThat(playerScoreRule.getName(), is(equalTo("test")));
        assertThat(playerScoreRule.getDescription(),
                is(equalTo("test description")));
        assertThat(playerScoreRule.getScore(),
                is(equalTo(BigInteger.valueOf(10))));
    }

    @Test
    public void testGetScore() {
        assertNotNull(defaultPlayerScoreRule);
        assertThat(defaultPlayerScoreRule.getScore(),
                is(equalTo(BigInteger.ZERO)));
    }

    @Test
    public void testSetScoreBigInteger() {
        assertNotNull(defaultPlayerScoreRule);
        defaultPlayerScoreRule.setScore(new BigInteger("10"));
        assertThat(defaultPlayerScoreRule.getScore(),
                is(equalTo(BigInteger.valueOf(10))));
    }

    @Test
    public void testSetScoreLong() {
        assertNotNull(defaultPlayerScoreRule);
        defaultPlayerScoreRule.setScore(Long.valueOf("10"));
        assertThat(defaultPlayerScoreRule.getScore(),
                is(equalTo(BigInteger.valueOf(10))));
        defaultPlayerScoreRule.setScore(BigInteger.ZERO);
        assertThat(defaultPlayerScoreRule.getScore(),
                is(equalTo(BigInteger.ZERO)));
        defaultPlayerScoreRule.setScore(10);
        assertThat(defaultPlayerScoreRule.getScore(),
                is(equalTo(BigInteger.valueOf(10))));
    }
}
