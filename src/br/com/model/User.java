package br.com.model;

public class User extends AccountHolder {
	private Oid _id;
	private int register;
	private String age;

	public int getRegister() {
		return register;
	}

	public void setRegister(int register) {
		this.register = register;
	}

	public String getAge() {
		return age;
	}

	public void setAge(String age) {
		this.age = age;
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
