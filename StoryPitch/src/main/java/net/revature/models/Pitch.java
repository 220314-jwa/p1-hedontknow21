package net.revature.models;

import java.sql.Date;
import java.time.LocalDate;

public class Pitch {

	// create abstract fields
	private int id;
	private String tenativeTitle;
	private Date expCompletionDate;
	private String lengthType;
	private String oneSentenceBlurb;
	private String description;
	private String status;
	private String role;
	private String genre;
	private int  usersId;
	
	
	
	public Pitch() {
		id = 0;
		usersId = getUsersId();
		tenativeTitle = "";
		expCompletionDate = getExpCompletionDate();
		lengthType = "";
		oneSentenceBlurb = "";
		description = "";
		status = "";
		role = "";
		genre = "";
	}
	
	
	// Create getters and setters for each field
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getTenativeTitle() {
		return tenativeTitle;
	}
	public void setTenativeTitle(String tenativeTitle) {
		this.tenativeTitle = tenativeTitle;
	}
	public Date getExpCompletionDate() {
		return expCompletionDate;
	}
	public void setExpCompletionDate(Date expCompletionDate) {
		this.expCompletionDate = expCompletionDate;
	}
	public String getLengthType() {
		return lengthType;
	}
	public void setLengthType(String lengthType) {
		this.lengthType = lengthType;
	}
	public String getOneSentenceBlurb() {
		return oneSentenceBlurb;
	}
	public void setOneSentenceBlurb(String oneSentenceBlurb) {
		this.oneSentenceBlurb = oneSentenceBlurb;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	public String getGenre() {
		return genre;
	}
	public void setGenre(String genre) {
		this.genre = genre;
	}
	

	public int getUsersId() {
		return usersId;
	}


	public void setUsersId(int usersId) {
		this.usersId = usersId;
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		//result = prime * result + age;
		result = prime * result + ((description == null) ? 0 : description.hashCode());
		result = prime * result + ((tenativeTitle == null) ? 0 : tenativeTitle.hashCode());
		result = prime * result + ((status == null) ? 0 : status.hashCode());
		result = prime * result + id ;
		result = prime * result + usersId ;
		result = prime * result + ((lengthType == null) ? 0 : lengthType.hashCode());
		result = prime * result + ((oneSentenceBlurb == null) ? 0 : oneSentenceBlurb.hashCode());
		result = prime * result + ((role == null) ? 0 : role.hashCode());
		result = prime * result + ((genre == null) ? 0 : genre.hashCode());
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
		Pitch other = (Pitch) obj;
		if (expCompletionDate != other.expCompletionDate)
			return false;
		if (description == null) {
			if (other.description != null)
				return false;
		} else if (!description.equals(other.description))
			return false;
		if (id != other.id)
			return false;
		if (lengthType == null) {
			if (other.lengthType != null)
				return false;
		} else if (!lengthType.equals(other.lengthType))
			return false;
		if (oneSentenceBlurb == null) {
			if (other.oneSentenceBlurb != null)
				return false;
		} else if (!oneSentenceBlurb.equals(other.oneSentenceBlurb))
			return false;
		if (tenativeTitle == null) {
			if (other.tenativeTitle != null)
				return false;
		} else if (!tenativeTitle.equals(other.tenativeTitle))
			return false;
		if (role == null) {
			if (other.role != null)
				return false;
		} else if (!role.equals(other.role))
			return false;
		if (genre == null) {
			if (other.genre != null)
				return false;
		} else if (!genre.equals(other.genre))
			return false;
		
		return true;
	}

	@Override
	public String toString() {
		// Overriding the toString
		return "Pitch [id=" + id +", usersId=" +usersId + ", tenativeTitle=" + tenativeTitle + ", expCompletionDate=" + expCompletionDate + ", legnthType="
		+ lengthType + ", oneSentenceBlurb=" + oneSentenceBlurb + ", description=" + description +  ", role=" + role + ", genre=" + genre +"]";


	}
	
	
	
	
		
	
	
	
}
