package com.prim.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection="simpleDocument")
public class SimpleDocument {
	@Id
	String id;
	String name;
	String description;
	
	public SimpleDocument(String name, String description) {
		this.name = name;
		this.description = description;
	}
	
	@Override
	public String toString() {
		return String.format("[id = %s, name = %s, description = %s]", id, name, description);
	}
	
}
