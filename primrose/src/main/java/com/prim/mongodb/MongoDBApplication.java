package com.prim.mongodb;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;

import com.prim.model.SimpleDocument;

@SpringBootApplication
@Import(AppConfig.class)
public class MongoDBApplication implements CommandLineRunner {
	
	@Autowired
	MongoOperations mongoOperations;
	
	public static void main(String[] args) {
		SpringApplication.run(MongoDBApplication.class, args);
	}
	
	@Override
	public void run(String... arg0) throws Exception {
		SimpleDocument simpleDocument = new SimpleDocument("document_1", "this is description for document 1");
		// save
		mongoOperations.save(simpleDocument);
		
		// build mongo query
		Query query = new Query(Criteria.where("name").is("document_1"));
		
		// search operation
		SimpleDocument object = (SimpleDocument) mongoOperations.findOne(query, SimpleDocument.class);
		System.out.println("##################  After: save document 1");
		System.out.println(object.toString());
		
		//update operation
		mongoOperations.updateFirst(query, Update.update("description", "update description"), SimpleDocument.class);
		
		// search a updated Simple Document
		SimpleDocument updatedObject = (SimpleDocument) mongoOperations.findOne(query, SimpleDocument.class);
		System.out.println("##################  After: update document 1");
		System.out.println(updatedObject.toString());
		
		// save other
		SimpleDocument other = new SimpleDocument("document_2", "this is description for document 2.");
		mongoOperations.save(other);
				
		// find all simple document in DB
		List<SimpleDocument> objLst =  mongoOperations.findAll(SimpleDocument.class);
		System.out.println("##################  After: save other");
		System.out.println(objLst.size());
		
		// delete a simple document in Db
		mongoOperations.remove(query, SimpleDocument.class);
		
		// find all simple document in DB
		objLst =  mongoOperations.findAll(SimpleDocument.class);
		System.out.println("##################  After: delete a simple document 1");
		System.out.println(objLst.size());
	
	}

}
