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
import br.com.model.Address;

public class AddressDao {
	private Address address;
	private static String database = "bank-mongodb";
	private static String collection = "addresses";

	public AddressDao() {

	}

	public AddressDao(Address address) {
		this.setAddress(address);
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	private String jsonUser() {
		Gson gson = new Gson();
		return gson.toJson(this.getAddress());
	}

	public void create() {
		ConnectMongo mongo = ConnectMongo.getInstance();
		mongo.setDb(database, collection);
		mongo.inserir(this.jsonUser());
	}

	public List<Address> getList() {
		ConnectMongo mongo = ConnectMongo.getInstance();
		mongo.setDb(database, collection);
		DBCollection collectionMongo = mongo.getCollection();
		Gson gson = new Gson();
		List<Address> addresses = new ArrayList<Address>();
		for (String serializable : listOne(collectionMongo, null)) {
			Address address = new Address();
			address = gson.fromJson(serializable, Address.class);
			addresses.add(address);
		}
		return addresses;
	}

	public List<Address> consultOne(String key, String reg) {
		ConnectMongo mongo = ConnectMongo.getInstance();
		mongo.setDb(database, collection);

		DBCollection collectionMongo = mongo.getCollection();
		BasicDBObject query = new BasicDBObject();
		query.put(key, new BasicDBObject("$regex", reg).append("$options", "i"));
		Gson gson = new Gson();
		List<Address> addresses = new ArrayList<Address>();
		for (String serializable : listOne(collectionMongo, query)) {
			Address address = new Address();
			address = gson.fromJson(serializable, Address.class);
			addresses.add(address);
		}
		return addresses;
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
