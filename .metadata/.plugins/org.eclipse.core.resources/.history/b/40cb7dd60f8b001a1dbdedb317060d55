package com.bridgelabz.fundoonotes.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.bridgelabz.fundoonotes.dto.LoginDto;
import com.bridgelabz.fundoonotes.dto.RegisterDto;
import com.bridgelabz.fundoonotes.dto.UpdatePassword;
import com.bridgelabz.fundoonotes.exception.EmailSentFailedException;
import com.bridgelabz.fundoonotes.exception.RemainderException;
import com.bridgelabz.fundoonotes.exception.UserException;
import com.bridgelabz.fundoonotes.model.User;
import com.bridgelabz.fundoonotes.response.Response;
import com.bridgelabz.fundoonotes.response.UserDetailResponse;
import com.bridgelabz.fundoonotes.service.UserService;
import com.bridgelabz.fundoonotes.utility.JWTToken;

/**
 * 
 * @author Niranjan c.t
 * @version 1.0
 * @Date : 29-02-2019
 */

@RestController
@CrossOrigin("*")
@RequestMapping
public class UserController {

	@Autowired
	private UserService userService;

	@Autowired
	private JWTToken jwtToken;

	@SuppressWarnings("unchecked")
	@PostMapping("user/registration")
	public ResponseEntity<Response> registration(@RequestBody RegisterDto newUserDTO) {
		System.out.println("Inside register");
		boolean resultStatus = userService.register(newUserDTO);
		if (!resultStatus) {
			// throw new UserException("User already Registered", 208);
			return (ResponseEntity<Response>) ResponseEntity.status(HttpStatus.ALREADY_REPORTED);
		}
		return ResponseEntity.status(HttpStatus.CREATED).body(new Response("registration successful", 200));
	}


	@GetMapping("user/verification/{token}")
	public ResponseEntity<Response> VerifyRegisterUser(@PathVariable("token") String token) {
		if (userService.isVerifiedUserToken(token)) {
			return ResponseEntity.status(HttpStatus.OK).body(new Response("Verification successful", HttpStatus.OK));
		}
		return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body(new Response("Verification Failed",HttpStatus.NOT_ACCEPTABLE));

	}


	@PostMapping("user/login")
	public ResponseEntity<UserDetailResponse> login(@RequestBody LoginDto loginDto) {
		User userdatafromdb = userService.login(loginDto);
		// checking user exist
		if (userdatafromdb != null) {
			String newToken = jwtToken.createJwtToken(userdatafromdb.getUserId());
			System.out.println("Token : " + newToken);
			return ResponseEntity.status(HttpStatus.OK).body(new UserDetailResponse("login successful", 200,
					newToken, userdatafromdb.getName()));
		
		}
		return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
				.body(new UserDetailResponse("Check Your mail for verification...", 401));
	}


	@PostMapping("user/forgotPassword")
	public ResponseEntity<Response> forgotPassword(@RequestParam("emailId") String emailId) {
		boolean userEmail = userService.isUserExist(emailId);
		if (userEmail) {
			return ResponseEntity.status(HttpStatus.FOUND).body(new Response(" user Exist.. email sent to reset password",HttpStatus.FOUND));
		}
		
		return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new Response("Unauthorized user",HttpStatus.UNAUTHORIZED));
	}


	@PutMapping("user/updatepassword/{token}")
	public ResponseEntity<Response> updatedPassword(@PathVariable("token") String token,
			@RequestBody() UpdatePassword upadatePassword) {
		System.out.println("Hitting update password controller");
		boolean updationStatus = userService.updatePassword(upadatePassword, token);
		if (updationStatus) {
			return ResponseEntity.status(HttpStatus.OK).body(new Response("Password updated sucessfully",HttpStatus.OK));
		}
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new Response("failed to update the password",HttpStatus.BAD_REQUEST));

	}
	
	/**
	 * Api to get perticular user
	 * @param userId given by the user to get the detail
	 * @return returns user object
	 */
	@GetMapping("users/getUser/{token}")
	public User getUser(@PathVariable String token)
	{

		User user = userService.getUser(token);


		if(user!=null)
			
			return user;
		
		return null;
//
//		return ResponseEntity.status(HttpStatus.OK).body(new UserResponse("User found" , user));
//		
//		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new UserResponse("User Not found" , user));
	}

	/**
	 * Api to get all users
	 * @return list of users
	 */
	@GetMapping("users/getUsers")
	public ResponseEntity<Response> getUsers()
	{
		List<User> user = userService.getUsers();
		return ResponseEntity.status(HttpStatus.OK).body(new Response("users found" , user));
	}
	
	
	
	

}
