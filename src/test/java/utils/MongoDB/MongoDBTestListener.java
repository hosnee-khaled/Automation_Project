package utils.MongoDB;

import com.mongodb.client.*;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import org.bson.Document;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

public class MongoDBTestListener {

    MongoCollection<Document> collection;
    MongoClient mongoClient;
    FindIterable<Document> docs;

    public void onStart(String collectionName) {
        mongoClient = MongoClients.create("mongodb+srv://hosneykhaledhosney_db_user:nti123Q@cluster0.gdtfgog.mongodb.net/?retryWrites=true&w=majority&appName=Cluster0");
        MongoDatabase database = mongoClient.getDatabase("final_project");
        collection = database.getCollection(collectionName);

        // Fetch documents
        docs = collection.find();
    }

    public String[][] getTwoDArray(String collectionName){
        onStart(collectionName);

        // Store all documents in a list for processing
        List<Document> docList = new ArrayList<>();
        Set<String> fieldNames = new LinkedHashSet<>(); // preserve field order

        for (Document doc : docs) {
            fieldNames.addAll(doc.keySet());
            docList.add(doc);
        }

        // Convert field names to array (columns)
        String[] columns = fieldNames.toArray(new String[0]);

        // Create 2D array [rows = documents][cols = fields]
        String[][] data = new String[docList.size()][columns.length];

        for (int i = 0; i < docList.size(); i++) {
            Document doc = docList.get(i);
            for (int j = 0; j < columns.length; j++) {
                Object value = doc.get(columns[j]);
                data[i][j] = (value != null) ? value.toString() : "";
            }
        }

        return data;
    }


}
