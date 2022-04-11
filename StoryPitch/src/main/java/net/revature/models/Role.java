package net.revature.models;

import java.util.Objects;
// creating the object of roles
public class Role {
// abstract fields
	private int id;
	
	private String roleName;
	//method
	public Role() {
		id = 1;
		
		roleName = "";
	}
//abstraction
	@Override
	public String toString() {
		return "Role [id=" + id + ", roleName=" + roleName + "]";
	}
//getters , setters
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}
//hash code
	@Override
	public int hashCode() {
		return Objects.hash(id, roleName);
	}
// abstraction of the object class
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Role other = (Role) obj;
		return id == other.id && Objects.equals(roleName, other.roleName);
	}
	
	
	
	
}
