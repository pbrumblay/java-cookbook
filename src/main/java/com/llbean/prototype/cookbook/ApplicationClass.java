package com.llbean.prototype.cookbook;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;

import javax.annotation.PostConstruct;
import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

import com.fasterxml.jackson.databind.AnnotationIntrospector;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.jaxrs.json.JacksonJaxbJsonProvider;
import com.fasterxml.jackson.module.jaxb.JaxbAnnotationIntrospector;

@ApplicationPath("api")
public class ApplicationClass extends Application {
    @Override
    public Set<Class<?>> getClasses() {
        final Set<Class<?>> classes = new HashSet<>();
        // register root resource
        classes.add(RecipesCollection.class);
        return classes;
    }
    
    @Override
    public Set<Object> getSingletons() {
      Set<Object> s = new HashSet<Object>();
      
      // Register Jackson as JAXB to JSON provider
      ObjectMapper mapper = new ObjectMapper();
      AnnotationIntrospector introspector =
          new JaxbAnnotationIntrospector(mapper.getTypeFactory());   
      mapper.setAnnotationIntrospector(introspector);
      
      JacksonJaxbJsonProvider jaxbProvider = new JacksonJaxbJsonProvider();
      jaxbProvider.setMapper(mapper);
      s.add(jaxbProvider);
      
      return s;
    }
    
    @PostConstruct
    public void initialize() {
    	InputStream is = getClass().getClassLoader().getResourceAsStream("/recipes.json");
   	
    	try {
    		DataStore.INSTANCE.load(new InputStreamReader(is));
    	} catch (Exception e) {
    		System.err.println("Could not open resource stream " + e.getMessage());
    	} finally {
    		try {
    			is.close();
    		} catch(Exception e2) {
    			System.err.println("Could not close input stream " + e2.getMessage());
    		}
    	}
    }    
}