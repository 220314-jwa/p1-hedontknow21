package net.revature.models;

import java.util.ArrayList;
import java.util.List;

public class User {
	
	private int id;
	private String userName;
	private String passWord;
	private String firstName;
	private String lastName;
	private Role role;
	private List<Pitch> pitches;
	
	public User() {
		id = 0;
		userName = "";
		passWord = "";
		firstName = "";
		lastName = "";
		role = new Role();
		pitches = new ArrayList<>();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassWord() {
		return passWord;
	}

	public void setPassWord(String passWord) {
		this.passWord = passWord;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public Role getRole() {
		return role;
	}

	public void  setRole(Role role) {
		this.role = role;
	}

	public List<Pitch> getPitches() {
		return pitches;
	}

	public void setPitches(List<Pitch> pitches) {
		this.pitches = pitches;
	}
	
	
	// overriding the toString method
		public String toString() {
			return "Pitch [id=" + id +", username=" + userName + ", password=" + passWord + ", firstname="
					+ firstName + ", lastname=" + lastName + ", role=" + role + ", pitches=" + pitches +"]";
		}
		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			//result = prime * result + age;
			result = prime * result + ((userName == null) ? 0 : userName.hashCode());
			result = prime * result + ((passWord == null) ? 0 : passWord.hashCode());
			result = prime * result + id;
			result = prime * result + ((firstName == null) ? 0 : firstName.hashCode());
			result = prime * result + ((lastName == null) ? 0 : lastName.hashCode());
			result = prime * result + ((role == null) ? 0 : role.hashCode());
			result = prime * result + ((pitches == null) ? 0 : pitches.hashCode());
			return result;
		}
		
		
		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			User other = (User) obj;
			if (firstName == null) {
				if (other.firstName != null)
					return false;
			} else if (!firstName.equals(other.firstName))
				return false;
			if (id != other.id)
				return false;
			if (lastName == null) {
				if (other.lastName != null)
					return false;
			} else if (!lastName.equals(other.lastName))
				return false;
			if (passWord == null) {
				if (other.passWord != null)
					return false;
			} else if (!passWord.equals(other.passWord))
				return false;
			if (role == null) {
				if (other.role != null)
					return false;
			} else if (!role.equals(role))
				return false;
			if (userName == null) {
				if (other.userName != null)
					return false;
			} else if (!userName.equals(other.userName))
				return false;
			if (pitches == null) {
				if (other.pitches != null)
					return false;
			} else if (!pitches.equals(other.pitches))
				return false;
			return true;
		}

}
