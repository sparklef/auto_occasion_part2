package com.c_project.auto_occasion.connexion;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.client.MongoDatabase;

public class ConnexionMongo {
    private MongoClient mongoClient;
    private MongoDatabase database;
    public ConnexionMongo(String connectionString, String databaseName) {
        MongoClientURI uri = new MongoClientURI(connectionString);
        mongoClient = new MongoClient(uri);
        database = mongoClient.getDatabase(databaseName);
    }
    public MongoDatabase getDatabase() {
        return database;
    }
    public void close() {
        mongoClient.close();
    }
}
