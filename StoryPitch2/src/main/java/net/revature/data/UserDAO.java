package net.revature.data;

import net.revature.models.User;

public interface UserDAO extends GenericDAO<User>{

	//we can set the generic's type here since we are inheriting it
		//i've set it to User so a class that implements this interface will
		//have the types as User
		public User getByUsername(String username);
}
