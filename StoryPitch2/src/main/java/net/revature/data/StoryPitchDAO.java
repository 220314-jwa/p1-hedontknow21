package net.revature.data;

import java.util.List;

import net.revature.models.StoryPitch;
import net.revature.models.User;

public interface StoryPitchDAO extends GenericDAO<StoryPitch> {
	// we can set the generic's type here since we are inheriting it
	// i've set it to Pet so a class that implements this interface will
	// have the types as Pet
	
	public List<StoryPitch> getByRole(User roleName);
	public List<StoryPitch> getByStatus(String status);
}
