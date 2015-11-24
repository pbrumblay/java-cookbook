package com.llbean.prototype.cookbook;

public class Cookbook {
    private WriteModel writeModel = new WriteModel(); 
    private ReadModel readModel = new ReadModel();
    
    public Recipe findRecipe(int id) {
    	Recipe r = readModel.getByRecipeId(id);
    	
    	//business rule. can't look at invisible entries.
    	if(r != null && r.isVisible()) { 
    		return r;
    	} else return null;
    }
    
	public Recipe[] listRecipes() {
		return readModel
				.getAll()
				.filter(r -> r.isVisible())
				.toArray(Recipe[]::new);
	}
	
	public void addRecipe(Recipe r) throws Exception {
		if(r.getName() == null 
				|| r.getIngredients() == null 
				|| r.getIngredients().size() == 0) throw new Exception("Missing name or ingredients.");
		writeModel.createRecipe(r);
	}
	
	public void changeRecipe(Recipe r) throws Exception {
		if(r.getName() == null 
				|| r.getIngredients() == null 
				|| r.getIngredients().size() == 0) throw new Exception("Missing name or ingredients.");
		writeModel.saveRecipe(r);
	}
	
	public void throwOutRecipe(int id) {
		writeModel.deleteRecipe(id, (Recipe r) -> !r.isFavorite());
	}
}
