package com.llbean.prototype.cookbook;

import java.net.URI;
import javax.ejb.Stateless;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

@Path("/recipes")
@Stateless
@Produces(MediaType.APPLICATION_JSON)
public class RecipesCollection {
 
	private Cookbook cookbook = new Cookbook();  
	
    @GET
    @Path("/{id}") 
    public Response get(@PathParam("id") int id) {
    	
    	// Lookup by ID
    	Recipe r = cookbook.findRecipe(id);
    	
    	// Return the customer (or 404 not found)
    	Response response;
    	if (r == null) {
    		response = Response.status(Status.NOT_FOUND).build();
    	} else {
    		response = Response.ok(r).build();
    	}   	
    	return response;
    }
    
    @GET
    @Path("/")
    public Response getAll(String search) {
    	
    	Recipe[] all = cookbook.listRecipes();
    	Response response;
    	if (all.length == 0) {
    		response = Response.status(Status.NOT_FOUND).build();
    	} else {
    		response = Response.ok(all).build();
    	}
    	return response;
    }
    
    @POST
    @Path("/")
    public Response create(Recipe recipe) {
    	Response response = null;
    	try {
    		cookbook.addRecipe(recipe);
    		URI location = new URI("recipes/" + recipe.getId());
    		response = Response.created(location).build();
    	} catch (Exception e) {
    		response = Response.serverError().build();
    	}
    	return response;
    }
    
    @PUT
    @Path("/{id}") 
    public Response save(@PathParam("id") int id, Recipe recipe) {
    	Response response;
    	try {
    		recipe.setId(id);
    		cookbook.changeRecipe(recipe);
    		response = Response.ok(recipe).build();
    	} catch (Exception e) {
    		response = Response.serverError().build();
    	}
    	return response;
    }
    
    @DELETE
    @Path("/{id}")
    public Response delete(@PathParam("id") int id) {
    	Response response;
    	try {
    		cookbook.throwOutRecipe(id);
    		response = Response.ok().build();
    	} catch (Exception e) {
    		response = Response.serverError().build();
    	}
    	return response;
    }
    
}