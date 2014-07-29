package com.darcstarsolutions.games.common.beans;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.math.BigInteger;

import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.hamcrest.core.IsNot.not;
import static org.hamcrest.core.IsSame.sameInstance;
import static org.junit.Assert.*;

public class GameObjectTest {
	private GameObject gameObject;

	@Before
	public void setUp() throws Exception {
		gameObject = new GameObject();
	}

	@After
	public void tearDown() throws Exception {
		gameObject = null;
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

}
