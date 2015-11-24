package com.llbean.prototype.cookbook;

import java.util.*;
import java.io.Reader;
import java.util.concurrent.locks.*;
import java.util.stream.Stream;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;

//lifted from: http://docs.oracle.com/javase/7/docs/api/java/util/concurrent/locks/ReentrantReadWriteLock.html
public enum DataStore {
	INSTANCE;
	
    private final Map<Integer, Recipe> m = new TreeMap<Integer, Recipe>();
    private final ReentrantReadWriteLock rwl = new ReentrantReadWriteLock();
    private final Lock r = rwl.readLock();
    private final Lock w = rwl.writeLock();
    
    public void load(Reader reader) {
    	ObjectMapper mapper = new ObjectMapper();
    	mapper.setPropertyNamingStrategy(PropertyNamingStrategy.PASCAL_CASE_TO_CAMEL_CASE);
    	
    	Recipe[] recipes = null;
    	try {
    		recipes = mapper.readValue(reader, Recipe[].class);
    	} catch(Exception e) {
    		System.err.println("Error loading recipes. " + e.getMessage());
    	}
    	
    	for(Recipe r : recipes) {
    		save(r);
    	}
    }

    public Recipe get(Integer key) {
        r.lock();
        try { return m.get(key); }
        finally { r.unlock(); }
    }
    public Stream<Integer> allKeys() {
        r.lock();
        try {
        	return m.keySet().stream();        	
        }
        finally { r.unlock(); }
    }
    
    public Stream<Recipe> allValues() {
        r.lock();
        try {
        	return m.values().stream();        	
        }
        finally { r.unlock(); }
    }    
    
    public void update(Recipe value) {
    	int id = value.getId();
    	if(id > 0) {
	        w.lock();
	        try {  m.put(id, value); }
	        finally { w.unlock(); }
    	}
    }
    
    public void save(Recipe value) {
        w.lock();
        int id = value.getId();
        try {
        	if(id == 0) {
            	id = m.size() + 1;
            	while(m.containsKey(id)) {
            		id++;
            	}
            	value.setId(id);
        	}
        	m.put(id, value); 
        }
        finally { w.unlock(); }
    }
    
    public void remove(Integer key) {
        w.lock();
        try {
        	if(m.containsKey(key)) {
        		m.remove(key);
        	}
        }
        finally { w.unlock(); }
    }
    
    public void clear() {
        w.lock();
        try {  m.clear(); }
        finally { w.unlock(); }
    }    
 }