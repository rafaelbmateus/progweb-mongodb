package br.com.dao;

import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.util.JSON;

import br.com.factory.ConnectMongo;
import br.com.model.Account;

public class AccountDao {
	private Account account;
	private static String database = "bank-mongodb";
	private static String collection = "accounts";

	public AccountDao() {

	}

	public AccountDao(Account account) {
		this.setAccount(account);
	}

	public void setAccount(Account account) {
		this.account = account;
	}

	public Account getAccount() {
		return account;
	}

	public String jsonAccount() {
		Gson gson = new Gson();
		return gson.toJson(this.getAccount());
	}

	public void create() {
		ConnectMongo mongo = ConnectMongo.getInstance();
		mongo.setDb(database, collection);
		mongo.inserir(this.jsonAccount());
	}

	public List<Account> getList() {
		ConnectMongo mongo = ConnectMongo.getInstance();
		mongo.setDb(database, collection);
		DBCollection collection = mongo.getCollection();
		Gson gson = new Gson();
		List<Account> accounts = new ArrayList<Account>();
		for (String serealizable : listOne(collection, null)) {
			Account account = new Account();
			account = gson.fromJson(serealizable, Account.class);
			accounts.add(account);
		}
		return accounts;
	}

	private List<String> listOne(DBCollection banco, DBObject query) {

		DBCursor cursor = banco.find(query);
		List<String> serializeble = new ArrayList<String>();
		while (cursor.hasNext()) {
			String json = JSON.serialize(cursor.next());
			serializeble.add(json);
		}
		return serializeble;
	}
}