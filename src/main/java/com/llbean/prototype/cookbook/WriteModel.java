package com.llbean.prototype.cookbook;

import java.util.function.Predicate;

public class WriteModel {
	public void saveRecipe(Recipe r) {
		DataStore.INSTANCE.save(r);
	}
	
	public void createRecipe(Recipe r) {
		r.setId(0);
		DataStore.INSTANCE.save(r);
	}
	
	public void deleteRecipe(int id, Predicate<Recipe> pred) {
		Recipe r = DataStore.INSTANCE.get(id);
		if(r != null && pred.test(r)) {
			DataStore.INSTANCE.remove(id);
		}
	}
}
