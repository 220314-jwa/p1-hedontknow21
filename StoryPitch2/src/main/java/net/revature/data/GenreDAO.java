package net.revature.data;

public interface GenreDAO {
	// Converting string of genre into an integer value and vice versa
		public int getByGenreName(String genre);
		public String getByGenreId(int genre_id);

}
