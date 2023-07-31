/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.prismdown.hw7;

import java.util.Optional;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.result.InsertOneResult;

import org.bson.Document;

/**
 * @author kvarnoel
 */
public class ODMDModel {

    private static final MongoClient mongoClient = MongoClients.create();
    private static final MongoDatabase database = mongoClient.getDatabase("test");
    private static final MongoCollection<Document> collection = database.getCollection("data");

    private ODMDModel() {
    }

    public static boolean insertJson(String jsonObjectString) {
        Document document = Document.parse(jsonObjectString);
        Optional<InsertOneResult> result = Optional.of(collection.insertOne(document));
        return result.isPresent();
    }

}
