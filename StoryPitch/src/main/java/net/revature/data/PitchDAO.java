package net.revature.data;

import java.util.List;

import net.revature.models.Pitch;
import net.revature.models.User;

public interface PitchDAO extends GenericDAO<Pitch> {

	// we can set the generic's type here since we are inheriting it
		// i've set it to Pitch so a class that implements this interface will
		// have the types as Pitch

		public List<Pitch> getByRole(User role);
		public List<Pitch> getByStatus(String status);
		public List<Pitch> getByPitch(User user);
		
}
