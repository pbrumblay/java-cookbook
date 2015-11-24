package com.llbean.prototype.cookbook;

import java.util.stream.Stream;

public class ReadModel {
	public Recipe getByRecipeId(int id) {
		return DataStore.INSTANCE.get(id);
	}

	public Stream<Recipe> getAll() {
		return DataStore.INSTANCE.allValues();
	}
}
