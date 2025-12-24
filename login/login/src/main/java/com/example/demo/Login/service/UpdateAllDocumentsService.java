//package com.example.demo.Login.service;
//
//import com.mongodb.client.MongoCollection;
//import com.mongodb.client.MongoCursor;
//import com.mongodb.client.MongoDatabase;
//import org.bson.Document;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.data.mongodb.core.MongoTemplate;
//import org.springframework.stereotype.Service;
//
//@Service
//public class UpdateAllDocumentsService {
//
//    @Autowired
//    private MongoTemplate mongoTemplate;
//
//    public int updateAllCollections(Document inputData) {
//
//        MongoDatabase db = mongoTemplate.getDb();
//        int totalUpdated = 0;
//
//        // Loop through every collection
//        for (String collectionName : db.listCollectionNames()) {
//
//            MongoCollection<Document> collection = db.getCollection(collectionName);
//            MongoCursor<Document> cursor = collection.find().iterator();
//
//            // Update each document
//            while (cursor.hasNext()) {
//                Document doc = cursor.next();
//
//                // Add all key-value pairs from input JSON
//                for (String key : inputData.keySet()) {
//                    doc.put(key, inputData.get(key));
//                }
//
//                // Save updated document
//                collection.replaceOne(new Document("_id", doc.get("_id")), doc);
//
//                totalUpdated++;
//            }
//        }
//
//        return totalUpdated;
//    }
//}
//
