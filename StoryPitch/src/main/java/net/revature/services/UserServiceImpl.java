package net.revature.services;

import java.util.List;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.stream.Collectors;

import net.revature.exceptions.AlreadySubmittedException;
import net.revature.exceptions.IncorrectCredentialsException;
import net.revature.exceptions.UserNameAlreadyExistsException;
import net.revature.models.Pitch;
import net.revature.models.User;
import net.revature.data.DAOFactory;
import net.revature.data.PitchDAO;
import net.revature.data.UserDAO;

public class UserServiceImpl implements UserService {
	private UserDAO userDao = DAOFactory.getUserDAO();
	private PitchDAO pitchDao = DAOFactory.getPitchDAO();

	@Override
	public User logIn(String username, String password) throws IncorrectCredentialsException {
		User user = userDao.getByUsername(username);
		if (user != null && user.getPassWord().equals(password)) {
			return user;
		} else {
			throw new IncorrectCredentialsException();
		}

	}

	@Override
	public User registerUserSuccessfully(User newUser) throws UserNameAlreadyExistsException, SQLException {
		int id = userDao.create(newUser);

		if (id != 0) {
			newUser.setId(id);
			return newUser;
		} else {
			throw new UserNameAlreadyExistsException();
		}

	}

	@Override
	public User submittedPitch(User user, Pitch storyToPitch) throws AlreadySubmittedException {
		// check storyToPitch with its database entry to get most up-to-date status
		storyToPitch = pitchDao.getById(storyToPitch.getId());

		// make sure the pitch is not already submitted
		if (storyToPitch.getStatus().equals("Submitted")) {
			throw new AlreadySubmittedException();
		} else {
			// check user to make sure account is valid
			user = userDao.getById(user.getId());
			if (user != null) {
				storyToPitch.setStatus("Submitted");
				user.getPitches().add(storyToPitch);
				pitchDao.update(storyToPitch);
				userDao.update(user);
			}
			return user;
		}

	}

	@Override
	public Pitch getPitchById(int id) {

		return pitchDao.getById(id);
	}

	@Override
	public List<Pitch> viewUnSubmittedPitches() {

		return pitchDao.getByStatus("Unsubmitted");

		// alternative using Java 8 Streams (less SQL, more Java)
		// if you're interested :)
	}

	@Override
	public List<Pitch> viewSubmittedPitches() {

		return pitchDao.getByStatus("Submitted");

		// alternative using Java 8 Streams (less SQL, more Java)
		// if you're interested :)

	}

	@Override
	public List<Pitch> searchPitchByStatus(String status) {
		List<Pitch> pitches = pitchDao.getAll();

		// Java 8 Streams way (more efficient) with a lambda
		pitches = pitches.stream().filter((pitch) -> pitch.getStatus().toLowerCase().contains(status.toLowerCase()))
				.collect(Collectors.toList());
		return pitches;

		// without Streams
//		List<Pitch> pitchesWithStatus = new LinkedList<>();
//		for (int i = 0; i < pitches.size(); i++) {
//			// if the pitch's status is equal to the status passed in
//			// (toLowerCase to allow it to be case-insensitive)
//			if (pitches.get(i).getStatus().toLowerCase().equals(status.toLowerCase())) {
//				pitchesWithStatus.add(pitches.get(i));
//			}
//		}
//
//		return pitchesWithStatus;
	}


	@Override
	public User getUserById(int id) {

		return userDao.getById(id);
	}

	@Override
	public List<Pitch> getAllPitches() {
		return pitchDao.getAll();
	}
}