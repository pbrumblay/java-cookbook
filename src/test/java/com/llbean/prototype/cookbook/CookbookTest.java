package com.llbean.prototype.cookbook;

import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.After;
import org.junit.Test;

public class CookbookTest {

	private Cookbook sut;
	
	@Before
	public void setUp() throws Exception {
		String s = "[{"
		         + "\"Name\":\"Raw shrimp\","
		         + "\"Description\":\"Just eat it.\","
		         + "\"Visible\":true,"
		         + "\"Ingredients\":[{"
		         + "\"Name\":\"fresh whole shrimp\","
		         + "\"Amount\":\"2 lbs\""
		         + "}],"
		         + "\"CategoryName\":\"Desperate\","
		         + "\"Id\":1"
		         + "},{"
		         + "\"Name\":\"Street tacos\","
		         + "\"Description\":\"Danger, Will Robinson!\","
		         + "\"Visible\":false,"
		         + "\"Ingredients\":[{"
		         + "\"Name\":\"Mystery meat\","
		         + "\"Amount\":\"4 oz\""
		         + "}],"
		         + "\"CategoryName\":\"Food. Maybe.\","
		         + "\"Id\":2"		
		         + "},{"
		         + "\"Name\":\"Ramen\","
		         + "\"Description\":\"Cheap, but tasty!\","
		         + "\"Visible\":true,"
		         + "\"Ingredients\":[{"
		         + "\"Name\":\"Noodles\","
		         + "\"Amount\":\"8 oz\""
		         + "}],"
		         + "\"CategoryName\":\"College food.\","
		         + "\"Id\":3"		         
		         + "}]";
		DataStore.INSTANCE.load(new StringReader(s));
		
		sut = new Cookbook();
	}
	
	@After
	public void tearDown() {
		DataStore.INSTANCE.clear();
	}

	@Test
	public void testFindRecipe() {
		Recipe r = sut.findRecipe(1);
		
		assertNotNull(r);
		assertNotNull(r.getIngredients());
		assertEquals("Raw shrimp", r.getName());
		assertEquals("Just eat it.", r.getDescription());		
	}
	
	@Test
	public void testCantFindInvisibleRecipe() {
		Recipe r = sut.findRecipe(2);
		
		assertNull(r);
	}
	
	@Test
	public void testListRecipes() {
		Recipe[] list = sut.listRecipes();
		assertNotNull(list);
		assertEquals(2, list.length);
		assertEquals(1, list[0].getId());
		assertEquals(3, list[1].getId());
	}

	@Test
	public void testAddRecipe() {
		//arrange
		Recipe r = new Recipe();
		r.setName("Pizza");
		r.setCategoryName("Boring");
		r.setDescription("Plain Jane American food.");
		r.setVisible(true);
		List<Ingredient> ingredients = new ArrayList<Ingredient>();
		Ingredient ing = new Ingredient();
		ing.setAmount("enough");
		ing.setName("dough");
		ingredients.add(ing);
		r.setIngredients(ingredients);
		
		//act
		try {
			sut.addRecipe(r);
		} catch(Exception e) {
			fail("Failed with " + e.getMessage());
		}
		Recipe found = sut.findRecipe(r.getId());
		
		//assert
		assertTrue(r.getId() > 0);
		assertNotNull(found);
		assertEquals(r.getId(), found.getId());
	}

}
