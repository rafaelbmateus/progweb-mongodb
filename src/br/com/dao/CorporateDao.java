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
import br.com.model.Corporate;

public class CorporateDao {
	private Corporate corporate;
	private static String database = "bank-mongodb";
	private static String collection = "corporates";
	
	public CorporateDao(){
		
	}
	
	public CorporateDao(Corporate corporate){
		this.setCorporate(corporate);
	}
	
	
	public Corporate getCorporate(){
		return corporate;
	}
	
	public void setCorporate(Corporate corporate){
		this.corporate = corporate;
	}
	
	private String jsonCorporate(){
		Gson gson = new Gson();
		return gson.toJson(this.getCorporate());
	}
	
	public void create(){
		ConnectMongo mongo = ConnectMongo.getInstance();
		mongo.setDb(database, collection);
		mongo.inserir(this.jsonCorporate());
	}
	
	public List<Corporate> getList() {
		ConnectMongo mongo = ConnectMongo.getInstance();
		mongo.setDb(database, collection);
		DBCollection collectionMongo = mongo.getCollection();
		Gson gson = new Gson();
		List<Corporate> corporates = new ArrayList<Corporate>();
		for (String serializable : listOne(collectionMongo, null)) {
			Corporate corporate = new Corporate();
			corporate = gson.fromJson(serializable, Corporate.class);
			corporates.add(corporate);
		}
		return corporates;
	}
	
	public List<Corporate> consultOne(String key, String reg) {
		ConnectMongo mongo = ConnectMongo.getInstance();
		mongo.setDb(database, collection);

		DBCollection collectionMongo = mongo.getCollection();
		BasicDBObject query = new BasicDBObject();
		query.put(key, new BasicDBObject("$regex", reg).append("$options", "i"));
		Gson gson = new Gson();
		List<Corporate> funcionarios = new ArrayList<Corporate>();
		for (String serializable : listOne(collectionMongo, query)) {
			Corporate corporate = new Corporate();
			corporate = gson.fromJson(serializable, Corporate.class);
			funcionarios.add(corporate);
		}
		return funcionarios;
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
