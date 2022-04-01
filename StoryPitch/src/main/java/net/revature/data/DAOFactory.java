package net.revature.data;

// this class is responsible for instantiating/ retrning dao
public class DAOFactory {
	// initialize our pet dao to be null
	private static StoryPitchDAO storyToPitchDAO = null;
	private static UserDAO userDAO = null;
	private static GenreDAO genreDAO = null;
	private static RoleNameDAO rolaNameDAO = null;
	
	// make our constructor private, so it can't be accessed publicly
	private DAOFactory() {
		
	}
	
	public static StoryPitchDAO getStoryPitchDAO() {
		// make sure we are not recreating the dao if it already exists
		if(storyToPitchDAO == null) {
			storyToPitchDAO = new StoryPitchDAOImpl();
		}
		return storyToPitchDAO;
	}
	
	public static GenreDAO getGenreDAO() {
		//
		if(genreDAO == null) {
			genreDAO = new GenreDAOImp();
		}
		return genreDAO;
	}
	
	public static RoleNameDAO getRoleNameDAO() {
		//
		if(rolaNameDAO == null) {
			rolaNameDAO = new RoleNameDAOImp();
		}
		return rolaNameDAO;
	}
}
