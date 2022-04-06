package net.revature.app;
import java.util.Map;

import net.revature.exceptions.AlreadySubmittedException;
import net.revature.exceptions.IncorrectCredentialsException;

import net.revature.data.DAOFactory;
import net.revature.data.PitchDAO;
import net.revature.exceptions.UserNameAlreadyExistsException;
import net.revature.models.Pitch;
import net.revature.models.User;
import net.revature.services.UserService;
import net.revature.services.UserServiceImpl;
import io.javalin.Javalin;
import io.javalin.http.HttpCode;


public class StoryPitchApp {
	
	private static UserService userServ = new UserServiceImpl();;

	public static void main(String[] args) {
		// set up endpoints
		Javalin app = Javalin.create();
		app.start();

		// just to test creating a pitch
		// post method , takes in a function
		/*
		 * we can test this out in postman by making a post request with this url:
		 * http://localhost:8080/pitches and pass in the pitch object in the body
		 */
		app.post("/pitches", ctx -> {
			// because this is a function, we actually write code in here:
			// create a pitch object
			// we get this date from the body of the http request
			Pitch pitch = ctx.bodyAsClass(Pitch.class);
			// get out pitch dao from the factory
			PitchDAO pitchDAO = DAOFactory.getPitchDAO();
			// try to insert pitch obj into database
			int id = pitchDAO.create(pitch);
			ctx.result("Generated id :" + id);
			System.out.println(id);
		});
		// get pitch by id
		app.get("/pitches/{id}", ctx -> {
			int id = Integer.parseInt(ctx.pathParam("id"));
			// get our story dao from the dao factory
			PitchDAO pitchDAO = DAOFactory.getPitchDAO();
			Pitch resultPitch = pitchDAO.getById(id);
			System.out.println(resultPitch);
			ctx.json(resultPitch);

		});

		//Get to /pitches: get available pitches
		app.get("/pitches", ctx -> {
			// .json() is an alternative to .result() that
			// sets the data type as JSON, the format that we
			// will be sending/receiving data in!

			ctx.json(userServ.viewSubmittedPitches());
		});

		// POST to /users: register a new user
		app.post("/users", ctx -> {
			// context bodyAsClass method will transform JSON data into
			// a Java object as long as the JSON keys match the Java fields
			User newUser = ctx.bodyAsClass(User.class);

			try {
				newUser = userServ.register(newUser);
				ctx.json(newUser);
			} catch (UserNameAlreadyExistsException e) {
				ctx.status(HttpCode.CONFLICT); // 409 conflict - 
			}

		});

		// POST to /auth: log in
		app.post("/auth", ctx -> {
			// if the keys in JSON data don't exactly match a class,
			// we can just use a Map!
			Map<String, String> credentials = ctx.bodyAsClass(Map.class);
			String username = credentials.get("username");
			String password = credentials.get("password");

			try {
				User user = userServ.logIn(username, password);
				ctx.json(user);
			} catch (net.revature.exceptions.IncorrectCredentialsException e) {
				ctx.status(HttpCode.UNAUTHORIZED); // 401 unauthorized - must be logged in
			}
			
		});

		// PUT to /pitches/{id}/status where {id} will be a number (a pitch ID): submit
		// pitch
		app.put("/pitches/{id}/status", ctx -> {
			// first we can grab the ID from the URI
			// since it is part of the path (URI) it is a path parameter
			// so we use ctx.pathParam and use the name we specified in
			// the path above
			int pitchId = Integer.parseInt(ctx.pathParam("id"));
			Pitch pitchSumbit = userServ.getPitchtById(pitchId);

			// now we need to get the User from the request body
			User user = ctx.bodyAsClass(User.class);

			try {
				// now we have everything we need to submit a pitch
				user = userServ.submittedPitch(user, pitchSumbit);

				// then we can return the updated user
				ctx.json(user);
			} catch (AlreadySubmittedException e) {
				ctx.status(HttpCode.CONFLICT); // 409 conflict
			}
		});

	}
}
