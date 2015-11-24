package com.llbean.prototype.cookbook;

import java.util.List;


public class Recipe {

	private int id;
	private String name;
	private String description;
	private String source;
	private String instructions;
	private String ingredientsSearchText;
	private boolean visible;
	private String categoryName;
	private boolean favorite;
	private List<Ingredient> ingredients;
	
	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}   
	
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public String getDescription() {
		return description;
	}
	
	public void setDescription(String description) {
		this.description = description;
	}
	
	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}

	public String getInstructions() {
		return instructions;
	}

	public void setInstructions(String instructions) {
		this.instructions = instructions;
	}

	public String getIngredientsSearchText() {
		return ingredientsSearchText;
	}

	public void setIngredientsSearchText(String ingredientsSearchText) {
		this.ingredientsSearchText = ingredientsSearchText;
	}

	public boolean isVisible() {
		return visible;
	}

	public void setVisible(boolean visible) {
		this.visible = visible;
	}
	
	public boolean isFavorite() {
		return this.favorite;
	}
	
	public void setFavorite(boolean favorite) {
		this.favorite = favorite;
	}

	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	public List<Ingredient> getIngredients() {
		return ingredients;
	}

	public void setIngredients(List<Ingredient> ingredients) {
		this.ingredients = ingredients;
	}
}
