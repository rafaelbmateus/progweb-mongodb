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
import br.com.model.User;

public class UserDao {
	private User user;
	private static String database = "bank-mongodb";
	private static String collection = "users";

	public UserDao() {

	}

	public UserDao(User user) {
		this.setUser(user);
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	private String jsonUser() {
		Gson gson = new Gson();
		return gson.toJson(this.getUser());
	}

	public void create() {
		ConnectMongo mongo = ConnectMongo.getInstance();
		mongo.setDb(database, collection);
		mongo.inserir(this.jsonUser());
	}

	public List<User> getList() {
		ConnectMongo mongo = ConnectMongo.getInstance();
		mongo.setDb(database, collection);
		DBCollection collectionMongo = mongo.getCollection();
		Gson gson = new Gson();
		List<User> users = new ArrayList<User>();
		for (String serializable : listarOne(collectionMongo, null)) {
			User user = new User();
			user = gson.fromJson(serializable, User.class);
			users.add(user);
		}
		return users;
	}

	public List<User> consultarOne(String chave, String registro) {
		ConnectMongo mongo = ConnectMongo.getInstance();
		mongo.setDb(database, collection);

		DBCollection collectionMongo = mongo.getCollection();
		BasicDBObject query = new BasicDBObject();
		query.put(chave, new BasicDBObject("$regex", registro).append("$options", "i"));
		Gson gson = new Gson();
		List<User> funcionarios = new ArrayList<User>();
		for (String serializable : listarOne(collectionMongo, query)) {
			User user = new User();
			user = gson.fromJson(serializable, User.class);
			funcionarios.add(user);
		}
		return funcionarios;
	}

	private List<String> listarOne(DBCollection banco, DBObject query) {

		DBCursor cursor = banco.find(query);
		List<String> serializeble = new ArrayList<String>();
		while (cursor.hasNext()) {
			String json = JSON.serialize(cursor.next());
			serializeble.add(json);
		}
		return serializeble;
	}
	
}