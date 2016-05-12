package br.com.dao;

import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;
import com.mongodb.BasicDBObject;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.util.JSON;

import br.com.factory.ConnectMongo;
import br.com.model.Agency;

public class AgencyDao {
	private Agency agency;
	private static String database = "bank-mongodb";
	private static String collection = "agencies";

	public AgencyDao() {

	}

	public AgencyDao(Agency agency) {
		this.setAgency(agency);
	}

	public Agency getAgency() {
		return agency;
	}

	public void setAgency(Agency agency) {
		this.agency = agency;
	}

	private String jsonUser() {
		Gson gson = new Gson();
		return gson.toJson(this.getAgency());
	}

	public void create() {
		ConnectMongo mongo = ConnectMongo.getInstance();
		mongo.setDb(database, collection);
		mongo.inserir(this.jsonUser());
	}

	public List<Agency> getList() {
		ConnectMongo mongo = ConnectMongo.getInstance();
		mongo.setDb(database, collection);
		DBCollection collectionMongo = mongo.getCollection();
		Gson gson = new Gson();
		List<Agency> agencies = new ArrayList<Agency>();
		for (String serializable : listOne(collectionMongo, null)) {
			Agency agency = new Agency();
			agency = gson.fromJson(serializable, Agency.class);
			agencies.add(agency);
		}
		return agencies;
	}

	public List<Agency> consultOne(String key, String reg) {
		ConnectMongo mongo = ConnectMongo.getInstance();
		mongo.setDb(database, collection);

		DBCollection collectionMongo = mongo.getCollection();
		BasicDBObject query = new BasicDBObject();
		query.put(key, new BasicDBObject("$regex", reg).append("$options", "i"));
		Gson gson = new Gson();
		List<Agency> agencies = new ArrayList<Agency>();
		for (String serializable : listOne(collectionMongo, query)) {
			Agency agency = new Agency();
			agency = gson.fromJson(serializable, Agency.class);
			agencies.add(agency);
		}
		return agencies;
	}

	private List<String> listOne(DBCollection database, DBObject query) {

		DBCursor cursor = database.find(query);
		List<String> serializeble = new ArrayList<String>();
		while (cursor.hasNext()) {
			String json = JSON.serialize(cursor.next());
			serializeble.add(json);
		}
		return serializeble;
	}
}
