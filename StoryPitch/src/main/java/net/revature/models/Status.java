package net.revature.models;

import java.util.Objects;

public class Status {

	private  int id;
	
	private String statusName;
	
	public Status() {
		id = 0;
		
		statusName = "";
		
	}

	@Override
	public String toString() {
		return "Status [id=" + id + ", statusName=" + statusName + "]";
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getStatusName() {
		return statusName;
	}

	public void setStatusName(String statusName) {
		this.statusName = statusName;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, statusName);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Status other = (Status) obj;
		return id == other.id && Objects.equals(statusName, other.statusName);
	}
	
	
}
