package application;

import org.bson.Document;
import org.bson.conversions.Bson;
import org.bson.types.ObjectId;

import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;
import com.mongodb.ServerApi;
import com.mongodb.ServerApiVersion;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;

public class MongoDB {

	public static void main(String[] args) {

		String uri = "mongodb+srv://wanderlist:csye6200@wanderlist.kg0ftjr.mongodb.net/?retryWrites=true&w=majority";

		ServerApi serverApi = ServerApi.builder().version(ServerApiVersion.V1).build();

		MongoClientSettings settings = MongoClientSettings.builder().applyConnectionString(new ConnectionString(uri))
				.serverApi(serverApi).build();

		MongoClient mongoClient = MongoClients.create(settings);
		MongoDatabase database = mongoClient.getDatabase("sample_restaurants");
		MongoCollection<Document> collection = database.getCollection("neighborhoods");

		Bson filter = Filters.eq("_id", new ObjectId("55cb9c666c522cafdb053a1a"));
		collection.find(filter).forEach(doc -> System.out.println(doc.toJson()));
		collection.find(filter).forEach(doc -> System.out.println(doc.get("name")));

	}

}
