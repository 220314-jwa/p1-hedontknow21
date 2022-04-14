package net.revature.data;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.SQLException;
import java.util.List;
import java.util.Random;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;

import net.revature.data.DAOFactory;
import net.revature.data.PitchDAO;
import net.revature.data.UserDAO;
import net.revature.models.Pitch;
import net.revature.models.User;

@TestMethodOrder(OrderAnnotation.class)
public class PitchDAOTest {
	private static UserDAO userDAO = DAOFactory.getUserDAO();
	private static PitchDAO pitchDAO = DAOFactory.getPitchDAO();
	private static Pitch testPitch = new Pitch();
	private static Pitch testNewPitch = new Pitch();
	private static User testUser = new User();
	
	@BeforeAll
	public static void setUP() throws Exception {
		// this is the base test pitch used for most tests
				testPitch.setTenativeTitle("test");
				testPitch.setUsersId(1);
				
				// this is the pitch to test create and delete
				Random rand = new Random();
				testNewPitch.setTenativeTitle("test_" + rand.nextLong());
				
				// TODO create pitches in DB with name "test"
				// and set the pitches ID to the returned value
				testPitch.setId(pitchDAO.create(testPitch));
			}
			
			@AfterAll
			public static void cleanUp() throws SQLException {
				// TODO remove pitches in DB with name containing "test"
				pitchDAO.delete(testPitch);
			}
			
			@Test
			public void getByUserExists() {
				userDAO.getAll().contains(testUser);
				
			}
			
			@Test
			public void getByUserDoesNotExist() {
				List<User> users = userDAO.getAll();
				
				boolean doesUserExist = true;
				for(User user : users) {
					String userExist = user.getUserName().toLowerCase();
					if(!users.contains(userExist)) {
						doesUserExist = false;
						break;
					}
				}
						
			}
			
			@Test
			public void getByStatus(String testStatus) {
				testStatus = "Unsubmitted";
				List<Pitch> pitches = pitchDAO.getByStatus(testStatus);
				
				boolean onlyCorrectStatus = true;
				for (Pitch pitch : pitches) {
					if (!(pitch.getStatus().equals(testStatus))) {
						onlyCorrectStatus = false;
						break;
					}
				}
				assertTrue(onlyCorrectStatus);
			}
			
			@Test
			@Order(1)
			public void createPitchSuccessfully() throws SQLException {
				int id =pitchDAO.create(testNewPitch);
				
				assertNotEquals(1, id);
			}
			
			@Test
			public void getByIdExists() {
				int id = testPitch.getId();
				
				Pitch pitch =pitchDAO.getById(id);
				
				
			}
			
			@Test
			public void getByIdDoesNotExist() {
				Pitch pitch = pitchDAO.getById(0);
				assertNull(pitch);
			}
			
			@Test
			public void getAll() {
				assertNotNull(pitchDAO.getAll());
			}
			
			@Test
			public void updateUserExists() {
				assertDoesNotThrow(() -> {
					pitchDAO.update(testPitch);
				});
			}
			
			
			
			@Test
			@Order(2)
			public void deleteUserExists() {
				assertDoesNotThrow(() -> {
					pitchDAO.delete(testNewPitch);
				});
			}
			
			

}
