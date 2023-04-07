package application;

import static com.mongodb.MongoClientSettings.getDefaultCodecRegistry;
import static org.bson.codecs.configuration.CodecRegistries.fromProviders;
import static org.bson.codecs.configuration.CodecRegistries.fromRegistries;

import org.bson.codecs.configuration.CodecProvider;
import org.bson.codecs.configuration.CodecRegistry;
import org.bson.codecs.pojo.PojoCodecProvider;

import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;
import com.mongodb.ServerApi;
import com.mongodb.ServerApiVersion;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

import model.Trip.Trip;
import model.Trip.TripDirectory;
import model.User.User;

public class MongoDB {

	private static String uri = "mongodb+srv://wanderlist:csye6200@wanderlist.kg0ftjr.mongodb.net/?retryWrites=true&w=majority";
	private static ServerApi serverApi = ServerApi.builder().version(ServerApiVersion.V1).build();
	private static MongoClientSettings settings = MongoClientSettings.builder()
			.applyConnectionString(new ConnectionString(uri)).serverApi(serverApi).build();

	private static CodecProvider pojoCodecProvider = PojoCodecProvider.builder().automatic(true).build();
	private static CodecRegistry pojoCodecRegistry = fromRegistries(getDefaultCodecRegistry(),
			fromProviders(pojoCodecProvider));

	private static MongoClient mongoClient = MongoClients.create(uri);
	private static MongoDatabase database = mongoClient.getDatabase("wanderlistDB")
			.withCodecRegistry(pojoCodecRegistry);

	public static void main(String[] args) {

//		String uri = "mongodb+srv://wanderlist:csye6200@wanderlist.kg0ftjr.mongodb.net/?retryWrites=true&w=majority";
//
//		ServerApi serverApi = ServerApi.builder().version(ServerApiVersion.V1).build();
//
//		MongoClientSettings settings = MongoClientSettings.builder().applyConnectionString(new ConnectionString(uri))
//				.serverApi(serverApi).build();

		// OLD CONNECTION
//		MongoClient mongoClient = MongoClients.create(settings);
//		MongoDatabase database = mongoClient.getDatabase("wanderlistDB");
//		MongoCollection<Document> collection = database.getCollection("users");

		// OLD WAY TO TEST IF CONNECTED

//		Bson filter = Filters.eq("_id", new ObjectId("55cb9c666c522cafdb053a1a"));
//		collection.find(filter).forEach(doc -> System.out.println(doc.toJson()));
//		collection.find(filter).forEach(doc -> System.out.println(doc.get("name")));

//		CodecProvider pojoCodecProvider = PojoCodecProvider.builder().register("org.example.pojos").build();
//		CodecRegistry pojoCodecRegistry = fromRegistries(getDefaultCodecRegistry(), fromProviders(pojoCodecProvider));
//
//		ClassModel<User> classModel = ClassModel.builder(User.class).build();		

		// POJO
//		CodecProvider pojoCodecProvider = PojoCodecProvider.builder().automatic(true).build();
//		CodecRegistry pojoCodecRegistry = fromRegistries(getDefaultCodecRegistry(), fromProviders(pojoCodecProvider));
//
//		MongoClient mongoClient = MongoClients.create(uri);
//		MongoDatabase database = mongoClient.getDatabase("wanderlistDB").withCodecRegistry(pojoCodecRegistry);
//		MongoCollection<User> collection = database.getCollection("users", User.class);
//		User user = new User("anita@gamil.com", "anita");
////		user.getTrips().addNewTrip("NYC", "USA", "NYC", 2023, 2, 1, 2023, 2, 4, null);
//		collection.insertOne(user);
//
//		// return all documents in the collection
//		List<User> userlist = new ArrayList<>();
//		collection.find().into(userlist);
//		System.out.println(userlist);

		// --------TESTING

//		User user = new User("anita@gmail.com", "anita");

//		TripDirectory tripDirectory = new TripDirectory(user);

//		Document doc = new Document().append("user", user);
//		collection.insertOne(doc);

//		InsertOneResult result = collection.insertOne(doc1);
//		System.out.println("Inserted a document with the following id: " 
//		    + result.getInsertedId().asObjectId().getValue());

		User user = new User("anita@gamil.com", "anita");
		user.getTrips().addNewTrip("NYC", "USA", "NYC", 2023, 2, 1, 2023, 2, 4, null);
		addNewUser(user);

		System.out.println("ADD SUCCESS");

	}

	public static void addNewUser(User user) {
		addNewTripDirectory(user);
		MongoCollection<User> collection = database.getCollection("users", User.class);
		collection.insertOne(user);
	}

	public static void addNewTripDirectory(User user) {
		MongoCollection<TripDirectory> collection = database.getCollection("tripDirectories", TripDirectory.class);
		collection.insertOne(user.getTrips());
	}

	public static void addNewTrip(User user) {
		MongoCollection<Trip> collection = database.getCollection("trips", Trip.class);
		collection.insertOne(user.getTrips().getTrips().get(0));
	}

}
