package com.darcstarsolutions.games.common.beans.rules;

import com.darcstarsolutions.games.common.beans.Player;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.hamcrest.core.IsNot.not;
import static org.hamcrest.core.IsSame.sameInstance;
import static org.junit.Assert.*;

public class CompositeRuleTest {

    private CompositeRule<Player, PlayerRule> defaultCompositeRule;
    private CompositeRule<Player, PlayerRule> compositeRule;
    private Player player;
    private ChangePlayerScoreRule animal;
    private ChangePlayerScoreRule restaurant;

    @Before
    public void setUp() throws Exception {
        animal = new ChangePlayerScoreRule("Animal", "Animals give one",
                BigInteger.ONE);
        restaurant = new ChangePlayerScoreRule("Restaurant",
                "Restaurants give 5", BigInteger.valueOf(5));
        player = new Player("Test", "Test player");
        defaultCompositeRule = new CompositeRule<>();
    }

    @After
    public void tearDown() throws Exception {
        restaurant = null;
        animal = null;
        player = null;
        compositeRule = null;
        defaultCompositeRule = null;
    }

    @Test
    public void testApplyRule() {
        assertNotNull(player);
        assertNotNull(animal);
        assertNotNull(restaurant);
        assertNotNull(defaultCompositeRule);
        defaultCompositeRule.addRule(animal);
        defaultCompositeRule.addRule(restaurant);
        assertThat(player.getScore(), is(equalTo(BigInteger.ZERO)));
        defaultCompositeRule.apply(player);
        assertThat(player.getScore(), is(equalTo(BigInteger.valueOf(6))));

    }

    @Test
    public void testCompositeRule() {
        assertNotNull(defaultCompositeRule);
        assertThat(defaultCompositeRule.getName(), is(equalTo("")));
        assertThat(defaultCompositeRule.getDescription(), is(equalTo("")));
        assertNotNull(defaultCompositeRule.getRules());
        assertThat(defaultCompositeRule.getRules().isEmpty(), is(true));
    }

    @Test
    public void testCompositeRuleStringString() {
        compositeRule = new CompositeRule<>("test", "test description");
        assertNotNull(compositeRule);
        assertThat(compositeRule.getName(), is(equalTo("test")));
        assertThat(compositeRule.getDescription(),
                is(equalTo("test description")));
        assertNotNull(compositeRule.getRules());
        assertThat(compositeRule.getRules().isEmpty(), is(true));
    }

    @Test
    public void testCompositeRuleListOfRuleType() {
        List<PlayerRule> playerRules = new ArrayList<>();
        playerRules.add(animal);
        playerRules.add(restaurant);
        compositeRule = new CompositeRule<>(playerRules);
        assertNotNull(compositeRule);
        assertThat(compositeRule.getRules().size(), is(equalTo(2)));
        assertThat((ChangePlayerScoreRule) compositeRule.getRule(0),
                is(sameInstance(animal)));
        assertThat((ChangePlayerScoreRule) compositeRule.getRule(1),
                is(sameInstance(restaurant)));
    }

    @Test
    public void testCompositeRuleStringStringListOfRuleType() {
        List<PlayerRule> playerRules = new ArrayList<>();
        playerRules.add(animal);
        playerRules.add(restaurant);
        compositeRule = new CompositeRule<>("test", "test description",
                playerRules);
        assertNotNull(compositeRule);
        assertThat(compositeRule.getName(), is(equalTo("test")));
        assertThat(compositeRule.getDescription(),
                is(equalTo("test description")));
        assertThat(compositeRule.getRules().size(), is(equalTo(2)));
        assertThat((ChangePlayerScoreRule) compositeRule.getRule(0),
                is(sameInstance(animal)));
        assertThat((ChangePlayerScoreRule) compositeRule.getRule(1),
                is(sameInstance(restaurant)));
    }

    @Test
    public void testGetRules() {
        assertNotNull(defaultCompositeRule);
        assertNotNull(defaultCompositeRule.getRules());
        assertThat(defaultCompositeRule.getRules().isEmpty(), is(true));
    }

    @Test
    public void testSetRules() {
        assertNotNull(defaultCompositeRule);
        assertNotNull(defaultCompositeRule.getRules());
        assertThat(defaultCompositeRule.getRules().isEmpty(), is(true));
        List<PlayerRule> playerRules = new ArrayList<>();
        assertThat(defaultCompositeRule.getRules(),
                is(not(sameInstance(playerRules))));
        defaultCompositeRule.setRules(playerRules);
        assertThat(defaultCompositeRule.getRules(),
                is(sameInstance(playerRules)));
    }

    @Test
    public void testAddRule() {
        assertNotNull(defaultCompositeRule);
        assertNotNull(defaultCompositeRule.getRules());
        assertThat(defaultCompositeRule.getRules().isEmpty(), is(true));
        defaultCompositeRule.addRule(animal);
        assertThat(defaultCompositeRule.getRules().isEmpty(), is(false));
        assertThat(defaultCompositeRule.getRules().size(), is(1));
        assertThat((ChangePlayerScoreRule) defaultCompositeRule.getRule(0),
                is(sameInstance(animal)));
    }

    @Test
    public void testRemoveRuleRuleType() {
        assertNotNull(defaultCompositeRule);
        assertNotNull(defaultCompositeRule.getRules());
        assertThat(defaultCompositeRule.getRules().isEmpty(), is(true));
        defaultCompositeRule.addRule(animal);
        assertThat(defaultCompositeRule.getRules().isEmpty(), is(false));
        assertThat(defaultCompositeRule.getRules().size(), is(1));
        assertThat((ChangePlayerScoreRule) defaultCompositeRule.getRule(0),
                is(sameInstance(animal)));
        boolean result = defaultCompositeRule.removeRule(animal);
        assertTrue(result);
        assertThat(defaultCompositeRule.getRules().isEmpty(), is(true));
    }

    @Test
    public void testRemoveRuleInt() {
        assertNotNull(defaultCompositeRule);
        assertNotNull(defaultCompositeRule.getRules());
        assertThat(defaultCompositeRule.getRules().isEmpty(), is(true));
        defaultCompositeRule.addRule(animal);
        assertThat(defaultCompositeRule.getRules().isEmpty(), is(false));
        assertThat(defaultCompositeRule.getRules().size(), is(1));
        assertThat((ChangePlayerScoreRule) defaultCompositeRule.getRule(0),
                is(sameInstance(animal)));
        PlayerRule playerRule = defaultCompositeRule.removeRule(0);
        assertNotNull(playerRule);
        assertThat((ChangePlayerScoreRule) playerRule, is(sameInstance(animal)));
        assertThat(defaultCompositeRule.getRules().isEmpty(), is(true));
    }

    @Test
    public void testGetRule() {
        List<PlayerRule> rules = new ArrayList<>();
        rules.add(animal);
        compositeRule = new CompositeRule<>(rules);
        assertNotNull(compositeRule);
        assertThat((ChangePlayerScoreRule) compositeRule.getRule(0),
                is(sameInstance(animal)));
    }

    @Test
    public void testSetRuleIntRuleType() {
        List<PlayerRule> rules = new ArrayList<>();
        rules.add(animal);
        compositeRule = new CompositeRule<>(rules);
        assertNotNull(compositeRule);
        assertThat((ChangePlayerScoreRule) compositeRule.getRule(0),
                is(sameInstance(animal)));
        PlayerRule rule = compositeRule.setRule(0, restaurant);
        assertNotNull(rule);
        assertThat((ChangePlayerScoreRule) rule, is(sameInstance(animal)));
        assertThat((ChangePlayerScoreRule) compositeRule.getRule(0),
                is(sameInstance(restaurant)));
    }

}
