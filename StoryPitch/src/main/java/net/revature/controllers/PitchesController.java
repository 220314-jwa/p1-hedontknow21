package net.revature.controllers;


import net.revature.models.User;
import io.javalin.http.Context;
import io.javalin.http.HttpCode;
import net.revature.exceptions.AlreadySubmittedException;
import net.revature.models.Pitch;
import net.revature.services.UserService;
import net.revature.services.UserServiceImpl;

public class PitchesController {
	private static UserService userServ = new UserServiceImpl();
	
	//GET /pitches from the database (unsubmitted)
	public static void getPitches(Context ctx) {
		ctx.json(userServ.viewUnSubmittedPitches());
	}
	// GET /pitchs from database (submitted)
	public static void getPitchs(Context ctx) {
		ctx.json(userServ.viewUnSubmittedPitches());
	}
	
	// GET /pitches or pitchs/{id} where id is the id of the pitch
	public static void getPitchById(Context ctx) {
		int pitchId = Integer.parseInt(ctx.pathParam("id"));
		Pitch pitch = userServ.getPitchtById(pitchId);
		if(pitch != null) {
			ctx.json(pitch);
		}else {
			ctx.status(HttpCode.NOT_FOUND); //404 code for not found in the server
		}
	}
	
	public static void submitPitch(Context ctx) {
		int pitchId = Integer.parseInt(ctx.pathParam("id"));
		Pitch pitchToSubmit = userServ.getPitchtById(pitchId);
		
		User user = ctx.bodyAsClass(User.class);
		
		try {
			user = userServ.submittedPitch(user, pitchToSubmit);
			
			ctx.json(user);
		} catch (AlreadySubmittedException e) {
			ctx.status(HttpCode.CONFLICT); // 409 conflict
		} catch (Exception e) {
			ctx.status(HttpCode.BAD_REQUEST); // 400 bad request
		}
	}

}
