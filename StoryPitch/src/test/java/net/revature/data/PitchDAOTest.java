package net.revature.data;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.SQLException;
import java.util.List;
import java.util.Random;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;

import net.revature.data.DAOFactory;
import net.revature.data.PitchDAO;
import net.revature.data.UserDAO;
import net.revature.models.Pitch;

@TestMethodOrder(OrderAnnotation.class)
public class PitchDAOTest {
	private static UserDAO userDAO = DAOFactory.getUserDAO();
	private static PitchDAO pitchDAO = DAOFactory.getPitchDAO();
	private static Pitch testPitch = new Pitch();
	private static Pitch testNewPitch = new Pitch();
	
	@BeforeAll
	public static void setUP() throws SQLException {
		// this is the base test pitch used for most tests
				testPitch.setTenativeTitle("test");
				
				// this is the pitch to test create and delete
				Random rand = new Random();
				testNewPitch.setTenativeTitle("test_" + rand.nextLong());
				
				// TODO create pitches in DB with name "test"
				// and set the pitches ID to the returned value
				testPitch.setId(pitchDAO.create(testPitch));
			}
			
			@AfterAll
			public static void cleanUp() {
				// TODO remove pitches in DB with name containing "test"
				pitchDAO.delete(testPitch);
			}
			
			@Test
			public void getByUserExists() {
				// 
				
			}
			
			@Test
			public void getByUserDoesNotExist() {
				// TODO
			}
			
			@Test
			public void getByStatus() {
				String testStatus = "Unsubmitted";
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
				
				assertNotEquals(0, id);
			}
			
			@Test
			public void getByIdExists() {
				int id = testPitch.getId();
				
				Pitch pitch =pitchDAO.getById(id);
				
				assertEquals(testPitch, pitch);
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
			public void updateUserDoesNotExist() {
				assertThrows(SQLException.class, () -> {
					pitchDAO.update(new Pitch());
				});
			}
			
			@Test
			@Order(2)
			public void deleteUserExists() {
				assertDoesNotThrow(() -> {
					pitchDAO.delete(testNewPitch);
				});
			}
			
			@Test
			public void deleteUserDoesNotExist() {
				
				assertThrows(SQLException.class, () -> {
					pitchDAO.delete(new Pitch());
				});
			}

}
