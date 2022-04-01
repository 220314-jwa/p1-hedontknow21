package net.revature.services;

import java.util.List;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.stream.Collectors;

import net.revature.exceptions.AlreadySubmittedException;
import net.revature.exceptions.IncorrectCredentialsException;
import net.revature.exceptions.UserNameAlreadyExistsException;
import net.revature.models.StoryPitch;
import net.revature.models.User;
import net.revature.data.StoryPitchDAO;
import net.revature.data.UserDAO;

public class UserServiceImp implements UserService {
	private UserDAO userDao;
	private StoryPitchDAO storyPitchDao;
	
	

	@Override
	public User logIn(String username, String password) throws IncorrectCredentialsException {
		User user = userDao.getByUsername(username);
		if(user != null && user.getPassWord().equals(password)) {
			return user;
		}else {
			throw new IncorrectCredentialsException();
		}
		
	}

	@Override
	public User register(User newUser) throws UserNameAlreadyExistsException, SQLException {
		int id = userDao.create(newUser);
		if(id != 0) {
			newUser.setId(id);
			return newUser;
		}
		return null;
	}

	@Override
	public User submittedPitch(User user, StoryPitch storyToPitch) throws AlreadySubmittedException {
		// check storyToPitch with its database entry to get most up-to-date status
		storyToPitch = storyPitchDao.getById(storyToPitch.getId());
		
		// make sure the pitch is not already submitted
		if(storyToPitch.getStatus().equals("Submitted")) {
			throw new AlreadySubmittedException();
		}else {
			// check user to make sure account is valid
			user = userDao.getById(user.getId());
			if(user != null) {
				storyToPitch.setStatus("Submitted");
				user.getPitches().add(storyToPitch);
				storyPitchDao.update(storyToPitch);
				userDao.update(user);
			}
			return user;
		}
		
	}

	@Override
	public StoryPitch getPitchtById(int id) {
		
		return storyPitchDao.getById(id);
	}

	@Override
	public List<StoryPitch> viewSubmittedPitches() {
		
		return storyPitchDao.getByStatus("Submitted");
		
		// alternative using Java 8 Streams (less SQL, more Java)
				// if you're interested :)
//				List<Pet> pets = petDao.getAll();
//				pets = pets.stream()
//						.filter((pet) -> pet.getStatus().equals("Available"))
//						.collect(Collectors.toList());
//				return pets;
	}

	@Override
	public List<StoryPitch> searchPitchByStatus(String status) {
		List<StoryPitch> pitches = storyPitchDao.getAll();
		
		// Java 8 Streams way (more efficient) with a lambda
//		pets = pets.stream()
//				.filter((pet) -> pet.getSpecies().toLowerCase().contains(species.toLowerCase()))
//				.collect(Collectors.toList());
//		return pets;
		
		// without Streams
		List<StoryPitch> pitchesWithStatus = new LinkedList<>();
		for(int i = 0; i<pitches.size(); i++) {
			// if the pet's species is equal to the species passed in
			// (toLowerCase to allow it to be case-insensitive)
			if(pitches.get(i).getStatus().toLowerCase().equals(status.toLowerCase())) {
				pitchesWithStatus.add(pitches.get(i));
			}
		}
		
		return pitchesWithStatus;
	}

}
