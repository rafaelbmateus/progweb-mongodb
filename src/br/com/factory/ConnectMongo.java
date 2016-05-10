package br.com.factory;

import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBObject;
import com.mongodb.Mongo;
import com.mongodb.util.JSON;

@SuppressWarnings("deprecation")
public class ConnectMongo {

	private Mongo mongo;
	private DBCollection collection;
	private DB db;
	private DBObject dbObject;
	private static ConnectMongo instance = new ConnectMongo();

	private ConnectMongo() {
		this.mongo = new Mongo("localhost", 27017);
		
	}

	public static ConnectMongo getInstance() {
		return instance;
	}

	public Mongo getMongo() {
		return mongo;
	}

	public void setMongo(Mongo mongo) {
		this.mongo = mongo;
	}

	public DB getDb() {
		return db;
	}

	public void setDb(String db, String collectionName) {
		this.db = this.getMongo().getDB(db);
		this.collection = this.db.getCollection(collectionName);
	}
	
	public DBCollection getCollection(){
		return this.collection;
	}
	
	public DBObject getDbObject() {
		return dbObject;
	}

	public void setDbObject(DBObject dbObject) {
		this.dbObject = dbObject;
	}
	public void setDbObject(String dbObject) {
		this.dbObject = (DBObject) JSON.parse(dbObject);
	}
	public void inserir(String dbObject){
		this.setDbObject(dbObject);
		this.collection.insert(this.dbObject);
	}
	
}
