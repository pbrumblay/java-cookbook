package com.llbean.prototype.cookbook;

import java.io.StringReader;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

public class DataStoreTest {

	@Before
	public void setUp() throws Exception {
		String s = String.format("%s\n%s\n%s\n%s\n%s\n%s\n%s\n%s\n%s\n%s\n%s"
		         , "[{"
		         , "\"Name\":\"Raw shrimp\","
		         , "\"Description\":\"Just eat it.\","
		         , "\"Visible\":true,"
		         , "\"Ingredients\":[{"
		         , "\"Name\":\"fresh whole shrimp\","
		         , "\"Amount\":\"2 lbs\""
		         , "}],"
		         , "\"CategoryName\":\"Desperate\","
		         , "\"Id\":1"
		         , "}]");
		DataStore.INSTANCE.load(new StringReader(s));
	}

	@Test
	public void testGet() {
		Recipe r = DataStore.INSTANCE.get(1);
		
		assertNotNull(r);
		assertNotNull(r.getIngredients());
		assertEquals("Raw shrimp", r.getName());
		assertEquals("Just eat it.", r.getDescription());
	}
}
