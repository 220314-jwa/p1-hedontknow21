package net.revature.data;



// this is a class responsible for instantiating and returning data based objects(dao's)
public class DAOFactory {
	
	// initialize our pet dao to be null
		private static PitchDAO pitchDAO = null;
		private static UserDAO userDAO = null;
		private static GenreDAO genreDAO = null;
		private static RoleDAO roleDAO = null;
		private static StatusDAO statusDAO = null;

		// make our constructor private, so it can't be accessed publicly
		private DAOFactory() {
			
		}
		
		public static StatusDAO getStatusDAO() {
			if(statusDAO == null) {
				statusDAO = new StatusPostgres();
			}
			return statusDAO;
		}
		
		
		public static UserDAO getUserDAO() {
			if(userDAO == null) {
				userDAO = new UserPostgres();
			}
			return userDAO;
		}

		

		public static PitchDAO getPitchDAO() {
			// make sure we are not recreating the dao if it already exists
			if(pitchDAO == null) {
				pitchDAO = new PitchPostgres();
			}
			return pitchDAO;
		}

		public static GenreDAO getGenreDAO() {
			//
			if(genreDAO == null) {
				genreDAO = new GenrePostgres();
			}
			return genreDAO;
		}

		public static RoleDAO getRoleDAO() {
			//
			if(roleDAO == null) {
				roleDAO = new RolePostgres();
			}
			return roleDAO;
		}

}
