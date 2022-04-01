package net.revature.models;

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
import net.revature.data.StoryPitchDAO;
import net.revature.models.StoryPitch;

@TestMethodOrder(OrderAnnotation.class)
public class StoryPitchDAOTest {
	private static StoryPitchDAO storyPitchDAO = DAOFactory.getStoryPitchDAO();
	private static StoryPitch testPitch = new StoryPitch();
	private static StoryPitch testNewPitch = new StoryPitch();
	
	@BeforeAll
	public static void setUP() throws SQLException {
		// this is the base test pitch used for most tests
				testPitch.setTenativeTitle("test");
				
				// this is the pet to test create and delete
				Random rand = new Random();
				testNewPitch.setTenativeTitle("test_" + rand.nextLong());
				
				// TODO create pitches in DB with name "test"
				// and set the pitches ID to the returned value
				testPitch.setId(storyPitchDAO.create(testPitch));
			}
			
			@AfterAll
			public static void cleanUp() {
				// TODO remove pitches in DB with name containing "test"
			}
			
			@Test
			public void getByUserExists() {
				// TODO
			}
			
			@Test
			public void getByUserDoesNotExist() {
				// TODO
			}
			
			@Test
			public void getByStatus() {
				String testStatus = "Unsubmitted";
				List<StoryPitch> pitches = storyPitchDAO.getByStatus(testStatus);
				
				boolean onlyCorrectStatus = true;
				for (StoryPitch pitch : pitches) {
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
				int id = storyPitchDAO.create(testNewPitch);
				
				assertNotEquals(0, id);
			}
			
			@Test
			public void getByIdExists() {
				int id = testPitch.getId();
				
				StoryPitch pitch = storyPitchDAO.getById(id);
				
				assertEquals(testPitch, pitch);
			}
			
			@Test
			public void getByIdDoesNotExist() {
				StoryPitch pitch = storyPitchDAO.getById(0);
				assertNull(pitch);
			}
			
			@Test
			public void getAll() {
				assertNotNull(storyPitchDAO.getAll());
			}
			
			@Test
			public void updateUserExists() {
				assertDoesNotThrow(() -> {
					storyPitchDAO.update(testPitch);
				});
			}
			
			@Test
			public void updateUserDoesNotExist() {
				assertThrows(SQLException.class, () -> {
					storyPitchDAO.update(new StoryPitch());
				});
			}
			
			@Test
			@Order(2)
			public void deleteUserExists() {
				assertDoesNotThrow(() -> {
					storyPitchDAO.delete(testNewPitch);
				});
			}
			
			@Test
			public void deleteUserDoesNotExist() {
				assertThrows(SQLException.class, () -> {
					storyPitchDAO.delete(new StoryPitch());
				});
			}
	}

