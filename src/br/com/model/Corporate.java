package br.com.model;

import java.util.List;

public class Corporate {
	private Oid _id;
	private String register;
	private String fantasy_name;
	private List<User> partners;
	
	public String getRegister() {
		return register;
	}
	public void setRegister(String register) {
		this.register = register;
	}
	public String getName() {
		return fantasy_name;
	}
	public void setName(String name) {
		this.fantasy_name = name;
	}
	public List<User> getPartners() {
		return partners;
	}
	public void setPartners(List<User> partners) {
		this.partners = partners;
	}
	
	public Oid get_id() {
		return _id;
	}

	public void set_id(Oid _id) {
		this._id = _id;
	}

	public class Oid {
		String $oid;

		public String get$oid() {
			return $oid;
		}

		public void set$oid(String $oid) {
			this.$oid = $oid;
		}

	}
}
