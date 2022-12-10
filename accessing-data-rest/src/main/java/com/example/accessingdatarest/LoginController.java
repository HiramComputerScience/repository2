package com.example.accessingdatarest;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.accessingdatarest.model.request.UserLoginRequestModel;
import com.example.accessingdatarest.model.response.UserLoginResponseModel;

import shared.UserDto;

@RestController
@RequestMapping("/login") //http:localhost:8085/login
public class LoginController {
	@Autowired
	UserService userService;
	
	@PostMapping
	public UserLoginResponseModel loginUser(@RequestBody UserLoginRequestModel userLoginRequest) {
		
		UserLoginResponseModel userLoginResponse = userService.loginUser(userLoginRequest);
		return userLoginResponse;
	}
}
